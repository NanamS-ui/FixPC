package org.projets3s5.atelier.controller.entity;

import javax.servlet.http.HttpServlet;
import org.projets3s5.atelier.model.*;
import org.projets3s5.atelier.utils.DatabaseUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AchatServlet")
public class AchatServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des achats
            List<Achat> achats = Achat.getAll(connection);
            request.setAttribute("achats", achats);

            // Récupérer la liste des fournisseurs pour la liste déroulante
            List<Fournisseur> fournisseurs = Fournisseur.getAll(connection);
            request.setAttribute("fournisseurs", fournisseurs);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/achatList.jsp").forward(request, response);
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
        String dateAchat = request.getParameter("date_achat");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        double prixUnitaire = Double.parseDouble(request.getParameter("prix_unitaire"));
        String idFournisseur = request.getParameter("id_fournisseur");
        String idComposant = request.getParameter("id_composant");

        Achat achat = new Achat(null, dateAchat, quantite, prixUnitaire, idFournisseur, idComposant);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter l'achat dans la base de données
            achat.insert(connection);
            request.setAttribute("message", "Achat ajouté avec succès !");

            // Récupérer la liste des achats
            List<Achat> achats = Achat.getAll(connection);
            request.setAttribute("achats", achats);

            // Récupérer la liste des fournisseurs pour la liste déroulante
            List<Fournisseur> fournisseurs = Fournisseur.getAll(connection);
            request.setAttribute("fournisseurs", fournisseurs);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/achatList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'ajout de l'achat : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
