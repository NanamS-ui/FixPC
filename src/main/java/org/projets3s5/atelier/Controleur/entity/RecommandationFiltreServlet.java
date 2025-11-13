package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.RecommandationComposant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RecommandationFiltreServlet")
public class RecommandationFiltreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int mois = Integer.parseInt(request.getParameter("mois"));
            int annee = Integer.parseInt(request.getParameter("annee"));

            List<RecommandationComposant> recommandations = RecommandationComposant.getFiltreByMoisAnnee(mois, annee);

            request.setAttribute("recommandations", recommandations);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Une erreur s'est produite lors du filtrage des recommandations.");
        }

        request.getRequestDispatcher("/temp/back_Office/pages/filtre/recommandationsFiltre.jsp").forward(request, response);
    }
}
