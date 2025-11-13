<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.RecommandationComposant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>

<div class="container mt-4">
    <h2 class="h4 text-center">Listes Recommandations</h2>

    <!-- Liste des composants filtrés -->
    <div class="mt-5">
        <h3 class="h5">Résultats du filtre</h3>
        <table class="table table-bordered table-striped table-sm">
            <thead>
            <tr>
                <th>ID Composant</th>
                <th>Valeur</th>
                <th>Prix Vente</th>
                <th>Annee</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<RecommandationComposant> recommandations = (List<RecommandationComposant>) request.getAttribute("recommandations");
                if (recommandations != null && !recommandations.isEmpty()) {
                    for (RecommandationComposant recommandation : recommandations) {
            %>
            <tr>
                <td><%= recommandation.getIdComposant() %></td>
                <td><%= recommandation.getComposantVal() %></td>
                <td><%= recommandation.getComposantPrixVente() %> €</td>
                <td><%= recommandation.getAnnee()%></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="8" class="text-center">Aucune recommandation trouvée</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
