package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Genre {
    private String idGenre;
    private String val;

    // Constructeur par défaut
    public Genre() {
    }

    // Constructeur paramétré
    public Genre(String idGenre, String val) {
        this.idGenre = idGenre;
        this.val = val;
    }

    // Getters et Setters
    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthode pour récupérer tous les genres
    public static List<Genre> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Genre";
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                genres.add(new Genre(
                        rs.getString("id_genre"),
                        rs.getString("val")
                ));
            }
        }
        return genres;
    }

    // Méthode pour récupérer un genre par son ID
    public static Genre getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Genre WHERE id_genre = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Genre(
                            rs.getString("id_genre"),
                            rs.getString("val")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un nouveau genre
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Genre (val) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un genre
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Genre SET val = ? WHERE id_genre = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un genre
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Genre WHERE id_genre = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
