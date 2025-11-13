package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CombinedRetour {

    private String idRetour;
    private String retourDescription;
    private String typeRetourVal;
    private String idReparationDetails;
    private Double prixReparation;  // Changed from BigDecimal to Double
    private Short quantiteUtilisee;
    private String idReparation;
    private String dateReparation;
    private String reparationDescription;
    private String reparationStatus;
    private String idOrdinateur;
    private String marque;
    private String modele;
    private String clientName;
    private String clientEmail;

    public CombinedRetour() {
    }

    public String getIdRetour() {
        return idRetour;
    }

    public void setIdRetour(String idRetour) {
        this.idRetour = idRetour;
    }

    public String getRetourDescription() {
        return retourDescription;
    }

    public void setRetourDescription(String retourDescription) {
        this.retourDescription = retourDescription;
    }

    public String getTypeRetourVal() {
        return typeRetourVal;
    }

    public void setTypeRetourVal(String typeRetourVal) {
        this.typeRetourVal = typeRetourVal;
    }

    public String getIdReparationDetails() {
        return idReparationDetails;
    }

    public void setIdReparationDetails(String idReparationDetails) {
        this.idReparationDetails = idReparationDetails;
    }

    public Double getPrixReparation() {
        return prixReparation;
    }

    public void setPrixReparation(Double prixReparation) {
        this.prixReparation = prixReparation;
    }

    public Short getQuantiteUtilisee() {
        return quantiteUtilisee;
    }

    public void setQuantiteUtilisee(Short quantiteUtilisee) {
        this.quantiteUtilisee = quantiteUtilisee;
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

    public String getReparationDescription() {
        return reparationDescription;
    }

    public void setReparationDescription(String reparationDescription) {
        this.reparationDescription = reparationDescription;
    }

    public String getReparationStatus() {
        return reparationStatus;
    }

    public void setReparationStatus(String reparationStatus) {
        this.reparationStatus = reparationStatus;
    }

    public String getIdOrdinateur() {
        return idOrdinateur;
    }

    public void setIdOrdinateur(String idOrdinateur) {
        this.idOrdinateur = idOrdinateur;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public CombinedRetour(String idRetour, String retourDescription, String typeRetourVal, String idReparationDetails, Double prixReparation, Short quantiteUtilisee, String idReparation, String dateReparation, String reparationDescription, String reparationStatus, String idOrdinateur, String marque, String modele, String clientName, String clientEmail) {
        this.idRetour = idRetour;
        this.retourDescription = retourDescription;
        this.typeRetourVal = typeRetourVal;
        this.idReparationDetails = idReparationDetails;
        this.prixReparation = prixReparation;
        this.quantiteUtilisee = quantiteUtilisee;
        this.idReparation = idReparation;
        this.dateReparation = dateReparation;
        this.reparationDescription = reparationDescription;
        this.reparationStatus = reparationStatus;
        this.idOrdinateur = idOrdinateur;
        this.marque = marque;
        this.modele = modele;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
    }

    // Getters and Setters

    public static List<CombinedRetour> getRetourByComposantTypeRetour(Connection connection, String idComposant, String idTypeRetour, String idTypeOrdi) {
        List<CombinedRetour> retourList = new ArrayList<>();
        String sql = "SELECT id_retour, retour_description, type_retour_val, " +
                "id_reparation_details, prix_reparation, quantite_utilisee, " +
                "id_reparation, date_reparation, reparation_description, reparation_status, " +
                "id_ordinateur, marque, modele, client_name, client_email " +
                "FROM CombinedRetourView " +
                "WHERE id_composant_retour = ? AND id_type_retour = ? AND id_type_ordi = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set parameters
            stmt.setString(1, idComposant);
            stmt.setString(2, idTypeRetour);
            stmt.setString(3, idTypeOrdi);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CombinedRetour retour = new CombinedRetour();
                    retour.setIdRetour(rs.getString("id_retour"));
                    retour.setRetourDescription(rs.getString("retour_description"));
                    retour.setTypeRetourVal(rs.getString("type_retour_val"));
                    retour.setIdReparationDetails(rs.getString("id_reparation_details"));
                    retour.setPrixReparation(rs.getDouble("prix_reparation"));
                    retour.setQuantiteUtilisee(rs.getShort("quantite_utilisee"));
                    retour.setIdReparation(rs.getString("id_reparation"));
                    retour.setDateReparation(rs.getString("date_reparation"));
                    retour.setReparationDescription(rs.getString("reparation_description"));
                    retour.setReparationStatus(rs.getString("reparation_status"));
                    retour.setIdOrdinateur(rs.getString("id_ordinateur"));
                    retour.setMarque(rs.getString("marque"));
                    retour.setModele(rs.getString("modele"));
                    retour.setClientName(rs.getString("client_name"));
                    retour.setClientEmail(rs.getString("client_email"));

                    retourList.add(retour);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retourList;
    }
}
