package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ordinateur {
    private String idOrdinateur;
    private String marque;
    private String modele;
    private String idSysteme;
    private String idTypeOrdi;
    private String idClient;

    // Constructeurs
    public Ordinateur() { }

    public Ordinateur(String idOrdinateur, String marque, String modele, String idSysteme, String idTypeOrdi, String idClient) {
        this.idOrdinateur = idOrdinateur;
        this.marque = marque;
        this.modele = modele;
        this.idSysteme = idSysteme;
        this.idTypeOrdi = idTypeOrdi;
        this.idClient = idClient;
    }

    // Getters et Setters
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

    public String getIdSysteme() {
        return idSysteme;
    }

    public void setIdSysteme(String idSysteme) {
        this.idSysteme = idSysteme;
    }

    public String getIdTypeOrdi() {
        return idTypeOrdi;
    }

    public void setIdTypeOrdi(String idTypeOrdi) {
        this.idTypeOrdi = idTypeOrdi;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    // Méthodes CRUD
    public static List<Ordinateur> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Ordinateur";
        List<Ordinateur> ordinateurs = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ordinateurs.add(new Ordinateur(
                        rs.getString("id_ordinateur"),
                        rs.getString("marque"),
                        rs.getString("modele"),
                        rs.getString("id_systeme"),
                        rs.getString("id_type_ordi"),
                        rs.getString("id_client")
                ));
            }
        }
        return ordinateurs;
    }

    public static Ordinateur getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Ordinateur WHERE id_ordinateur = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Ordinateur(
                            rs.getString("id_ordinateur"),
                            rs.getString("marque"),
                            rs.getString("modele"),
                            rs.getString("id_systeme"),
                            rs.getString("id_type_ordi"),
                            rs.getString("id_client")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Ordinateur (marque, modele, id_systeme, id_type_ordi, id_client) VALUES ( ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.marque);
            pstmt.setString(2, this.modele);
            pstmt.setString(3, this.idSysteme);
            pstmt.setString(4, this.idTypeOrdi);
            pstmt.setString(5, this.idClient);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Ordinateur SET marque = ?, modele = ?, id_systeme = ?, id_type_ordi = ?, id_client = ? WHERE id_ordinateur = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.marque);
            pstmt.setString(2, this.modele);
            pstmt.setString(3, this.idSysteme);
            pstmt.setString(4, this.idTypeOrdi);
            pstmt.setString(5, this.idClient);
            pstmt.setString(6, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Ordinateur WHERE id_ordinateur = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }

    public static List<Ordinateur> getComputersWithProblem(Connection connection, String problemId) throws SQLException {
        List<Ordinateur> ordinateurs = new ArrayList<>();

        String query = "SELECT o.id_ordinateur, o.marque, o.modele, o.id_client " +
                "FROM Ordinateur o " +
                "JOIN Reparation r ON o.id_ordinateur = r.id_ordinateur " +
                "JOIN ReparationDetails rd ON r.id_reparation = rd.id_reparation " +
                "JOIN Probleme p ON rd.id_probleme = p.id_probleme " +
                "WHERE p.id_probleme = ? GROUP BY o.id_ordinateur";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, problemId); // Set the problem ID in the query

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String idOrdinateur = rs.getString("id_ordinateur");
                    String marque = rs.getString("marque");
                    String modele = rs.getString("modele");
                    String id_client = rs.getString("id_client");

                    // Create Ordinateur objects and add them to the list
                    Ordinateur ordinateur = new Ordinateur(idOrdinateur, marque, modele,"","",id_client);
                    ordinateurs.add(ordinateur);
                }
            }
        }

        return ordinateurs;
    }
}