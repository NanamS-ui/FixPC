<%@ page import="org.projets3s5.atelier.model.Achat" %>
<%@ page import="org.projets3s5.atelier.model.Fournisseur" %>
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
        if (message != null) { %> <div class="alert alert-success mb-3"> <%= message %> </div> <% } %>
                    <h2 class="h4">Ajouter un Achat</h2>
                    <p class="mb-4">Veuillez entrer les informations de l'achat :</p>

                    <!-- Formulaire d'ajout d'un achat -->
                    <form action="<%= request.getContextPath() %>/AchatServlet" method="POST">
                        <div class="form-group mb-3">
                            <label for="date_achat" class="small">Date d'Achat:</label>
                            <input type="date" class="form-control form-control-sm" id="date_achat" name="date_achat" required>
                        </div>
                        <div class="form-group mb-3">
                            <label for="quantite" class="small">Quantité:</label>
                            <input type="number" class="form-control form-control-sm" id="quantite" name="quantite" required>
                        </div>
                        <div class="form-group mb-3">
                            <label for="prix_unitaire" class="small">Prix Unitaire:</label>
                            <input type="number" step="0.01" class="form-control form-control-sm" id="prix_unitaire" name="prix_unitaire" required>
                        </div>
                        <div class="form-group mb-3">
                            <label for="id_fournisseur" class="small">Fournisseur:</label>
                            <select class="form-control form-control-sm" id="id_fournisseur" name="id_fournisseur" required>
                                <option value="">Sélectionner le fournisseur</option>
                                <%
                                    List<Fournisseur> fournisseurs = (List<Fournisseur>) request.getAttribute("fournisseurs");
                                    if (fournisseurs != null) {
                                        for (Fournisseur fournisseur : fournisseurs) {
                                %>
                                <option value="<%= fournisseur.getIdFournisseur() %>"><%= fournisseur.getVal() %></option>
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
                        <button type="submit" class="btn btn-primary btn-sm">Ajouter Achat</button>
                    </form>

                    <hr class="my-4">

                    <!-- Liste des achats -->
                    <h3 class="h5 mb-3">Liste des Achats</h3>

                    <div class="table-responsive">
                        <table class="table table-striped table-sm table-bordered">
                            <thead>
                            <tr>
                                <th>ID Achat</th>
                                <th>Date d'Achat</th>
                                <th>Quantité</th>
                                <th>Prix Unitaire</th>
                                <th>Fournisseur</th>
                                <th>Composant</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                List<Achat> achats = (List<Achat>) request.getAttribute("achats");
                                if (achats != null) {
                                    for (Achat achat : achats) {
                            %>
                            <tr>
                                <td><%= achat.getIdAchat() %></td>
                                <td><%= achat.getDateAchat() %></td>
                                <td><%= achat.getQuantite() %></td>
                                <td><%= achat.getPrixUnitaire() %></td>
                                <td><%= achat.getIdFournisseur() %></td>
                                <td><%= achat.getIdComposant() %></td>
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
                                <td colspan="7" class="text-center">Aucun achat trouvé</td>
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
