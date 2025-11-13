package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Employe;
import org.projets3s5.atelier.model.Genre;
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

@WebServlet("/EmployeServlet")
public class EmployeServlet extends javax.servlet.http.HttpServlet {

    // Méthode pour traiter les requêtes GET
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des employés
            List<Employe> employes = Employe.getAll(connection);
            request.setAttribute("employes", employes);

            // Récupérer la liste des postes pour la liste déroulante
            List<Poste> postes = Poste.getAll(connection);
            request.setAttribute("postes", postes);

            List<Genre> genres = Genre.getAll(connection);
            request.setAttribute("genres", genres);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/employeList.jsp").forward(request, response);
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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String username = request.getParameter("username");
        String mdp = request.getParameter("mdp");
        String email = request.getParameter("email");
        String idPoste = request.getParameter("poste");
        String idGenre = request.getParameter("genre");

        Employe employe = new Employe(null, nom, prenom, username, mdp, email, idPoste, idGenre);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            employe.insert(connection);
            request.setAttribute("message", "Employé ajouté avec succès !");

            List<Employe> employes = Employe.getAll(connection);
            request.setAttribute("employes", employes);

            List<Poste> postes = Poste.getAll(connection);
            request.setAttribute("postes", postes);

            List<Genre> genres = Genre.getAll(connection);
            request.setAttribute("genres", genres);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/employeList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion de l'employé : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
