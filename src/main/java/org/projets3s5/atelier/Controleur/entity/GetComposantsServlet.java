package org.projets3s5.atelier.controller.entity;

import com.google.gson.Gson;
import org.projets3s5.atelier.model.Composant;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/GetComposantsServlet")
public class GetComposantsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTypeReparation = request.getParameter("id_type_reparation");

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            if ("TYR00002".equals(idTypeReparation)) {
                // Récupérer la liste des composants
                List<Composant> composantList = Composant.getAll(connection);

                // Convertir la liste en JSON
                String jsonResponse = new Gson().toJson(composantList);

                // Envoyer la réponse JSON
                response.setContentType("application/json");
                response.getWriter().write(jsonResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

