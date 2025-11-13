package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Poste {
    private String idPoste;
    private String val;
    private double salaire;

    public Poste() {
    }

    public Poste(String idPoste, String val, double salaire) {
        this.idPoste = idPoste;
        this.val = val;
        this.salaire = salaire;
    }

    // Getters et setters
    public String getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(String idPoste) {
        this.idPoste = idPoste;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    // Méthode pour récupérer tous les postes
    public static List<Poste> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Poste";
        List<Poste> postes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                postes.add(new Poste(
                        rs.getString("id_poste"),
                        rs.getString("val"),
                        rs.getDouble("salaire")
                ));
            }
        }
        return postes;
    }

    // Méthode pour récupérer un poste par son ID
    public static Poste getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Poste WHERE id_poste = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Poste(
                            rs.getString("id_poste"),
                            rs.getString("val"),
                            rs.getDouble("salaire")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un nouveau poste
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Poste (val, salaire) VALUES ( ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setDouble(2, this.salaire);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un poste
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Poste SET val = ?, salaire = ? WHERE id_poste = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setDouble(2, this.salaire);
            pstmt.setString(3, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un poste
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Poste WHERE id_poste = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
