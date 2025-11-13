<%@ page import="org.projets3s5.atelier.model.Composant" %>
<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.Prix_Composant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>

<div class="container mt-4">
    <h3 class="mb-4">Filtrer par historique</h3>

    <!-- Formulaire de filtre -->
    <form method="get" action="<%= request.getContextPath() %>/historiquePrix" class="form-inline">
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

        <button type="submit" class="btn btn-primary mb-2">Filtrer</button>
    </form>

    <!-- Affichage des résultats du filtre -->
    <%
        List<Prix_Composant> historiques = (List<Prix_Composant>) request.getAttribute("historiques");
        if (historiques != null && !historiques.isEmpty()) {
    %>
    <h4 class="mt-4">Historique des prix</h4>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID Prix</th>
            <th>Prix</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Prix_Composant historique : historiques) {
        %>
        <tr>
            <td><%= historique.getIdPrix() %></td>
            <td><%= historique.getPrixComposant() %></td>
            <td><%= historique.getDatePrix() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>
    <p>Aucun historique trouvé pour ce composant.</p>
    <%
        }
    %>

</div>

<jsp:include page="../footer.jsp"/>
