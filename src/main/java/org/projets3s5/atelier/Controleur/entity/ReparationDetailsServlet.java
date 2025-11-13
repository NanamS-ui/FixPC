package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.*;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ReparationDetailsServlet")
public class ReparationDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des détails de réparation
            List<ReparationDetails> detailsList = ReparationDetails.getAll(connection);
            request.setAttribute("detailsList", detailsList);

            // Récupérer la liste des réparations pour la liste déroulante
            List<Reparation> reparations = Reparation.getAll(connection);
            request.setAttribute("reparations", reparations);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            // Récupérer la liste des problèmes pour la liste déroulante
            List<Probleme> problemes = Probleme.getAll(connection);
            request.setAttribute("problemes", problemes);

            List<Employe> employes= Employe.getAll(connection);
            request.setAttribute("employes", employes);


            // Récupérer la liste des types de réparations pour la liste déroulante
            List<TypeReparation> typeReparations = TypeReparation.getAll(connection);
            request.setAttribute("typeReparations", typeReparations);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/reparationDetailsList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des données : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String quantiteUtilisee = request.getParameter("quantite_utilisee");
        String idTypeReparation = request.getParameter("id_type_reparation");
        String idComposant = request.getParameter("id_composant");
        String idProbleme = request.getParameter("id_probleme");
        String idReparation = request.getParameter("id_reparation");
        String idEmploye = request.getParameter("id_employe");

        // Vérification et conversion de la quantité utilisée
        int quantiteUtiliseeInt = 0; // Par défaut, 0 si non fourni
        if (quantiteUtilisee != null && !quantiteUtilisee.isEmpty()) {
            try {
                quantiteUtiliseeInt = Integer.parseInt(quantiteUtilisee);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "La quantité utilisée n'est pas valide.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
        }



        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            Prix_Composant prixReparation = Prix_Composant.getDernierPrix(connection, idComposant);
            ReparationDetails reparationDetails;
                reparationDetails = new ReparationDetails(
                        null,
                        prixReparation.getPrixComposant(),
                        quantiteUtiliseeInt, // Utilisation de la valeur par défaut ou convertie
                        idTypeReparation,
                        null,
                        idProbleme,
                        idReparation,
                        idEmploye
                );
            reparationDetails.insert(connection);
            request.setAttribute("message", "Détail de réparation ajouté avec succès !");

            // Récupérer la liste des détails de réparation
            List<ReparationDetails> detailsList = ReparationDetails.getAll(connection);
            request.setAttribute("detailsList", detailsList);

            // Récupérer la liste des réparations pour la liste déroulante
            List<Reparation> reparations = Reparation.getAll(connection);
            request.setAttribute("reparations", reparations);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            // Récupérer la liste des problèmes pour la liste déroulante
            List<Probleme> problemes = Probleme.getAll(connection);
            request.setAttribute("problemes", problemes);

            // Récupérer la liste des types de réparations pour la liste déroulante
            List<TypeReparation> typeReparations = TypeReparation.getAll(connection);
            request.setAttribute("typeReparations", typeReparations);
            List<Employe> employes= Employe.getAll(connection);
            request.setAttribute("employes", employes);

            // Rediriger vers la page de la liste des détails de réparation
            request.getRequestDispatcher("/temp/back_Office/pages/aff/reparationDetailsList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'ajout du détail de réparation : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }



}
