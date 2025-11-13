<%@ page import="org.projets3s5.atelier.model.TypeRetour" %>
<%@ page import="java.util.List" %>
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

            <!-- Card Formulaire -->
            <div class="card shadow-sm mb-4">
                <div class="card-header bg-primary text-white">
                    <h5 class="card-title mb-0">Ajouter un Type de Retour</h5>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/TypeRetourServlet" method="POST">
                        <div class="mb-3">
                            <label for="val" class="form-label">Type de Retour</label>
                            <input type="text" class="form-control" id="val" name="val" required>
                        </div>
                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-plus-circle me-2"></i>Ajouter
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Card Liste -->
            <div class="card shadow-sm">
                <div class="card-header bg-white">
                    <div class="d-flex justify-content-between align-items-center">
                        <h5 class="card-title mb-0">Liste des Types de Retour</h5>
                        <div class="input-group w-auto">
                            <input type="text" class="form-control form-control-sm" id="searchInput"
                                   placeholder="Rechercher...">
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
                                <th>Type</th>
                                <th class="text-end px-3">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                List<TypeRetour> typeRetours = (List<TypeRetour>) request.getAttribute("typeRetours");
                                if (typeRetours != null && !typeRetours.isEmpty()) {
                                    for (TypeRetour typeRetour : typeRetours) {
                            %>
                            <tr>
                                <td class="px-3"><%= typeRetour.getIdTypeRetour() %></td>
                                <td><%= typeRetour.getVal() %></td>
                                <td class="text-end px-3">
                                    <button class="btn btn-warning btn-sm"
                                            onclick="modifierTypeRetour(<%= typeRetour.getIdTypeRetour() %>)">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger btn-sm ms-1"
                                            onclick="confirmerSuppression(<%= typeRetour.getIdTypeRetour() %>)">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="3" class="text-center py-3">Aucun type de retour enregistré</td>
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
                Êtes-vous sûr de vouloir supprimer ce type de retour ?
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
            window.location.href = `${pageContext.request.contextPath}/TypeRetourServlet?action=delete&id=${id}`;
        };
        modal.show();
    }

    function modifierTypeRetour(id) {
        window.location.href = `${pageContext.request.contextPath}/TypeRetourServlet?action=edit&id=${id}`;
    }

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
