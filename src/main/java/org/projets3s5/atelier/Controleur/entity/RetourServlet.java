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

@WebServlet("/RetourServlet")
public class RetourServlet extends HttpServlet {

    // Méthode pour gérer les requêtes GET et POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des retours depuis la base de données
            List<Retour> retours = Retour.getAll(connection);
            request.setAttribute("retours", retours);

            // Récupérer les types de retours
            List<TypeRetour> typeRetours = TypeRetour.getAll(connection);
            request.setAttribute("typeretours", typeRetours);

            // Récupérer les détails de réparation
            List<ReparationDetails> reparationDetails = ReparationDetails.getAll(connection);
            request.setAttribute("reparationdetails", reparationDetails);

            // Récupérer les composants
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            List<Client> clients = Client.getAll(connection);
            request.setAttribute("clients", clients);

            // Rediriger vers la page de la liste des retours
            request.getRequestDispatcher("/temp/back_Office/pages/aff/retourList.jsp").forward(request, response);
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des données : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Gérer la méthode GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Gérer la méthode POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        String idTypeRetour = request.getParameter("id_type_retour");
        String idReparationDetails = request.getParameter("id_reparation_details");
        String idComposant = request.getParameter("id_composant");
        String idClient = request.getParameter("id_client");
        String dateRetour = request.getParameter("date_retour");

        // Créer un objet Retour à partir des données de la requête
        Retour retour = new Retour();
        retour.setDescription(description);
        retour.setIdTypeRetour(idTypeRetour);
        retour.setIdReparationDetails(idReparationDetails);
        retour.setIdComposant(idComposant);
        retour.setIdClient(idClient);
        retour.setDateRetour(dateRetour);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter le retour dans la base
            retour.insert(connection);
            request.setAttribute("message", "Retour ajouté avec succès !");

            // Récupérer à nouveau la liste des retours, types de retours, détails de réparation et composants
            List<Retour> retours = Retour.getAll(connection);
            request.setAttribute("retours", retours);

            List<TypeRetour> typeRetours = TypeRetour.getAll(connection);
            request.setAttribute("typeretours", typeRetours);

            List<ReparationDetails> reparationDetails = ReparationDetails.getAll(connection);
            request.setAttribute("reparationdetails", reparationDetails);

            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            List<Client> clients = Client.getAll(connection);
            request.setAttribute("clients", clients);

            // Rediriger vers la page de la liste des retours
            request.getRequestDispatcher("/temp/back_Office/pages/aff/retourList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'opération : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
