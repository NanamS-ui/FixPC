package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRetour {
    private String idTypeRetour;
    private String val;

    // Constructeurs
    public TypeRetour() {
    }

    public TypeRetour(String idTypeRetour, String val) {
        this.idTypeRetour = idTypeRetour;
        this.val = val;
    }

    // Getters et Setters
    public String getIdTypeRetour() {
        return idTypeRetour;
    }

    public void setIdTypeRetour(String idTypeRetour) {
        this.idTypeRetour = idTypeRetour;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthode pour insérer un type de retour dans la base de données
    public void insert(Connection connection) throws SQLException {
        String sql = "INSERT INTO type_retour_client (val) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.val);
            stmt.executeUpdate();
        }
    }

    // Méthode pour récupérer tous les types de retour
    public static List<TypeRetour> getAll(Connection connection) throws SQLException {
        String sql = "SELECT id_type_retour, val FROM type_retour_client";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<TypeRetour> typeRetours = new ArrayList<>();
            while (rs.next()) {
                TypeRetour typeRetour = new TypeRetour();
                typeRetour.setIdTypeRetour(rs.getString("id_type_retour"));
                typeRetour.setVal(rs.getString("val"));
                typeRetours.add(typeRetour);
            }
            return typeRetours;
        }
    }
}
