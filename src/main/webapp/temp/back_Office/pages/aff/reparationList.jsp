<%@ page import="org.projets3s5.atelier.model.Reparation" %>
<%@ page import="org.projets3s5.atelier.model.Ordinateur" %>
<%@ page import="org.projets3s5.atelier.model.Employe" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="container mt-4">
    <div class="container-fluid pt-2 px-2">
        <div class="row justify-content-center mx-0">
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

                <h2 class="h4">Ajouter une Réparation</h2>
                <p class="mb-4">Veuillez entrer les informations de la réparation :</p>

                <!-- Formulaire d'ajout d'une réparation -->
                <form action="<%=request.getContextPath()%>/ReparationServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="date_reparation" class="small">Date de Réparation:</label>
                        <input type="date" class="form-control form-control-sm" id="date_reparation" name="date_reparation" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="description" class="small">Description:</label>
                        <input type="text" class="form-control form-control-sm" id="description" name="description" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="status" class="small">Status:</label>
                        <input type="text" class="form-control form-control-sm" id="status" name="status" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="id_ordinateur" class="small">Ordinateur:</label>
                        <select class="form-control form-control-sm" id="id_ordinateur" name="id_ordinateur" required>
                            <option value="">Sélectionner l'ordinateur</option>
                            <%
                                List<Ordinateur> ordinateurs = (List<Ordinateur>) request.getAttribute("ordinateurs");
                                for (Ordinateur ordinateur : ordinateurs) {
                            %>
                            <option value="<%= ordinateur.getIdOrdinateur() %>"><%= ordinateur.getMarque() + " " + ordinateur.getModele() %></option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Réparation</button>
                </form>

                <hr class="my-4">

                <!-- Liste des réparations -->
                <h3 class="h5 mb-3">Liste des Réparations</h3>

                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID Réparation</th>
                            <th>Date de Réparation</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Ordinateur</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            // Afficher la liste des réparations
                            List<Reparation> reparations = (List<Reparation>) request.getAttribute("reparations");
                            if (reparations != null) {
                                for (Reparation reparation : reparations) {
                        %>
                        <tr>
                            <td><%= reparation.getIdReparation() %></td>
                            <td><%= reparation.getDateReparation() %></td>
                            <td><%= reparation.getDescription() %></td>
                            <td><%= reparation.getStatus() %></td>
                            <td><%= reparation.getIdOrdinateur() %></td>
                            <td>
                                <!-- Ajouter des actions comme modifier ou supprimer -->
                                <a href="#" class="btn btn-warning btn-sm">Modifier</a>
                                <a href="#" class="btn btn-danger btn-sm">Supprimer</a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center">Aucune réparation trouvée</td>
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
