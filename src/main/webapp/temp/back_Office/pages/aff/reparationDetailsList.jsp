<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.*" %>
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


                <h2 class="h4">Détails des Réparations</h2>
                <p class="mb-4">Voici la liste des détails de réparation enregistrés :</p>

                <%-- Table des détails de réparation --%>
                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID Réparation</th>
                            <th>Prix Réparation</th>
                            <th>Quantité Utilisée</th>
                            <th>Composant</th>
                            <th>Problème</th>
                            <th>Type Réparation</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<ReparationDetails> detailsList = (List<ReparationDetails>) request.getAttribute("detailsList");
                            if (detailsList != null && !detailsList.isEmpty()) {
                                for (ReparationDetails detail : detailsList) {
                        %>
                        <tr>
                            <td><%= detail.getIdReparation() %></td>
                            <td><%= detail.getPrixReparation() %> €</td>
                            <td><%= detail.getQuantiteUtilisee() %> unités</td>
                            <td><%= detail.getIdComposant() != null ? detail.getIdComposant() : "Non spécifié" %></td>
                            <td><%= detail.getIdProbleme() != null ? detail.getIdProbleme() : "Non spécifié" %></td>
                            <td><%= detail.getIdTypeReparation() != null ? detail.getIdTypeReparation(): "Non spécifié" %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/ReparationDetailsEditServlet?id=<%= detail.getIdReparation() %>" class="btn btn-warning btn-sm">Modifier</a>
                                <a href="<%= request.getContextPath() %>/ReparationDetailsDeleteServlet?id=<%= detail.getIdReparation() %>" class="btn btn-danger btn-sm">Supprimer</a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center">Aucun détail de réparation trouvé</td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>

                <hr class="my-4">

                <%-- Formulaire pour ajouter un nouveau détail de réparation --%>
                    <!-- Formulaire pour ajouter un nouveau détail de réparation -->
                    <h4>Ajouter un Détail de Réparation</h4>
                    <form method="post" action="<%= request.getContextPath() %>/ReparationDetailsServlet">
                        <div class="form-group">
                            <label for="id_type_reparation">Type de Réparation</label>
                            <select name="id_type_reparation" id="id_type_reparation" class="form-control" required>
                                <%
                                    List<TypeReparation> typeReparations = (List<TypeReparation>) request.getAttribute("typeReparations");
                                    for (TypeReparation type : typeReparations) {
                                %>
                                <option value="<%= type.getIdTypeReparation() %>"><%= type.getVal() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

                        <!-- Section des composants affichée sans condition -->
                        <div id="composant-section">
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
                            <div class="form-group">
                                <label for="quantite_utilisee">Quantité Utilisée</label>
                                <input type="number" name="quantite_utilisee" id="quantite_utilisee" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="id_probleme">Problème</label>
                            <select name="id_probleme" id="id_probleme" class="form-control" required>
                                <%
                                    List<Probleme> problemes = (List<Probleme>) request.getAttribute("problemes");
                                    for (Probleme probleme : problemes) {
                                %>
                                <option value="<%= probleme.getIdProbleme() %>"><%= probleme.getDescription() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="id_reparation">Réparation</label>
                            <select name="id_reparation" id="id_reparation" class="form-control" required>
                                <%
                                    List<Reparation> reparations = (List<Reparation>) request.getAttribute("reparations");
                                    for (Reparation reparation : reparations) {
                                %>
                                <option value="<%= reparation.getIdReparation() %>"><%= reparation.getIdReparation() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

                        <div class="form-group mb-3">
                            <label for="id_employe" class="small">Employé:</label>
                            <select class="form-control form-control-sm" id="id_employe" name="id_employe" required>
                                <option value="">Sélectionner l'employé</option>
                                <%
                                    List<Employe> employes = (List<Employe>) request.getAttribute("employes");
                                    for (Employe employe : employes) {
                                %>
                                <option value="<%= employe.getIdEmploye() %>"><%= employe.getNom() %></option>
                                <% } %>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary btn-sm">Ajouter Détail</button>
                    </form>


            </div>
        </div>
    </div>
</div>
<%--<script>--%>
<%--    document.addEventListener("DOMContentLoaded", function () {--%>
<%--        const typeReparation = document.getElementById("id_type_reparation");--%>
<%--        const composantSection = document.getElementById("composant-section");--%>

<%--        // Afficher ou masquer la section des composants en fonction de la sélection--%>
<%--        typeReparation.addEventListener("change", function () {--%>
<%--            const selectedOptionText = typeReparation.options[typeReparation.selectedIndex].text.toLowerCase();--%>

<%--            if (selectedOptionText === "remplacement") {--%>
<%--                composantSection.classList.remove("d-none");--%>
<%--            } else {--%>
<%--                composantSection.classList.add("d-none");--%>
<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
<style>
    .d-none {
        display: none;
    }
</style>
<jsp:include page="../footer.jsp"/>
