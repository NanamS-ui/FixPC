<%@ page import="org.projets3s5.atelier.model.Recommandation" %>
<%@ page import="org.projets3s5.atelier.model.Composant" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<div class="container mt-4">
    <div class="container-fluid pt-2 px-2">
        <div class="row justify-content-center mx-0">
            <div class="col-md-12 col-lg-10 text-center p-3">

                <%-- Message de succès ou d'erreur --%>
                <%
                    String message = (String) request.getAttribute("message");
                    String error = (String) request.getAttribute("error");
                    if (message != null) {
                %>
                <div class="alert alert-success mb-3">
                    <%= message %>
                </div>
                <% } else if (error != null) { %>
                <div class="alert alert-danger mb-3">
                    <%= error %>
                </div>
                <% } %>

                <h2 class="h4">Ajouter une Recommandation</h2>
                <p class="mb-4">Veuillez entrer les informations de la recommandation :</p>

                <!-- Formulaire d'ajout d'une recommandation -->
                <form action="<%=request.getContextPath()%>/RecommandationServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="dateRecommandation" class="small">Date:</label>
                        <input type="date" class="form-control form-control-sm" id="dateRecommandation" name="dateRecommandation" required>
                    </div>

                    <div class="form-group mb-3">
                        <label for="id_composant" class="small">Composant:</label>
                        <select class="form-control form-control-sm" id="id_composant" name="id_composant" required>
                            <%-- Liste déroulante des composants --%>
                            <%
                                List<Composant> composants = (List<Composant>) request.getAttribute("composants");
                                if (composants != null) {
                                    for (Composant composant : composants) {
                            %>
                            <option value="<%= composant.getIdComposant() %>">
                                <%= composant.getNom() %>
                            </option>
                            <%      }
                            }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Recommandation</button>
                </form>

                <hr class="my-4">

                <!-- Liste des recommandations -->
                <h3 class="h5 mb-3">Liste des Recommandations</h3>
                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID Recommandation</th>
                            <th>Mois</th>
                            <th>Année</th>
                            <th>Composant</th>
                             </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Recommandation> recommandations = (List<Recommandation>) request.getAttribute("recommandations");
                            if (recommandations != null) {
                                for (Recommandation recommandation : recommandations) {
                        %>
                        <tr>
                            <td><%= recommandation.getIdRecommandation() %></td>
                            <td><%= recommandation.getMois() %></td>
                            <td><%= recommandation.getAnnee() %></td>
                            <td><%= recommandation.getIdComposant() %></td>
                           </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="4" class="text-center">Aucune recommandation trouvée</td>
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
