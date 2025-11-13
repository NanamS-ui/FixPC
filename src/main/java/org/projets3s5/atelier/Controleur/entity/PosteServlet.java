package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Poste;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/PosteServlet")
public class PosteServlet extends javax.servlet.http.HttpServlet {

    // Méthode pour traiter les requêtes GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des postes
            List<Poste> postes = Poste.getAll(connection);
            request.setAttribute("postes", postes);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/posteList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des postes : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val = request.getParameter("val");
        String salaireParam = request.getParameter("salaire");
        double salaire;

        try {
            salaire = Double.parseDouble(salaireParam);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Salaire invalide. Veuillez entrer un nombre valide.");
            processRequest(request, response);
            return;
        }

        Poste poste = new Poste(null, val, salaire);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            poste.insert(connection);
            request.setAttribute("message", "Poste ajouté avec succès !");

            // Mettre à jour la liste des postes pour affichage
            List<Poste> postes = Poste.getAll(connection);
            request.setAttribute("postes", postes);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/posteList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion du poste : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
