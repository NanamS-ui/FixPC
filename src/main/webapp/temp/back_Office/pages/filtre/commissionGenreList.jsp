<%@ page import="org.projets3s5.atelier.model.Commission" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>

<div class="container mt-4">
    <h3 class="mb-4">Liste des Commissions des Techniciens</h3>

    <form action="<%= request.getContextPath() %>/CommissionGenreServlet" method="get">
        <div class="form-row">
            <div class="col-md-5">
                <label for="dateDebut" class="small">Date Début :</label>
                <input type="date" class="form-control" id="dateDebut" name="dateDebut" required>
            </div>
            <div class="col-md-5">
                <label for="dateFin" class="small">Date Fin :</label>
                <input type="date" class="form-control" id="dateFin" name="dateFin" required>
            </div>

            <div class="col-md-5">
                <label for="dateFin" class="small">Seuil prix :</label>
                <input type="text" class="form-control" id="seuil" name="seuil" required>
            </div>
            <div class="col-md-2 align-self-end">
                <button type="submit" class="btn btn-primary btn-sm">Filtrer</button>
            </div>
        </div>
    </form>

    <%-- Affichage des résultats --%>
    <hr class="my-4">
    <h4 class="mb-3">Résultats pour la période :
        <%
            String dateDebut = (String) request.getAttribute("dateDebut");
            String dateFin = (String) request.getAttribute("dateFin");
        %>
        <%= (dateDebut != null && dateFin != null) ? dateDebut + " - " + dateFin : "Non défini" %>
    </h4>

    <div class="table-responsive">
        <table class="table table-striped table-sm table-bordered">
            <thead>
            <tr>
                <th>Genre</th>
                <th>Prix réparation</th>
                <th>Commission (5%)</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Commission> commissionList = (List<Commission>) request.getAttribute("commissionList");
                if (commissionList != null && !commissionList.isEmpty()) {
                    for (Commission commission : commissionList) {
            %>
            <tr>
                <td><%= commission.getGenreVal()%></td>
                <td><%= commission.getPrixReparation()%></td>
                <td><%= commission.getCommissionTechnicien() %> Ar</td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4" class="text-center">Aucune commission trouvée pour cette période</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
