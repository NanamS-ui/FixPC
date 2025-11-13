package org.projets3s5.atelier.controller;

import org.projets3s5.atelier.model.Composant;
import org.projets3s5.atelier.model.Ordinateur;
import org.projets3s5.atelier.model.Probleme;
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

@WebServlet("/filtre")
public class FiltreProblemeServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnectionPostgre();
            List<Probleme> composants = Probleme.getAll(connection);

            request.setAttribute("problemes", composants);

            request.getRequestDispatcher("/temp/back_Office/pages/filtre/filtre.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des composants :" +e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        finally {
            try {
                if (connection != null){
                    connection.close();
                }
            }
            catch (Exception e){
                request.setAttribute("error", "Une erreur est survenue lors du la fermeture connection :" +e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnectionPostgre();
            String designation = request.getParameter("probleme");
            List<Probleme> composants = Probleme.getAll(connection);

            request.setAttribute("problemes", composants);
            List<Ordinateur> list = Ordinateur.getComputersWithProblem(connection, designation);
            request.setAttribute("listOrdi", list);
            request.getRequestDispatcher("/temp/back_Office/pages/filtre/filtre.jsp").forward(request, response);
        } catch (Exception exception) {
            request.setAttribute("error", exception.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception exception) {
                System.out.println("Error closing connection: " + exception.getMessage());
            }
        }
    }
}
