<%@ page import="org.projets3s5.atelier.model.Stock" %>
<%@ page import="org.projets3s5.atelier.model.Composant" %>
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
                <div class="alert alert-success mb-3"> <%= message %> </div>
                <% } %>

                <%-- Affichage du message d'erreur --%>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                <div class="alert alert-danger mb-3"> <%= error %> </div>
                <% } %>

                <h2 class="h4">Liste des Stocks</h2>
                <p class="mb-4">Voici la liste des stocks disponibles :</p>

                <!-- Liste des stocks -->
                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID Stock</th>
                            <th>Composant</th>
                            <th>Quantité Disponible</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Stock> stockList = (List<Stock>) request.getAttribute("stockList");
                            if (stockList != null) {
                                for (Stock stock : stockList) {
                        %>
                        <tr>
                            <td><%= stock.getIdStock() %></td>
                            <td><%= stock.getIdComposant() %></td>
                            <td><%= stock.getQuantiteDisponible() %></td>
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
                            <td colspan="3" class="text-center">Aucun stock trouvé</td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>

                <hr class="my-4">

                <!-- Formulaire d'ajout de stock -->
                <h3 class="h5 mb-3">Ajouter un Stock</h3>
                <form action="<%= request.getContextPath() %>/StockServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="quantite_disponible" class="small">Quantité Disponible:</label>
                        <input type="number" class="form-control form-control-sm" id="quantite_disponible" name="quantite_disponible" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="id_composant" class="small">Composant:</label>
                        <select class="form-control form-control-sm" id="id_composant" name="id_composant" required>
                            <option value="">Sélectionner le composant</option>
                            <%
                                List<Composant> composants = (List<Composant>) request.getAttribute("composants");
                                if (composants != null) {
                                    for (Composant composant : composants) {
                            %>
                            <option value="<%= composant.getIdComposant() %>"><%= composant.getNom() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Stock</button>
                </form>

            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
