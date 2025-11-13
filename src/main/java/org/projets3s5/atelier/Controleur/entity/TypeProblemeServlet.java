package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.TypeProbleme;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/TypeProblemeServlet")
public class TypeProblemeServlet extends javax.servlet.http.HttpServlet {

    // Traitement de la requête GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des TypeProbleme
            List<TypeProbleme> typeProblemes = TypeProbleme.getAll(connection);
            request.setAttribute("typeProblemes", typeProblemes);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeProblemeList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des types de problèmes : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Méthode pour le traitement des requêtes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Méthode pour traiter la requête POST pour l'insertion d'un nouveau TypeProbleme
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val = request.getParameter("val");

        TypeProbleme typeProbleme = new TypeProbleme(null, val);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Insérer le nouveau TypeProbleme dans la base de données
            typeProbleme.insert(connection);

            // Récupérer à nouveau la liste des TypeProbleme après insertion
            List<TypeProbleme> typeProblemes = TypeProbleme.getAll(connection);

            request.setAttribute("message", "Type de problème ajouté avec succès !");
            request.setAttribute("typeProblemes", typeProblemes);

            // Rediriger vers la page de présentation avec la liste mise à jour
            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeProblemeList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion du type de problème : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
