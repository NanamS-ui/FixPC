package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.CombinedRetour;
import org.projets3s5.atelier.model.Composant;
import org.projets3s5.atelier.model.TypeOrdi;
import org.projets3s5.atelier.model.TypeRetour;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/CombinedServlet")
public class CombinedRetourServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnectionPostgre();


            List<Composant> composantList = Composant.getAll(connection);

            List<TypeRetour> typeRetourList = TypeRetour.getAll(connection);

            List<TypeOrdi> typeOrdis = TypeOrdi.getAll(connection);

            request.setAttribute("composantList", composantList);
            request.setAttribute("typeRetourList", typeRetourList);
            request.setAttribute("typeOrdisList", typeOrdis);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/temp/back_Office/pages/filtre/listRetour.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idComposant = request.getParameter("id_composant");
        String idTypeRetour = request.getParameter("id_type_retour");
        String idTypeOrdi = request.getParameter("id_type_ordi");

        if (idComposant == null || idTypeRetour == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        Connection connection = null;
        try {
            connection = DatabaseUtil.getConnectionPostgre();
            List<CombinedRetour> retourList = CombinedRetour.getRetourByComposantTypeRetour(connection, idComposant, idTypeRetour, idTypeOrdi);

            request.setAttribute("retourList", retourList);
            List<Composant> composantList = Composant.getAll(connection);

            // Retrieve list of typeRetour (assuming there's a method to get them)
            List<TypeRetour> typeRetourList = TypeRetour.getAll(connection);
            List<TypeOrdi> typeOrdis = TypeOrdi.getAll(connection);

            request.setAttribute("composantList", composantList);
            request.setAttribute("typeRetourList", typeRetourList);
            request.setAttribute("typeOrdisList", typeOrdis);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/temp/back_Office/pages/filtre/listRetour.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
        } finally {
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
