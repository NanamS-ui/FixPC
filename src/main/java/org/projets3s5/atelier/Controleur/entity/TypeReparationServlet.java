package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.TypeReparation;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/TypeReparationServlet")
public class TypeReparationServlet extends javax.servlet.http.HttpServlet {

    // Traitement de la requête GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des types de réparation
            List<TypeReparation> typeReparations = TypeReparation.getAll(connection);
            request.setAttribute("typeReparations", typeReparations);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeReparationList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des types de réparation : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Méthode pour le traitement des requêtes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Méthode pour traiter la requête POST pour l'insertion d'un nouveau TypeReparation
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val = request.getParameter("val");

        TypeReparation typeReparation = new TypeReparation(null, val);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            typeReparation.insert(connection);
            request.setAttribute("message", "Type de réparation ajouté avec succès !");

            List<TypeReparation> typeReparations = TypeReparation.getAll(connection);
            request.setAttribute("typeReparations", typeReparations);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeReparationList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion ou de la mise à jour du type de réparation : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
