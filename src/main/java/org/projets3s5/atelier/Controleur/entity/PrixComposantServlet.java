package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Composant;
import org.projets3s5.atelier.model.Prix_Composant;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@WebServlet("/PrixComposantServlet")
public class PrixComposantServlet extends javax.servlet.http.HttpServlet {

    // Méthode pour traiter les requêtes GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des prix composants
            List<Prix_Composant> prixComposants = Prix_Composant.getAll(connection);
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("prixComposants", prixComposants);
            request.setAttribute("composants", composants);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/prixComposantList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des prix composants : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prixParam = request.getParameter("prix");
        String datePrixParam = request.getParameter("datePrix");
        String idComposant = request.getParameter("id_composant");

        double prixComposant;
        Date datePrix;

        try {
            prixComposant = Double.parseDouble(prixParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Prix composant invalide. Veuillez entrer un nombre valide.");
            processRequest(request, response);
            return;
        }

        try {
            datePrix = Date.valueOf(datePrixParam);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Date invalide. Veuillez entrer une date au format YYYY-MM-DD.");
            processRequest(request, response);
            return;
        }

        Prix_Composant prixComposantObj = new Prix_Composant(null, prixComposant, datePrix, idComposant);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            prixComposantObj.create(connection);
            request.setAttribute("message", "Prix composant ajouté avec succès !");

            // Mettre à jour la liste des prix composants pour affichage
            List<Prix_Composant> prixComposants = Prix_Composant.getAll(connection);
            request.setAttribute("prixComposants", prixComposants);
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/prixComposantList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion du prix composant : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
