package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Client;
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

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des clients après insertion
            List<Client> clients = Client.getAll(connection);
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/client.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des clients : " + e.getMessage());
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
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String dateInscription = request.getParameter("dateInscription");

        Client client = new Client(null, nom, telephone, email, dateInscription);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            client.insert(connection);

            List<Client> clients = Client.getAll(connection);

            request.setAttribute("message", "Insertion client réussie");
            request.setAttribute("clients", clients);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/client.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion du client : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
