<%@ page import="org.projets3s5.atelier.model.TypeProbleme" %>
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

                <h2 class="h4">Ajouter un Type de Problème</h2>
                <p class="mb-4">Veuillez entrer les informations du Type de Problème :</p>

                <!-- Formulaire d'ajout d'un type de problème -->
                <form action="<%=request.getContextPath()%>/TypeProblemeServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="val" class="small">Valeur:</label>
                        <input type="text" class="form-control form-control-sm" id="val" name="val" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Type de Problème</button>
                </form>

                <hr class="my-4">

                <!-- Liste des types de problèmes -->
                <h3 class="h5 mb-3">Liste des Types de Problèmes</h3>

                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID TypeProbleme</th>
                            <th>Valeur</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            // Afficher la liste des types de problèmes
                            List<TypeProbleme> typeProblemes = (List<TypeProbleme>) request.getAttribute("typeProblemes");
                            if (typeProblemes != null) {
                                for (TypeProbleme typeProbleme : typeProblemes) {
                        %>
                        <tr>
                            <td><%= typeProbleme.getIdTypeProbleme() %></td>
                            <td><%= typeProbleme.getVal() %></td>
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
                            <td colspan="3" class="text-center">Aucun type de problème trouvé</td>
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
