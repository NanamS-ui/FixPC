package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.TypeRetour;
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

@WebServlet("/TypeRetourServlet")
public class TypeRetourServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des types de retour
            List<TypeRetour> typeRetours = TypeRetour.getAll(connection);
            request.setAttribute("typeRetours", typeRetours);

            // Rediriger vers la page de la liste des types de retour
            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeRetourList.jsp").forward(request, response);
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
        String val = request.getParameter("val");

        TypeRetour typeRetour = new TypeRetour();
        typeRetour.setVal(val);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter le type de retour dans la base
            typeRetour.insert(connection);
            request.setAttribute("message", "Type de retour ajouté avec succès !");

            // Rafraîchir la liste des types de retour
            List<TypeRetour> typeRetours = TypeRetour.getAll(connection);
            request.setAttribute("typeRetours", typeRetours);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeRetourList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'opération : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
