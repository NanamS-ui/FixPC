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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CommissionServlet")
public class CommissionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;

        // Récupération des paramètres pour les dates de début et de fin
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");

        if (dateDebut == null || dateFin == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Les paramètres dateDebut et dateFin sont requis");
            return;
        }

        try {
            connection = DatabaseUtil.getConnectionPostgre();

            List<Commission> commissionList = Commission.getCommissionsByTechnicianAndPeriod(connection, dateDebut, dateFin);

            request.setAttribute("commissionList", commissionList);
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/temp/back_Office/pages/filtre/commissionList.jsp");
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
