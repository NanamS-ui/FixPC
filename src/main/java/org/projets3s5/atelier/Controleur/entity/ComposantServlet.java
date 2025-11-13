package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Composant;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ComposantServlet")
public class ComposantServlet extends javax.servlet.http.HttpServlet {

    // Traitement de la requête GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des composants
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/composantList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des composants : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Méthode pour le traitement des requêtes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Méthode pour traiter la requête POST pour l'insertion d'un nouveau Composant
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String img = request.getParameter("img");

        Composant composant = new Composant(null, nom,  img);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Insérer le nouveau composant dans la base de données
            composant.insert(connection);

            // Récupérer à nouveau la liste des composants après insertion
            List<Composant> composants = Composant.getAll(connection);

            request.setAttribute("message", "Composant ajouté avec succès !");
            request.setAttribute("composants", composants);

            // Rediriger vers la page de présentation avec la liste mise à jour
            request.getRequestDispatcher("/temp/back_Office/pages/aff/composantList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion du composant : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
