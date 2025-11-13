package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Commission;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CommissionGenreServlet")
public class CommissionGenreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;

        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        double seuil = Double.parseDouble(request.getParameter("seuil"));

        if (dateDebut == null || dateFin == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les paramètres dateDebut et dateFin sont requis");
            return;
        }

        try {
            connection = DatabaseUtil.getConnectionPostgre();

            List<Commission> commissionList = Commission.getCommissionsByTechnicianGenreAndPeriod(connection, dateDebut, dateFin, seuil);

            request.setAttribute("commissionList", commissionList);
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/temp/back_Office/pages/filtre/commissionGenreList.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur s'est produite avec la base de données");
        } finally {
            // Fermeture de la connexion
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
