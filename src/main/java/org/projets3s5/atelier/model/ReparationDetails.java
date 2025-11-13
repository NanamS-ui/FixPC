package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReparationDetails {
    private String idReparationDetails;
    private double prixReparation;
    private int quantiteUtilisee;
    private String idTypeReparation;
    private String idComposant;
    private String idProbleme;
    private String idReparation;
    private String idEmploye;

    // Constructeurs
    public ReparationDetails() {}

    public ReparationDetails(String idReparationDetails, double prixReparation, int quantiteUtilisee,
                            String idTypeReparation, String idComposant,
                             String idProbleme, String idReparation, String idEmploye) {
        this.idReparationDetails = idReparationDetails;
        this.prixReparation = prixReparation;
        this.quantiteUtilisee = quantiteUtilisee;
        this.idTypeReparation = idTypeReparation;
        this.idComposant = idComposant;
        this.idProbleme = idProbleme;
        this.idReparation = idReparation;
        this.idEmploye = idEmploye;
    }

    // Getters et Setters
    public String getIdReparationDetails() {
        return idReparationDetails;
    }

    public void setIdReparationDetails(String idReparationDetails) {
        this.idReparationDetails = idReparationDetails;
    }

    public double getPrixReparation() {
        return prixReparation;
    }

    public void setPrixReparation(double prixReparation) {
        this.prixReparation = prixReparation;
    }

    public int getQuantiteUtilisee() {
        return quantiteUtilisee;
    }

    public void setQuantiteUtilisee(int quantiteUtilisee) {
        this.quantiteUtilisee = quantiteUtilisee;
    }



    public String getIdTypeReparation() {
        return idTypeReparation;
    }

    public void setIdTypeReparation(String idTypeReparation) {
        this.idTypeReparation = idTypeReparation;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    public String getIdProbleme() {
        return idProbleme;
    }

    public void setIdProbleme(String idProbleme) {
        this.idProbleme = idProbleme;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    // Méthodes CRUD
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO ReparationDetails ( prix_reparation, quantite_utilisee, id_type_reparation, id_composant, id_probleme, id_reparation, id_employe) " +
                "VALUES ( ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, this.prixReparation);
            pstmt.setInt(2, this.quantiteUtilisee);
            pstmt.setString(3, this.idTypeReparation);
            pstmt.setString(4, this.idComposant);
            pstmt.setString(5, this.idProbleme);
            pstmt.setString(6, this.idReparation);
            pstmt.setString(7, this.idEmploye);
            pstmt.executeUpdate();
        }
    }

    public static ReparationDetails getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM ReparationDetails WHERE id_reparation_details = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ReparationDetails(
                            rs.getString("id_reparation_details"),
                            rs.getDouble("prix_reparation"),
                            rs.getInt("quantite_utilisee"),
                            rs.getString("id_type_reparation"),
                            rs.getString("id_composant"),
                            rs.getString("id_probleme"),
                            rs.getString("id_reparation"),
                            rs.getString("id_employe")
                    );
                }
            }
        }
        return null;
    }

    public static List<ReparationDetails> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM ReparationDetails";
        List<ReparationDetails> detailsList = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                detailsList.add(new ReparationDetails(
                        rs.getString("id_reparation_details"),
                        rs.getDouble("prix_reparation"),
                        rs.getInt("quantite_utilisee"),
                        rs.getString("id_type_reparation"),
                        rs.getString("id_composant"),
                        rs.getString("id_probleme"),
                        rs.getString("id_reparation"),
                        rs.getString("id_employe")
                ));
            }
        }
        return detailsList;
    }

    public void update(Connection connection) throws SQLException {
        String query = "UPDATE ReparationDetails SET prix_reparation = ?, quantite_utilisee = ?, id_type_reparation = ?, id_composant = ?, id_probleme = ?, id_reparation = ?, id_employe = ? " +
                "WHERE id_reparation_details = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, this.prixReparation);
            pstmt.setInt(2, this.quantiteUtilisee);
            pstmt.setString(3, this.idTypeReparation);
            pstmt.setString(4, this.idComposant);
            pstmt.setString(5, this.idProbleme);
            pstmt.setString(6, this.idReparation);
            pstmt.setString(7, this.idReparationDetails);
            pstmt.setString(8, this.idEmploye);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM ReparationDetails WHERE id_reparation_details = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
