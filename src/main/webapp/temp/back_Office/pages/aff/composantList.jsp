<%@ page import="org.projets3s5.atelier.model.Composant" %>
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
                    <h5 class="card-title mb-0">Ajouter un Composant</h5>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/ComposantServlet" method="POST">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="nom" class="form-label">Nom</label>
                                <input type="text" class="form-control" id="nom" name="nom" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="img" class="form-label">Image URL</label>
                                <input type="text" class="form-control" id="img" name="img">
                            </div>
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
                        <h5 class="card-title mb-0">Liste des Composants</h5>
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
                                <th>Nom</th>
                                <th>Image</th>
                                <th class="text-end px-3">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                List<Composant> composants = (List<Composant>) request.getAttribute("composants");
                                if (composants != null && !composants.isEmpty()) {
                                    for (Composant composant : composants) {
                            %>
                            <tr>
                                <td class="px-3"><%= composant.getIdComposant() %></td>
                                <td><%= composant.getNom() %></td>
                                <td>
                                    <% if (composant.getImg() != null && !composant.getImg().isEmpty()) { %>
                                    <img src="<%= composant.getImg() %>" alt="<%= composant.getNom() %>" style="height: 30px; width: auto;">
                                    <% } else { %>
                                    <span class="text-muted">Aucune image</span>
                                    <% } %>
                                </td>
                                <td class="text-end px-3">
                                    <button class="btn btn-warning btn-sm"
                                            onclick="modifierComposant(<%= composant.getIdComposant() %>)">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger btn-sm ms-1"
                                            onclick="confirmerSuppression(<%= composant.getIdComposant() %>)">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="6" class="text-center py-3">Aucun composant enregistré</td>
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
                Êtes-vous sûr de vouloir supprimer ce composant ?
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
            window.location.href = `${pageContext.request.contextPath}/ComposantServlet?action=delete&id=${id}`;
        };
        modal.show();
    }

    function modifierComposant(id) {
        window.location.href = `${pageContext.request.contextPath}/ComposantServlet?action=edit&id=${id}`;
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