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

@WebServlet("/historiquePrix")
public class HistoriquePrixServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idComposant = request.getParameter("id_composant");
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            List<Composant> problemes = Composant.getAll(connection);
            request.setAttribute("composants", problemes);

            List<Prix_Composant> typeProblemes = Prix_Composant.getPrixHistoriqueByComposantId(connection, idComposant);
            request.setAttribute("historiques", typeProblemes);

            request.getRequestDispatcher("/temp/back_Office/pages/filtre/historiqueList.jsp").forward(request, response);
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des données : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idComposant = request.getParameter("id_composant");
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            List<Composant> problemes = Composant.getAll(connection);
            request.setAttribute("composants", problemes);

            List<Prix_Composant> typeProblemes = Prix_Composant.getPrixHistoriqueByComposantId(connection, idComposant);
            request.setAttribute("historiques", typeProblemes);

            request.getRequestDispatcher("/temp/back_Office/pages/filtre/historiqueList.jsp").forward(request, response);
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des données : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
