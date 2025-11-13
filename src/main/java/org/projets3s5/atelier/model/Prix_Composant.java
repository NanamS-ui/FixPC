package org.projets3s5.atelier.model;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Prix_Composant {
    public String idPrix;
    public double prixComposant;
    public Date datePrix;
    public String idComposant;
    public Composant composant;

    // Constructeur
    public Prix_Composant(String idPrix, double prixComposant, Date datePrix, String idComposant) {
        this.idPrix = idPrix;
        this.prixComposant = prixComposant;
        this.datePrix = datePrix;
        this.idComposant = idComposant;
    }

    public Prix_Composant(String idPrix, double prixComposant, Date datePrix, String idComposant, Composant composant) {
        this.idPrix = idPrix;
        this.prixComposant = prixComposant;
        this.datePrix = datePrix;
        this.idComposant = idComposant;
        this.composant = composant;
    }

    // Getters et Setters
    public String getIdPrix() {
        return idPrix;
    }

    public void setIdPrix(String idPrix) {
        this.idPrix = idPrix;
    }

    public double getPrixComposant() {
        return prixComposant;
    }

    public void setPrixComposant(double prixComposant) {
        this.prixComposant = prixComposant;
    }

    public Date getDatePrix() {
        return datePrix;
    }

    public void setDatePrix(Date datePrix) {
        this.datePrix = datePrix;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    @Override
    public String toString() {
        return "Prix_Composant{" +
                "idPrix='" + idPrix + '\'' +
                ", prixComposant=" + prixComposant +
                ", datePrix=" + datePrix +
                ", idComposant='" + idComposant + '\'' +
                '}';
    }

    public Composant getComposant() {
        return composant;
    }

    public void setComposant(Composant composant) {
        this.composant = composant;
    }

    // Fonction pour obtenir tous les prix composants
    public static List<Prix_Composant> getAll(Connection connection) throws SQLException {
        List<Prix_Composant> prixComposants = new ArrayList<>();
        String sql = "SELECT id_prix, prix_composant, date_prix, id_composant FROM Prix_Composant";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String idPrix = resultSet.getString("id_prix");
                double prixComposant = resultSet.getDouble("prix_composant");
                Date datePrix = resultSet.getDate("date_prix");
                String idComposant = resultSet.getString("id_composant");

                prixComposants.add(new Prix_Composant(idPrix, prixComposant, datePrix, idComposant));
            }
        }

        return prixComposants;
    }

    // Fonction pour obtenir un prix composant par son ID
    public static Prix_Composant getById(Connection connection, String idPrix) throws SQLException {
        String sql = "SELECT id_prix, prix_composant, date_prix, id_composant FROM Prix_Composant WHERE id_prix = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idPrix);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    double prixComposant = resultSet.getDouble("prix_composant");
                    Date datePrix = resultSet.getDate("date_prix");
                    String idComposant = resultSet.getString("id_composant");

                    return new Prix_Composant(idPrix, prixComposant, datePrix, idComposant);
                }
            }
        }

        return null;  // Si aucun résultat n'est trouvé
    }

    // Fonction pour créer un nouveau prix composant
    public void create(Connection connection) throws SQLException {
        String sql = "INSERT INTO Prix_Composant (prix_composant, date_prix, id_composant) VALUES ( ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, this.prixComposant);
            statement.setDate(2, this.datePrix);
            statement.setString(3, this.idComposant);

            statement.executeUpdate();  // Exécute l'insertion
        }
    }

    // Fonction pour mettre à jour un prix composant
    public boolean update(Connection connection, Prix_Composant prixComposant) throws SQLException {
        String sql = "UPDATE Prix_Composant SET prix_composant = ?, date_prix = ?, id_composant = ? WHERE id_prix = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, prixComposant.getPrixComposant());
            statement.setDate(2, prixComposant.getDatePrix());
            statement.setString(3, prixComposant.getIdComposant());
            statement.setString(4, prixComposant.getIdPrix());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Fonction pour supprimer un prix composant
    public boolean delete(Connection connection, String idPrix) throws SQLException {
        String sql = "DELETE FROM Prix_Composant WHERE id_prix = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idPrix);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Fonction pour obtenir l'historique des prix d'un composant donné
    public static List<Prix_Composant> getPrixHistoriqueByComposantId(Connection connection, String idComposant) throws SQLException {
        List<Prix_Composant> historiquePrix = new ArrayList<>();

        // Ajouter un tri par date décroissante pour obtenir l'historique des prix dans l'ordre du plus récent au plus ancien
        String sql = "SELECT * FROM View_Prix_Composant WHERE id_composant = ? ORDER BY date_prix DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idComposant);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String idPrix = resultSet.getString("id_prix");
                    double prixComposant = resultSet.getDouble("prix_composant");
                    Date datePrix = resultSet.getDate("date_prix");
                    String composantId = resultSet.getString("id_composant");
                    Composant composant1 = new Composant(
                            resultSet.getString("id_composant"),
                            resultSet.getString("composant_val"),
                            resultSet.getString("composant_img")
                    );

                    // Ajouter le prix historique à la liste
                    historiquePrix.add(new Prix_Composant(idPrix, prixComposant, datePrix, composantId, composant1));
                }
            }
        }

        return historiquePrix;
    }


    public static Prix_Composant getDernierPrix(Connection connection, String idComposant) throws SQLException {
        Prix_Composant prixComposant = null;
        String sql = "SELECT id_prix, prix_composant, date_prix, id_composant, composant_val, composant_img " +
                "FROM View_Prix_Composant " +
                "WHERE id_composant = ? " +
                "ORDER BY date_prix DESC LIMIT 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idComposant);  // Paramètre idComposant

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String idPrix = resultSet.getString("id_prix");
                    double prixComposantValue = resultSet.getDouble("prix_composant");
                    Date datePrix = resultSet.getDate("date_prix");
                    String composantId = resultSet.getString("id_composant");
                    Composant composant1 = new Composant(resultSet.getString("id_composant"), resultSet.getString("composant_val"), resultSet.getString("composant_img"));

                    // Création de l'objet Prix_Composant
                    prixComposant = new Prix_Composant(idPrix, prixComposantValue, datePrix, composantId, composant1);
                }
            }
        }

        return prixComposant;
    }


}
