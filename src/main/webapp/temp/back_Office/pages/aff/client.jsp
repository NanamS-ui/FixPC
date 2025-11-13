<%@ page import="org.projets3s5.atelier.model.Client" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<div class="container mt-4">
    <div class="container-fluid pt-2 px-2">
        <div class="row justify-content-center mx-0">
            <!-- Augmenter la largeur de la colonne en utilisant col-md-12 et col-lg-10 -->
            <div class="col-md-12 col-lg-10 text-center p-3">

                <%-- Affichage du message de succès ou d'erreur --%>
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                %>
                <div class="alert alert-success mb-3">
                    <%= message %>
                </div>
                <% } %>

                <h2 class="h4">Ajouter un Client</h2>
                <p class="mb-4">Veuillez entrer les informations du client :</p>

                <!-- Formulaire d'ajout d'un client -->
                <form action="<%=request.getContextPath()%>/ClientServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="nom" class="small">Nom:</label>
                        <input type="text" class="form-control form-control-sm" id="nom" name="nom" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="telephone" class="small">Téléphone:</label>
                        <input type="text" class="form-control form-control-sm" id="telephone" name="telephone" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="email" class="small">Email:</label>
                        <input type="email" class="form-control form-control-sm" id="email" name="email" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="dateInscription" class="small">Date Inscription:</label>
                        <input type="date" class="form-control form-control-sm" id="dateInscription" name="dateInscription" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Client</button>
                </form>

                <hr class="my-4">

                <!-- Liste des clients -->
                <h3 class="h5 mb-3">Liste des Clients</h3>

                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Téléphone</th>
                            <th>Email</th>
                            <th style="width: 150px;">Date</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            // Afficher la liste des clients
                            List<Client> clients = (List<Client>) request.getAttribute("clients");
                            if (clients != null) {
                                for (Client client : clients) {
                        %>
                        <tr>
                            <td><%= client.getIdClient() %></td>
                            <td><%= client.getNom() %></td>
                            <td><%= client.getTelephone() %></td>
                            <td><%= client.getEmail() %></td>
                            <td><%= client.getDateInscription() %></td>
                            <td>
                                <a href="#" class="btn btn-warning btn-sm">Modifier</a>
                                <a href="#" class="btn btn-danger btn-sm">Supprimer</a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5" class="text-center">Aucun client trouvé</td>
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


<jsp:include page="../footer.jsp"/>
