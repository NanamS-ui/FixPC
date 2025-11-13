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
                    <form action="<%=request.getContextPath()%>/RetourClientServlet" method="GET">
                        <div class="row">
                            <label for="date" class="form-label">Date Retour :</label>
                            <input type="date" class="form-control" id="date" name="date_retour" required>
                        </div>
                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-search me-2"></i>Filtrer
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Card Liste des Clients -->
            <div class="card shadow-sm mb-4">
                <div class="card-header bg-secondary text-white">
                    <h5 class="card-title mb-0">Liste des Clients</h5>
                </div>
                <div class="card-body">
                    <%
                        List<Client> clients = (List<Client>) request.getAttribute("clients");
                        if (clients != null && !clients.isEmpty()) {
                    %>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">ID Client</th>
                                <th scope="col">Nom</th>
                                <th scope="col">Téléphone</th>
                                <th scope="col">Email</th>
                                <th scope="col">Date d'inscription</th>
                                <th scope="col">Retour</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (Client client : clients) { %>
                            <tr>
                                <td><%= client.getIdClient() %></td>
                                <td><%= client.getNom() %></td>
                                <td><%= client.getTelephone() %></td>
                                <td><%= client.getEmail() != null ? client.getEmail() : "Non spécifié" %></td>
                                <td><%= client.getDateInscription() %></td>
                                <td><%= client.getRetour_description()%></td>
                            </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                    <% } else { %>
                    <div class="alert alert-warning" role="alert">
                        Aucun client trouvé pour la date sélectionnée.
                    </div>
                    <% } %>
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
