<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container-fluid py-4">
    <div class="row justify-content-center">
        <div class="col-12 col-xl-10">
            <!-- Message Alert -->
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <%= message %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <% } %>

            <!-- Card Formulaire de filtre -->
            <div class="card shadow-sm mb-4">
                <div class="card-header bg-primary text-white">
                    <h5 class="card-title mb-0">Filtrer les Retours</h5>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/CombinedServlet" method="POST">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="id_composant" class="form-label">Composant</label>
                                <select class="form-select" id="id_composant" name="id_composant" required>
                                    <option value="">Sélectionner un composant</option>
                                    <%
                                        List<Composant> composants = (List<Composant>) request.getAttribute("composantList");
                                        for (Composant composant : composants) {
                                    %>
                                    <option value="<%= composant.getIdComposant() %>"><%= composant.getNom() %></option>
                                    <% } %>
                                </select>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label for="id_type_retour" class="form-label">Type de Retour</label>
                                <select class="form-select" id="id_type_retour" name="id_type_retour" required>
                                    <option value="">Sélectionner un type de retour</option>
                                    <%
                                        List<TypeRetour> typeRetours = (List<TypeRetour>) request.getAttribute("typeRetourList");
                                        for (TypeRetour type : typeRetours) {
                                    %>
                                    <option value="<%= type.getIdTypeRetour() %>"><%= type.getVal() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="id_type_retour" class="form-label">Type d'ordinateur</label>
                                <select class="form-select" id="id_type_ordi" name="id_type_ordi" required>
                                    <option value="">Sélectionner un type de ordi</option>
                                    <%
                                        List<TypeOrdi> typeRetours2 = (List<TypeOrdi>) request.getAttribute("typeOrdisList");
                                        for (TypeOrdi type : typeRetours2) {
                                    %>
                                    <option value="<%= type.getIdTypeOrdi() %>"><%= type.getVal() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-search me-2"></i>Filtrer
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Card Liste des retours -->
            <div class="card shadow-sm">
                <div class="card-header bg-white">
                    <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title mb-0">Liste des Retours</h5>
                        <div class="input-group w-auto">
                            <input type="text" class="form-control form-control-sm" id="searchInput" placeholder="Rechercher...">
                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                        </div>
                    </div>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover table-striped mb-0">
                            <thead class="table-light">
                            <tr>
                                <th class="px-3">ID</th>
                                <th>Description</th>
                                <th>Type</th>
                                <th>Client</th>
                                <th>Ordinateur</th>

                            </tr>
                            </thead>
                            <tbody>
                            <%
                                List<CombinedRetour> retours = (List<CombinedRetour>) request.getAttribute("retourList");
                                if (retours != null && !retours.isEmpty()) {
                                    for (CombinedRetour retour : retours) {
                            %>
                            <tr>
                                <td class="px-3"><%= retour.getIdRetour() %></td>
                                <td><%= retour.getRetourDescription() %></td>
                                <td><%= retour.getTypeRetourVal() %></td>
                                <td><%= retour.getClientName()%>
                                </td>
                                <td><%= retour.getMarque() +" "+retour.getModele()%></td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="5" class="text-center py-3">Aucun retour trouvé</td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal de confirmation de suppression -->
<div class="modal fade" id="deleteModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirmer la suppression</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Êtes-vous sûr de vouloir supprimer ce retour ?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                <button type="button" class="btn btn-danger" id="confirmDelete">Supprimer</button>
            </div>
        </div>
    </div>
</div>

<script>
    function confirmerSuppression(id) {
        const modal = new bootstrap.Modal(document.getElementById('deleteModal'));
        document.getElementById('confirmDelete').onclick = function() {
            window.location.href = `${pageContext.request.contextPath}/RetourServlet?action=delete&id=${id}`;
        };
        modal.show();
    }

    function modifierRetour(id) {
        window.location.href = `${pageContext.request.contextPath}/RetourServlet?action=edit&id=${id}`;
    }

    // Recherche dans le tableau
    document.getElementById('searchInput').addEventListener('keyup', function() {
        const searchText = this.value.toLowerCase();
        const table = document.querySelector('table tbody');
        const rows = table.getElementsByTagName('tr');

        for (let row of rows) {
            const cells = row.getElementsByTagName('td');
            let found = false;
            for (let cell of cells) {
                if (cell.textContent.toLowerCase().includes(searchText)) {
                    found = true;
                    break;
                }
            }
            row.style.display = found ? '' : 'none';
        }
    });
</script>

<jsp:include page="../footer.jsp"/>
