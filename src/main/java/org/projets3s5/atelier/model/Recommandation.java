package org.projets3s5.atelier.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Recommandation {
    private String idRecommandation;
    private int mois;
    private int annee;
    private String idComposant;

    // Constructeurs
    public Recommandation() {
    }

    public Recommandation(String idRecommandation, int mois, int annee, String idComposant) {
        this.idRecommandation = idRecommandation;
        this.mois = mois;
        this.annee = annee;
        this.idComposant = idComposant;
    }

    // Getters et Setters
    public String getIdRecommandation() {
        return idRecommandation;
    }

    public void setIdRecommandation(String idRecommandation) {
        this.idRecommandation = idRecommandation;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Recommandation{" +
                "idRecommandation='" + idRecommandation + '\'' +
                ", mois=" + mois +
                ", annee=" + annee +
                ", idComposant='" + idComposant + '\'' +
                '}';
    }

    // CRUD Operations

    /**
     * Créer une recommandation
     */
    public void insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO Recommandation (id_recommandation, mois, annee, id_composant) " +
                "VALUES ('REC' || LPAD(nextval('recommandation_id_seq')::text, 5, '0'), ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mois);
            stmt.setInt(2, annee);
            stmt.setString(3, idComposant);
            stmt.executeUpdate();
        }
    }

    /**
     * Lire toutes les recommandations
     */
    public static List<Recommandation> getAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Recommandation";
        List<Recommandation> recommandations = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Recommandation recommandation = new Recommandation();
                recommandation.setIdRecommandation(rs.getString("id_recommandation"));
                recommandation.setMois(rs.getInt("mois"));
                recommandation.setAnnee(rs.getInt("annee"));
                recommandation.setIdComposant(rs.getString("id_composant"));
                recommandations.add(recommandation);
            }
        }
        return recommandations;
    }

    /**
     * Lire une recommandation par son ID
     */
    public static Recommandation getById(Connection connection, String idRecommandation) throws SQLException {
        String sql = "SELECT * FROM Recommandation WHERE id_recommandation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idRecommandation);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Recommandation(
                            rs.getString("id_recommandation"),
                            rs.getInt("mois"),
                            rs.getInt("annee"),
                            rs.getString("id_composant")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Mettre à jour une recommandation
     */
    public void update(Connection connection) throws SQLException {
        String sql = "UPDATE Recommandation SET mois = ?, annee = ?, id_composant = ? WHERE id_recommandation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mois);
            stmt.setInt(2, annee);
            stmt.setString(3, idComposant);
            stmt.setString(4, idRecommandation);
            stmt.executeUpdate();
        }
    }

    /**
     * Supprimer une recommandation par son ID
     */
    public static void delete(Connection connection, String idRecommandation) throws SQLException {
        String sql = "DELETE FROM Recommandation WHERE id_recommandation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idRecommandation);
            stmt.executeUpdate();
        }
    }
}
