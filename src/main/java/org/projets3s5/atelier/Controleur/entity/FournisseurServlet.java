package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Fournisseur;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/FournisseurServlet")
public class FournisseurServlet extends javax.servlet.http.HttpServlet {

    // Traitement de la requête GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des fournisseurs
            List<Fournisseur> fournisseurs = Fournisseur.getAll(connection);
            request.setAttribute("fournisseurs", fournisseurs);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/fournisseurList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des fournisseurs : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Méthode pour le traitement des requêtes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Méthode pour traiter la requête POST pour l'insertion d'un nouveau Fournisseur
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val = request.getParameter("val");

        Fournisseur fournisseur = new Fournisseur(null, val);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
                fournisseur.insert(connection);
                request.setAttribute("message", "Fournisseur ajouté avec succès !");

            List<Fournisseur> fournisseurs = Fournisseur.getAll(connection);
            request.setAttribute("fournisseurs", fournisseurs);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/fournisseurList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion ou de la mise à jour du fournisseur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
