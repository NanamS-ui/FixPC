<%@ page import="org.projets3s5.atelier.model.MvtStock" %>
<%@ page import="org.projets3s5.atelier.model.Achat" %>
<%@ page import="org.projets3s5.atelier.model.Composant" %>
<%@ page import="org.projets3s5.atelier.model.ReparationDetails" %>
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

                <h2 class="h4">Ajouter un Mouvement de Stock</h2>
                <p class="mb-4">Veuillez entrer les informations du mouvement :</p>

                <!-- Formulaire d'ajout de mouvement de stock -->
                <form action="<%= request.getContextPath() %>/MvtStockServlet" method="POST">
                    <div class="form-group mb-3">
                        <label for="date_mouvement" class="small">Date du Mouvement:</label>
                        <input type="date" class="form-control form-control-sm" id="date_mouvement" name="date_mouvement" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="entree" class="small">Entrée (quantité):</label>
                        <input type="number" class="form-control form-control-sm" id="entree" name="entree" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="sortie" class="small">Sortie (quantité):</label>
                        <input type="number" class="form-control form-control-sm" id="sortie" name="sortie" required>
                    </div>
                    <div class="form-group mb-3">
                        <label for="type_mvt" class="small">Type de Mouvement:</label>
                        <select class="form-control form-control-sm" id="type_mvt" name="type_mvt" required>
                            <option value="">Sélectionner le type</option>
                            <option value="Entree">Entrée</option>
                            <option value="Sortie">Sortie</option>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="id_achat" class="small">Achat:</label>
                        <select class="form-control form-control-sm" id="id_achat" name="id_achat" required>
                            <option value="">Sélectionner l'achat</option>
                            <%
                                List<Achat> achats = (List<Achat>) request.getAttribute("achats");
                                if (achats != null) {
                                    for (Achat achat : achats) {
                            %>
                            <option value="<%= achat.getIdAchat() %>"><%= achat.getIdAchat() %> - <%= achat.getDateAchat() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group mb-3">
                        <label for="id_reparation_details" class="small">Réparation:</label>
                        <select class="form-control form-control-sm" id="id_reparation_details" name="id_reparation_details">
                            <option value="">Sélectionner la réparation</option>
                            <%
                                List<ReparationDetails> reparationDetails = (List<ReparationDetails>) request.getAttribute("reparationDetails");
                                if (reparationDetails != null) {
                                    for (ReparationDetails repair : reparationDetails) {
                            %>
                            <option value="<%= repair.getIdReparationDetails() %>"><%= repair.getIdReparationDetails() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
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
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter Mouvement</button>
                </form>

                <hr class="my-4">

                <!-- Liste des mouvements de stock -->
                <h3 class="h5 mb-3">Liste des Mouvements de Stock</h3>

                <div class="table-responsive">
                    <table class="table table-striped table-sm table-bordered">
                        <thead>
                        <tr>
                            <th>ID Mouvement</th>
                            <th>Date du Mouvement</th>
                            <th>Entrée</th>
                            <th>Sortie</th>
                            <th>Type de Mouvement</th>
                            <th>Achat</th>
                            <th>Réparation</th>
                            <th>Composant</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<MvtStock> mvtStockList = (List<MvtStock>) request.getAttribute("mvtStockList");
                            if (mvtStockList != null) {
                                for (MvtStock mvt : mvtStockList) {
                        %>
                        <tr>
                            <td><%= mvt.getIdMvtStock() %></td>
                            <td><%= mvt.getDateMouvement() %></td>
                            <td><%= mvt.getEntree() %></td>
                            <td><%= mvt.getSortie() %></td>
                            <td><%= mvt.getTypeMvt() %></td>
                            <td><%= mvt.getIdAchat() %></td>
                            <td><%= mvt.getIdReparationDetails() %></td>
                            <td><%= mvt.getIdComposant() %></td>
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
                            <td colspan="9" class="text-center">Aucun mouvement de stock trouvé</td>
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
