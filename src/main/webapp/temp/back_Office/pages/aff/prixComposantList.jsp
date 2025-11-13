<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.Prix_Composant" %>
<%@ page import="org.projets3s5.atelier.model.Composant" %>
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
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                <div class="alert alert-danger mb-3">
                    <%= error %>
                </div>
                <% } %>

                <h2 class="h4">Ajouter un Prix Composant</h2>
                <p class="mb-4">Veuillez entrer les informations du prix composant :</p>

                <!-- Formulaire d'ajout d'un prix composant -->
                <form action="<%=request.getContextPath()%>/PrixComposantServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="prix" class="small">Prix du Composant:</label>
                        <input type="number" class="form-control form-control-sm" id="prix" name="prix" step="0.01" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="datePrix" class="small">Date du Prix:</label>
                        <input type="date" class="form-control form-control-sm" id="datePrix" name="datePrix" required>
                    </div>
                    <div class="form-group">
                        <label for="id_composant">Composant</label>
                        <select name="id_composant" id="id_composant" class="form-control">
                            <%
                                List<Composant> composants = (List<Composant>) request.getAttribute("composants");
                                for (Composant composant : composants) {
                            %>
                            <option value="<%= composant.getIdComposant() %>"><%= composant.getNom() %></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Prix Composant</button>
                </form>

                <hr class="my-4">

                <!-- Liste des prix composants -->
                <h3 class="h5 mb-3">Liste des Prix Composants</h3>

                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID Prix</th>
                            <th>Prix</th>
                            <th>Date du Prix</th>
                            <th>ID Composant</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            // Afficher la liste des prix composants
                            List<Prix_Composant> prixComposants = (List<Prix_Composant>) request.getAttribute("prixComposants");
                            if (prixComposants != null) {
                                for (Prix_Composant prixComposant : prixComposants) {
                        %>
                        <tr>
                            <td><%= prixComposant.getIdPrix() %></td>
                            <td><%= prixComposant.getPrixComposant() %></td>
                            <td><%= prixComposant.getDatePrix() %></td>
                            <td><%= prixComposant.getIdComposant() %></td>
                            <td>
                                <!-- Ajouter des actions, par exemple modifier ou supprimer -->
                                <a href="#" class="btn btn-warning btn-sm">Modifier</a>
                                <a href="#" class="btn btn-danger btn-sm">Supprimer</a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5" class="text-center">Aucun prix composant trouvé</td>
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
