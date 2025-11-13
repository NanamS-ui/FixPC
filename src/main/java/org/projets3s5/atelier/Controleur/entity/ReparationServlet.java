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

@WebServlet("/ReparationServlet")
public class ReparationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des réparations
            List<Reparation> reparations = Reparation.getAll(connection);
            request.setAttribute("reparations", reparations);

            // Récupérer la liste des ordinateurs pour la liste déroulante
            List<Ordinateur> ordinateurs = Ordinateur.getAll(connection);
            request.setAttribute("ordinateurs", ordinateurs);


            request.getRequestDispatcher("/temp/back_Office/pages/aff/reparationList.jsp").forward(request, response);
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
        String dateReparation = request.getParameter("date_reparation");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String idOrdinateur = request.getParameter("id_ordinateur");

        Reparation reparation = new Reparation(null, dateReparation, description, status, idOrdinateur);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter la réparation dans la base de données
            reparation.insert(connection);
            request.setAttribute("message", "Réparation ajoutée avec succès !");

            // Récupérer la liste des réparations
            List<Reparation> reparations = Reparation.getAll(connection);
            request.setAttribute("reparations", reparations);

            // Récupérer la liste des ordinateurs pour la liste déroulante
            List<Ordinateur> ordinateurs = Ordinateur.getAll(connection);
            request.setAttribute("ordinateurs", ordinateurs);


            request.getRequestDispatcher("/temp/back_Office/pages/aff/reparationList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'ajout de la réparation : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
