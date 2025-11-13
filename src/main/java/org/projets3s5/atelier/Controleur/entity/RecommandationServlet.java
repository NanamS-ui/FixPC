package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Recommandation;
import org.projets3s5.atelier.model.Composant;
import org.projets3s5.atelier.utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/RecommandationServlet")
public class RecommandationServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            List<Recommandation> recommandations = Recommandation.getAll(connection);
            request.setAttribute("recommandations", recommandations);

            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/recommandationList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Erreur lors de la récupération des données : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateRecommandation = request.getParameter("dateRecommandation"); // Valeur de type "yyyy-MM-dd"
        String idComposant = request.getParameter("id_composant");

        int mois = 0;
        int annee = 0;
        if (dateRecommandation != null && !dateRecommandation.isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(dateRecommandation);
                mois = date.getMonthValue();
                annee = date.getYear(); // Année
            } catch (DateTimeParseException e) {
                request.setAttribute("error", "La date fournie est invalide.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
        }

        // Créer une nouvelle recommandation
        Recommandation recommandation = new Recommandation();
        recommandation.setMois(mois);
        recommandation.setAnnee(annee);
        recommandation.setIdComposant(idComposant);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            recommandation.insert(connection);
            List<Recommandation> recommandations = Recommandation.getAll(connection);
            request.setAttribute("recommandations", recommandations);

            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/recommandationList.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'enregistrement de la recommandation.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
