<%@ page import="org.projets3s5.atelier.model.Poste" %>
<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.Employe" %>
<%@ page import="org.projets3s5.atelier.model.Genre" %>
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

                <h2 class="h4">Ajouter un Employé</h2>
                <p class="mb-4">Veuillez entrer les informations de l'employé :</p>

                <!-- Formulaire d'ajout d'un employé -->
                <form action="<%=request.getContextPath()%>/EmployeServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="nom" class="small">Nom :</label>
                        <input type="text" class="form-control form-control-sm" id="nom" name="nom" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="prenom" class="small">Prénom :</label>
                        <input type="text" class="form-control form-control-sm" id="prenom" name="prenom" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="username" class="small">Nom d'utilisateur :</label>
                        <input type="text" class="form-control form-control-sm" id="username" name="username" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="poste" class="small">Poste :</label>
                        <select id="poste" name="poste" class="form-control form-control-sm" required>
                            <%
                                List<Poste> postes = (List<Poste>) request.getAttribute("postes");
                                if (postes != null) {
                                    for (Poste poste : postes) {
                            %>
                            <option value="<%= poste.getIdPoste() %>"><%= poste.getVal() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group mb-3">
                        <label for="poste" class="small">Genre :</label>
                        <select id="genre" name="genre" class="form-control form-control-sm" required>
                            <%
                                List<Genre> genres = (List<Genre>) request.getAttribute("genres");
                                if (postes != null) {
                                    for (Genre poste : genres) {
                            %>
                            <option value="<%= poste.getIdGenre() %>"><%= poste.getVal() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="username" class="small">Email :</label>
                        <input type="text" class="form-control form-control-sm" id="email" name="email" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="mdp" class="small">Mot de passe :</label>
                        <input type="password" class="form-control form-control-sm" id="mdp" name="mdp" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Employé</button>
                </form>

                <hr class="my-4">

                <!-- Liste des employés -->
                <h3 class="h5 mb-3">Liste des Employés</h3>

                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID Employé</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Nom d'utilisateur</th>
                            <th>Poste</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            // Afficher la liste des employés
                            List<Employe> employes = (List<Employe>) request.getAttribute("employes");
                            if (employes != null) {
                                for (Employe emp : employes) {
                                    // Adapter selon votre modèle Employé
                                    // Exemple :
                                    // Employe employe = (Employe) emp;
                        %>
                        <tr>
                            <td><%= emp.getIdEmploye() %></td>
                            <td><%= emp.getNom() %></td>
                            <td><%= emp.getPrenom() %></td>
                            <td><%= emp.getUsername() %></td>
                            <td><%= emp.getIdPoste() %></td>
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
                            <td colspan="6" class="text-center">Aucun employé trouvé</td>
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
