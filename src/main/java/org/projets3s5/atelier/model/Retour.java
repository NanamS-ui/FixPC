package org.projets3s5.atelier.model;

import org.projets3s5.atelier.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Retour {
    private String idRetour;
    private String description;
    private String idTypeRetour;
    private String idReparationDetails;
    private String idComposant;

    private String dateRetour;
    private String idClient;

    // Constructeurs
    public Retour() {
    }

    public Retour(String idRetour, String description, String idTypeRetour, String idReparationDetails, String idComposant) {
        this.idRetour = idRetour;
        this.description = description;
        this.idTypeRetour = idTypeRetour;
        this.idReparationDetails = idReparationDetails;
        this.idComposant = idComposant;
    }

    public Retour(String idRetour, String description, String idTypeRetour, String idReparationDetails) {
        this.idRetour = idRetour;
        this.description = description;
        this.idTypeRetour = idTypeRetour;
        this.idReparationDetails = idReparationDetails;
    }

    // Getters et Setters
    public String getIdRetour() {
        return idRetour;
    }

    public void setIdRetour(String idRetour) {
        this.idRetour = idRetour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdTypeRetour() {
        return idTypeRetour;
    }

    public void setIdTypeRetour(String idTypeRetour) {
        this.idTypeRetour = idTypeRetour;
    }

    public String getIdReparationDetails() {
        return idReparationDetails;
    }

    public void setIdReparationDetails(String idReparationDetails) {
        this.idReparationDetails = idReparationDetails;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    // Méthode pour insérer un retour dans la base de données
    public void insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO Retour (description, id_type_retour, id_reparation_details, id_composant, date_retour, id_client) VALUES (?, ?, ?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.description);
            stmt.setString(2, this.idTypeRetour);
            stmt.setString(3, this.idReparationDetails);
            stmt.setString(4, this.idComposant);
            stmt.setDate(5, Utils.convertToDate(this.dateRetour));
            stmt.setString(6, this.idClient);
            stmt.executeUpdate();
        }
    }

    // Méthode pour récupérer tous les retours
    public static List<Retour> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id_retour, description, id_type_retour, id_reparation_details, id_composant FROM Retour";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<Retour> retours = new ArrayList<>();
            while (rs.next()) {
                Retour retour = new Retour();
                retour.setIdRetour(rs.getString("id_retour"));
                retour.setDescription(rs.getString("description"));
                retour.setIdTypeRetour(rs.getString("id_type_retour"));
                retour.setIdReparationDetails(rs.getString("id_reparation_details"));
                retour.setIdReparationDetails(rs.getString("id_composant"));
                retours.add(retour);
            }
            return retours;
        }
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
