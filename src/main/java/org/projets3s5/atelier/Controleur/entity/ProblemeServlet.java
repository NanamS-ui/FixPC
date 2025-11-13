package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Probleme;
import org.projets3s5.atelier.model.TypeProbleme;
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

@WebServlet("/ProblemeServlet")
public class ProblemeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des problèmes
            List<Probleme> problemes = Probleme.getAll(connection);
            request.setAttribute("problemes", problemes);

            // Récupérer les types de problèmes
            List<TypeProbleme> typeProblemes = TypeProbleme.getAll(connection);
            request.setAttribute("typeproblemes", typeProblemes);

            // Rediriger vers la page de la liste des problèmes
            request.getRequestDispatcher("/temp/back_Office/pages/aff/problemeList.jsp").forward(request, response);
        } catch (SQLException e) {
            // Gérer les erreurs SQL
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
        String description = request.getParameter("description");
        String id_type_probleme = request.getParameter("id_type_probleme");


        Probleme probleme = new Probleme();
        probleme.setDescription(description);
        probleme.setIdTypeProbleme(id_type_probleme);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter le problème dans la base
            probleme.insert(connection);
            request.setAttribute("message", "Problème ajouté avec succès !");

            List<Probleme> problemes = Probleme.getAll(connection);
            request.setAttribute("problemes", problemes);

            List<TypeProbleme> typeProblemes = TypeProbleme.getAll(connection);
            request.setAttribute("typeproblemes", typeProblemes);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/problemeList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'opération : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
