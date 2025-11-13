package org.projets3s5.atelier.controller.entity;

import org.projets3s5.atelier.model.TypeOrdi;
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

@WebServlet("/TypeOrdiServlet")
public class TypeOrdiServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Récupérer la liste des TypeOrdi
            List<TypeOrdi> typeOrdis = TypeOrdi.getAll(connection);
            request.setAttribute("typeOrdis", typeOrdis);
            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeOrdiList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des types d'ordis : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Méthode pour traiter la requête POST pour l'insertion d'un nouveau TypeOrdi
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String val = request.getParameter("val");

        TypeOrdi typeOrdi = new TypeOrdi(null, val);

        try (Connection connection = DatabaseUtil.getConnectionPostgre()) {
            // Insérer le nouveau TypeOrdi dans la base de données
            typeOrdi.insert(connection);

            // Récupérer à nouveau la liste des TypeOrdi après insertion
            List<TypeOrdi> typeOrdis = TypeOrdi.getAll(connection);

            request.setAttribute("message", "TypeOrdi ajouté avec succès !");
            request.setAttribute("typeOrdis", typeOrdis);

            // Rediriger vers la page de présentation avec la liste mise à jour
            request.getRequestDispatcher("/temp/back_Office/pages/aff/typeOrdiList.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Une erreur est survenue lors de l'insertion du TypeOrdi : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
