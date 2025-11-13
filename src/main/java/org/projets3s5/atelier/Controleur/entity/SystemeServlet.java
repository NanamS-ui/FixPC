package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Systeme;
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

@WebServlet("/SystemeServlet")
public class SystemeServlet extends HttpServlet {

    // Méthode pour traiter les requêtes GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des systèmes
            List<Systeme> systemes = Systeme.getAll(connection);
            request.setAttribute("systemes", systemes);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/systemeList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des systèmes : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Méthode pour gérer les requêtes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Méthode pour traiter la requête POST (ajout, mise à jour, suppression)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
                // Ajouter un nouveau système
                String val = request.getParameter("val");

                Systeme systeme = new Systeme(null, val);
                systeme.insert(connection);

                // Récupérer la liste mise à jour des systèmes
                List<Systeme> systemes = Systeme.getAll(connection);
                request.setAttribute("systemes", systemes);
                request.setAttribute("message", "Système ajouté avec succès");

                request.getRequestDispatcher("/temp/back_Office/pages/aff/systemeList.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la gestion du système : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
