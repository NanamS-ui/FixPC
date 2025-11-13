<%@ page import="org.projets3s5.atelier.model.Probleme" %>
<%@ page import="org.projets3s5.atelier.model.TypeProbleme" %>
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
                    <h5 class="card-title mb-0">Ajouter un Problème</h5>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/ProblemeServlet" method="POST">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="description" class="form-label">Description</label>
                                <input type="text" class="form-control" id="description" name="description" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="id_type_probleme" class="form-label">Type de Problème</label>
                                <select class="form-select" id="id_type_probleme" name="id_type_probleme" required>
                                    <option value="">Sélectionner un type</option>
                                    <%
                                        List<TypeProbleme> typeProblemes = (List<TypeProbleme>) request.getAttribute("typeproblemes");
                                        for (TypeProbleme type : typeProblemes) {
                                    %>
                                    <option value="<%= type.getIdTypeProbleme() %>"><%= type.getVal() %></option>
                                    <% } %>
                                </select>
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
                        <h5 class="card-title mb-0">Liste des Problèmes</h5>
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
                                <th>Description</th>
                                <th>Type</th>
                                <th class="text-end px-3">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                List<Probleme> problemes = (List<Probleme>) request.getAttribute("problemes");
                                if (problemes != null && !problemes.isEmpty()) {
                                    for (Probleme probleme : problemes) {
                            %>
                            <tr>
                                <td class="px-3"><%= probleme.getIdProbleme() %></td>
                                <td><%= probleme.getDescription() %></td>
                                <td><%= probleme.getIdTypeProbleme() %></td>
                                <td class="text-end px-3">
                                    <button class="btn btn-warning btn-sm"
                                            onclick="modifierProbleme(<%= probleme.getIdProbleme() %>)">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger btn-sm ms-1"
                                            onclick="confirmerSuppression(<%= probleme.getIdProbleme() %>)">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="4" class="text-center py-3">Aucun problème enregistré</td>
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
                Êtes-vous sûr de vouloir supprimer ce problème ?
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
            // Ajoutez ici la logique de suppression
            window.location.href = `${pageContext.request.contextPath}/ProblemeServlet?action=delete&id=${id}`;
        };
        modal.show();
    }

    function modifierProbleme(id) {
        // Ajoutez ici la logique de modification
        window.location.href = `${pageContext.request.contextPath}/ProblemeServlet?action=edit&id=${id}`;
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