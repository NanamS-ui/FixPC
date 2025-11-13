package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Employe {
    private String idEmploye;
    private String nom;
    private String prenom;
    private String username;
    private String mdp;
    private String email;
    private String idPoste;
    private String idGenre;

    // Constructeurs
    public Employe() {}

    public Employe(String idEmploye, String nom, String prenom, String username, String mdp, String email, String idPoste) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mdp = mdp;
        this.email = email;
        this.idPoste = idPoste;
    }

    public Employe(String idEmploye, String nom, String prenom, String username, String mdp, String email, String idPoste, String idGenre) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mdp = mdp;
        this.email = email;
        this.idPoste = idPoste;
        this.idGenre = idGenre;
    }

    // Getters et Setters
    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(String idPoste) {
        this.idPoste = idPoste;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    // Méthodes CRUD
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Employe ( nom, prenom, username, mdp, email, id_poste, id_genre) VALUES (?,?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.prenom);
            pstmt.setString(3, this.username);
            pstmt.setString(4, this.mdp);
            pstmt.setString(5, this.email);
            pstmt.setString(6, this.idPoste);
            pstmt.setString(7, this.idGenre);
            pstmt.executeUpdate();
        }
    }

    public static Employe getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Employe WHERE id_employe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Employe(
                            rs.getString("id_employe"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("username"),
                            rs.getString("mdp"),
                            rs.getString("email"),
                            rs.getString("id_poste"),
                            rs.getString("id_genre")
                    );
                }
            }
        }
        return null;
    }

    public static List<Employe> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Employe";
        List<Employe> employes = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                employes.add(new Employe(
                        rs.getString("id_employe"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("username"),
                        rs.getString("mdp"),
                        rs.getString("email"),
                        rs.getString("id_poste"),
                        rs.getString("id_genre")
                ));
            }
        }
        return employes;
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Employe SET nom = ?, prenom = ?, username = ?, mdp = ?, email = ?, id_poste = ?, id_genre = ? WHERE id_employe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.prenom);
            pstmt.setString(3, this.username);
            pstmt.setString(4, this.mdp);
            pstmt.setString(5, this.email);
            pstmt.setString(6, this.idPoste);
            pstmt.setString(7, this.idGenre);
            pstmt.setString(8, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Employe WHERE id_employe = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
