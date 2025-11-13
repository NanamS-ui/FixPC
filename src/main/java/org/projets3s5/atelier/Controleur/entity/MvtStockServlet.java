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

@WebServlet("/MvtStockServlet")
public class MvtStockServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des mouvements de stock
            List<MvtStock> mvtStockList = MvtStock.getAll(connection);
            request.setAttribute("mvtStockList", mvtStockList);

            // Récupérer la liste des achats pour la liste déroulante
            List<Achat> achats = Achat.getAll(connection);
            request.setAttribute("achats", achats);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            // Récupérer la liste des réparations pour la liste déroulante
            List<ReparationDetails> reparationDetails = ReparationDetails.getAll(connection);
            request.setAttribute("reparationDetails", reparationDetails);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/mvtStockList.jsp").forward(request, response);
        } catch (SQLException e) {
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
        String dateMouvement = request.getParameter("date_mouvement");
        int entree = Integer.parseInt(request.getParameter("entree"));
        int sortie = Integer.parseInt(request.getParameter("sortie"));
        String typeMvt = request.getParameter("type_mvt");
        String idAchat = request.getParameter("id_achat");
        String idReparationDetails = request.getParameter("id_reparation_details");
        String idComposant = request.getParameter("id_composant");

        MvtStock mvtStock = new MvtStock(null, dateMouvement, entree, sortie, typeMvt, idAchat, idReparationDetails, idComposant);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter le mouvement de stock dans la base de données
            mvtStock.insert(connection);
            request.setAttribute("message", "Mouvement de stock ajouté avec succès !");

            // Récupérer la liste des mouvements de stock mise à jour
            List<MvtStock> mvtStockList = MvtStock.getAll(connection);
            request.setAttribute("mvtStockList", mvtStockList);

            // Récupérer la liste des achats pour la liste déroulante
            List<Achat> achats = Achat.getAll(connection);
            request.setAttribute("achats", achats);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            // Récupérer la liste des réparations pour la liste déroulante
            List<ReparationDetails> reparationDetails = ReparationDetails.getAll(connection);
            request.setAttribute("reparationDetails", reparationDetails);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/mvtStockList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'ajout du mouvement de stock : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
