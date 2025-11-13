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

@WebServlet("/OrdinateurServlet")
public class OrdinateurServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des employés
            List<Ordinateur> employes = Ordinateur.getAll(connection);
            request.setAttribute("ordinateurs", employes);

            // Récupérer la liste des postes pour la liste déroulante
            List<Systeme> postes = Systeme.getAll(connection);
            request.setAttribute("systems", postes);

            List<TypeOrdi> typeOrdis = TypeOrdi.getAll(connection);
            request.setAttribute("typesOrdis", typeOrdis);

            List<Client> clients = Client.getAll(connection);
            request.setAttribute("clients", clients);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/ordinateurList.jsp").forward(request, response);
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
        String nom = request.getParameter("marque");
        String prenom = request.getParameter("modele");
        String username = request.getParameter("id_systeme");
        String mdp = request.getParameter("id_type_ordi");
        String email = request.getParameter("id_client");

        Ordinateur employe = new Ordinateur(null, nom, prenom, username, mdp, email);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter l'employé dans la base de données
            employe.insert(connection);
            request.setAttribute("message", "Ordinateur ajouté avec succès !");

            List<Ordinateur> employes = Ordinateur.getAll(connection);
            request.setAttribute("ordinateurs", employes);

            // Récupérer la liste des postes pour la liste déroulante
            List<Systeme> postes = Systeme.getAll(connection);
            request.setAttribute("systems", postes);

            List<TypeOrdi> typeOrdis = TypeOrdi.getAll(connection);
            request.setAttribute("typesOrdis", typeOrdis);

            List<Client> clients = Client.getAll(connection);
            request.setAttribute("clients", clients);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/ordinateurList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion de l'ordinateur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
