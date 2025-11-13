<%@ page import="org.projets3s5.atelier.model.Ordinateur" %>
<%@ page import="org.projets3s5.atelier.model.Systeme" %>
<%@ page import="org.projets3s5.atelier.model.TypeOrdi" %>
<%@ page import="org.projets3s5.atelier.model.Client" %>
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

                <h2 class="h4">Ajouter un Ordinateur</h2>
                <p class="mb-4">Veuillez entrer les informations de l'ordinateur :</p>

                <!-- Formulaire d'ajout d'un ordinateur -->
                <form action="<%=request.getContextPath()%>/OrdinateurServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="marque" class="small">Marque:</label>
                        <input type="text" class="form-control form-control-sm" id="marque" name="marque" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="modele" class="small">Modèle:</label>
                        <input type="text" class="form-control form-control-sm" id="modele" name="modele" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="id_systeme" class="small">Système:</label>
                        <select class="form-control form-control-sm" id="id_systeme" name="id_systeme" required>
                            <option value="">Sélectionner le système</option>
                            <%
                                List<Systeme> systems = (List<Systeme>) request.getAttribute("systems");
                                for (Systeme system : systems) {
                            %>
                            <option value="<%= system.getIdSysteme() %>"><%= system.getVal() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="id_type_ordi" class="small">Type Ordinateur:</label>
                        <select class="form-control form-control-sm" id="id_type_ordi" name="id_type_ordi" required>
                            <option value="">Sélectionner le type</option>
                            <%
                                List<TypeOrdi> typesOrdis = (List<TypeOrdi>) request.getAttribute("typesOrdis");
                                for (TypeOrdi typeOrdi : typesOrdis) {
                            %>
                            <option value="<%= typeOrdi.getIdTypeOrdi() %>"><%= typeOrdi.getVal() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="id_client" class="small">Client:</label>
                        <select class="form-control form-control-sm" id="id_client" name="id_client" required>
                            <option value="">Sélectionner le client</option>
                            <%
                                List<Client> clients = (List<Client>) request.getAttribute("clients");
                                for (Client client : clients) {
                            %>
                            <option value="<%= client.getIdClient() %>"><%= client.getNom() %></option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Ordinateur</button>
                </form>

                <hr class="my-4">

                <!-- Liste des ordinateurs -->
                <h3 class="h5 mb-3">Liste des Ordinateurs</h3>

                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Marque</th>
                            <th>Modèle</th>
                            <th>Système</th>
                            <th>Type</th>
                            <th>Client</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            // Afficher la liste des ordinateurs
                            List<Ordinateur> ordinateurs = (List<Ordinateur>) request.getAttribute("ordinateurs");
                            if (ordinateurs != null) {
                                for (Ordinateur ordinateur : ordinateurs) {
                        %>
                        <tr>
                            <td><%= ordinateur.getIdOrdinateur() %></td>
                            <td><%= ordinateur.getMarque() %></td>
                            <td><%= ordinateur.getModele() %></td>
                            <td><%= ordinateur.getIdSysteme() %></td>
                            <td><%= ordinateur.getIdTypeOrdi() %></td>
                            <td><%= ordinateur.getIdClient() %></td>
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
                            <td colspan="7" class="text-center">Aucun ordinateur trouvé</td>
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
