package org.projets3s5.atelier.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Commission {
    private String idReparation;
    private String dateReparation;
    private String descriptionReparation;
    private String statusReparation;
    private String idReparationDetails;
    private double prixReparation;
    private double commissionTechnicien;
    private String idEmploye;
    private String nomEmploye;
    private String prenomEmploye;
    private String poste;
    private String genreVal;
    private String idGenre;

    // Getters et Setters


    public Commission() {
    }

    public Commission(String idReparation, String dateReparation, String descriptionReparation, String statusReparation, String idReparationDetails, double prixReparation, double commissionTechnicien, String idEmploye, String nomEmploye, String prenomEmploye, String poste, String genreVal, String idGenre) {
        this.idReparation = idReparation;
        this.dateReparation = dateReparation;
        this.descriptionReparation = descriptionReparation;
        this.statusReparation = statusReparation;
        this.idReparationDetails = idReparationDetails;
        this.prixReparation = prixReparation;
        this.commissionTechnicien = commissionTechnicien;
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.poste = poste;
        this.genreVal = genreVal;
        this.idGenre = idGenre;
    }

    public String getGenreVal() {
        return genreVal;
    }

    public void setGenreVal(String genreVal) {
        this.genreVal = genreVal;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public String getDateReparation() {
        return dateReparation;
    }

    public void setDateReparation(String dateReparation) {
        this.dateReparation = dateReparation;
    }

    public String getDescriptionReparation() {
        return descriptionReparation;
    }

    public void setDescriptionReparation(String descriptionReparation) {
        this.descriptionReparation = descriptionReparation;
    }

    public String getStatusReparation() {
        return statusReparation;
    }

    public void setStatusReparation(String statusReparation) {
        this.statusReparation = statusReparation;
    }

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

    public double getCommissionTechnicien() {
        return commissionTechnicien;
    }

    public void setCommissionTechnicien(double commissionTechnicien) {
        this.commissionTechnicien = commissionTechnicien;
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public static List<Commission> getCommissionsByTechnicianAndPeriod(Connection connection, String startDate, String endDate) throws Exception {
        List<Commission> commissions = new ArrayList<>();

        String query = "SELECT id_employe, nom_employe, prenom_employe, poste_val, " +
                "SUM(prix_reparation) AS total_prix_reparation, " +
                "SUM(prix_reparation * 0.05) AS total_commission_technicien " +
                "FROM vue_commission_technicien " +
                "WHERE date_reparation BETWEEN ? AND ? " +
                "GROUP BY id_employe, nom_employe, prenom_employe, poste_val";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Commission commission = new Commission();
                    commission.setIdEmploye(rs.getString("id_employe"));
                    commission.setNomEmploye(rs.getString("nom_employe"));
                    commission.setPrenomEmploye(rs.getString("prenom_employe"));
                    commission.setPoste(rs.getString("poste_val"));
                    commission.setPrixReparation(rs.getDouble("total_prix_reparation"));
                    commission.setCommissionTechnicien(rs.getDouble("total_commission_technicien"));

                    commissions.add(commission);
                }
            }
        }

        return commissions;
    }

    public static List<Commission> getCommissionsByTechnicianGenreAndPeriod(Connection connection, String startDate, String endDate, double seuilPrix) {
        List<Commission> commissions = new ArrayList<>();
        String query = "SELECT " +
                "SUM(prix_reparation) AS total_prix_reparation, " +
                "SUM(CASE WHEN prix_reparation >= ? THEN prix_reparation * 0.05 ELSE 0 END) AS total_commission_technicien, " +
                "genre_employe, id_genre " +
                "FROM vue_commission_technicien_genre " +
                "WHERE date_reparation BETWEEN ? AND ? " +
                "GROUP BY genre_employe, id_genre ";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, seuilPrix);
            pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Commission commission = new Commission();
                    commission.setPrixReparation(rs.getDouble("total_prix_reparation"));
                    commission.setCommissionTechnicien(rs.getDouble("total_commission_technicien"));
                    commission.setGenreVal(rs.getString("genre_employe"));
                    commission.setIdGenre(rs.getString("id_genre"));
                    commissions.add(commission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commissions;
    }

    public static List<Commission> getCommissionsByTechnicianGenreAndPeriod(Connection connection, String startDate, String endDate) {
        List<Commission> commissions = new ArrayList<>();
        String query = "SELECT "+
                "SUM(prix_reparation) AS total_prix_reparation, " +
                "SUM(prix_reparation * 0.05) AS total_commission_technicien, " +
                "genre_employe, id_genre " +
                "FROM vue_commission_technicien_genre " +
                "WHERE date_reparation BETWEEN ? AND ? " +
                "GROUP BY genre_employe, id_genre ";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Commission commission = new Commission();
                    commission.setPrixReparation(rs.getDouble("total_prix_reparation"));
                    commission.setCommissionTechnicien(rs.getDouble("total_commission_technicien"));
                    commission.setGenreVal(rs.getString("genre_employe"));
                    commission.setIdGenre(rs.getString("id_genre"));
                    commissions.add(commission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commissions;
    }
}
