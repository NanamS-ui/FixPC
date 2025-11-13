package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.Stock;
import org.projets3s5.atelier.model.Composant;
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

@WebServlet("/StockServlet")
public class StockServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des stocks
            List<Stock> stockList = Stock.getAll(connection);
            request.setAttribute("stockList", stockList);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/stockList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des stocks : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantiteDisponible = Integer.parseInt(request.getParameter("quantite_disponible"));
        String idComposant = request.getParameter("id_composant");

        Stock stock = new Stock(null, quantiteDisponible, idComposant);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Ajouter le stock dans la base de données
            stock.insert(connection);
            request.setAttribute("message", "Stock ajouté avec succès !");

            // Récupérer la liste des stocks mise à jour
            List<Stock> stockList = Stock.getAll(connection);
            request.setAttribute("stockList", stockList);

            // Récupérer la liste des composants pour la liste déroulante
            List<Composant> composants = Composant.getAll(connection);
            request.setAttribute("composants", composants);

            request.getRequestDispatcher("/temp/back_Office/pages/aff/stockList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'ajout du stock : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
