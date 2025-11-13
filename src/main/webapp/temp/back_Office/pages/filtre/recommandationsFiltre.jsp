<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.RecommandationComposant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>

<div class="container mt-4">
    <h2 class="h4 text-center">Filtrer les Recommandations</h2>

    <!-- Formulaire de filtre -->
    <form action="<%=request.getContextPath()%>/RecommandationFiltreServlet" method="GET" class="mt-3">
        <div class="row justify-content-center">
            <!-- Sélection du mois -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="mois" class="small">Mois :</label>
                    <select class="form-control form-control-sm" id="mois" name="mois" required>
                        <option value="">Sélectionner un mois</option>
                        <option value="1">Janvier</option>
                        <option value="2">Février</option>
                        <option value="3">Mars</option>
                        <option value="4">Avril</option>
                        <option value="5">Mai</option>
                        <option value="6">Juin</option>
                        <option value="7">Juillet</option>
                        <option value="8">Août</option>
                        <option value="9">Septembre</option>
                        <option value="10">Octobre</option>
                        <option value="11">Novembre</option>
                        <option value="12">Décembre</option>
                    </select>

                </div>
            </div>

            <!-- Entrée de l'année -->
            <div class="col-md-3">
                <div class="form-group">
                    <label for="annee" class="small">Année :</label>
                    <select class="form-control form-control-sm" id="annee" name="annee" required>
                        <option value="">Sélectionner une année</option>
                        <option value="2000">2000</option>
                        <option value="2001">2001</option>
                        <option value="2002">2002</option>
                        <option value="2003">2003</option>
                        <option value="2004">2004</option>
                        <option value="2005">2005</option>
                        <option value="2006">2006</option>
                        <option value="2007">2007</option>
                        <option value="2008">2008</option>
                        <option value="2009">2009</option>
                        <option value="2010">2010</option>
                        <option value="2011">2011</option>
                        <option value="2012">2012</option>
                        <option value="2013">2013</option>
                        <option value="2014">2014</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                        <option value="2021">2021</option>
                        <option value="2022">2022</option>
                        <option value="2023">2023</option>
                        <option value="2024">2024</option>
                        <option value="2025">2025</option>
                    </select>

                </div>
            </div>

            <!-- Bouton de filtre -->
            <div class="col-md-3 align-self-end">
                <button type="submit" class="btn btn-primary btn-sm">Filtrer</button>
            </div>
        </div>
    </form>

    <!-- Liste des composants filtrés -->
    <div class="mt-5">
        <h3 class="h5">Résultats du filtre</h3>
        <table class="table table-bordered table-striped table-sm">
            <thead>
            <tr>
                <th>ID Composant</th>
                <th>Valeur</th>
                <th>Prix Vente</th>
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
