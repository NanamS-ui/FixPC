package org.projets3s5.atelier.Controleur;

import org.projets3s5.atelier.model.Composant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/composants")
public class ComposantServlet extends HttpServlet {

    // Méthode qui traite la requête
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Connexion à la base de données
        Connection connection = null;  // Assurez-vous d'avoir une connexion valide ici

        try {
            // Récupération de la liste des composants
            List<Composant> composants = Composant.getAll(connection);

            // Ajouter la liste des composants à l'objet requête
            request.setAttribute("composants", composants);

            // Dispatch vers la JSP pour afficher les composants
            // Nous utilisons request.getRequestDispatcher pour rediriger vers une page JSP
            request.getRequestDispatcher("/WEB-INF/afficherComposants.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
<<<<<<< Updated upstream:src/main/java/org/projets3s5/atelier/Controleur/ComposantServlet.java
            // Si une exception se produit, envoyer une erreur
            request.setAttribute("error", "Une erreur est survenue lors de la récupération des composants.");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
=======
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
>>>>>>> Stashed changes:src/main/java/org/projets3s5/atelier/controller/ComposantServlet.java
    }

    // Méthode doGet pour gérer les requêtes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    // Méthode doPost pour gérer les requêtes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
