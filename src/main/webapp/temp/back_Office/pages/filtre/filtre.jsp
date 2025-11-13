<%@ page import="org.projets3s5.atelier.model.Probleme" %>
<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.Ordinateur" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>

<div class="container mt-4">
    <h3 class="mb-4">Filtrer par Problème</h3>

    <!-- Formulaire de filtrage -->
    <form action="<%= request.getContextPath() %>/filtre" method="post">
        <div class="form-group">
            <label for="probleme" class="small">Sélectionnez un Problème :</label>
            <select class="form-control" id="probleme" name="probleme" required>
                <option value="">Sélectionner un problème</option>
                <%
                    List<Probleme> problemeList = (List<Probleme>) request.getAttribute("problemes");
                    if (problemeList != null) {
                        for (Probleme probleme : problemeList) {
                %>
                <option value="<%= probleme.getIdProbleme() %>"><%= probleme.getDescription() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary btn-sm">Filtrer</button>
    </form>

    <%-- Affichage des résultats filtrés --%>
    <hr class="my-4">
    <h4 class="mb-3">Résultats</h4>
    <div class="table-responsive">
        <table class="table table-striped table-sm table-bordered">
            <thead>
            <tr>
                <th>ID Ordinateur</th>
                <th>Marque</th>
                <th>Modele</th>
                <th>Client</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Ordinateur> filteredProblemeList = (List<Ordinateur>) request.getAttribute("listOrdi");
                if (filteredProblemeList != null && !filteredProblemeList.isEmpty()) {
                    for (Ordinateur p : filteredProblemeList) {
                        // Assuming you have methods to get the associated Composant and Client from each Problème.
            %>
            <tr>
                <td><%= p.getIdOrdinateur() %></td>
                <td><%= p.getMarque() %></td>
                <td><%= p.getModele() %></td>
                <td><%= p.getIdClient() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4" class="text-center">Aucun problème trouvé pour ce filtre</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="../footer.jsp"/>
