package org.projets3s5.atelier.model;

import org.projets3s5.atelier.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecommandationComposant {
    private String idRecommandation;
    private int mois;
    private int annee;
    private String idComposant;
    private String composantVal;
    private String composantImg;
    private double composantPrixAchat;
    private double composantPrixVente;

    // Constructeurs
    public RecommandationComposant() {}

    public RecommandationComposant(String idRecommandation, int mois, int annee, String idComposant,
                                   String composantVal, String composantImg, double composantPrixAchat,
                                   double composantPrixVente) {
        this.idRecommandation = idRecommandation;
        this.mois = mois;
        this.annee = annee;
        this.idComposant = idComposant;
        this.composantVal = composantVal;
        this.composantImg = composantImg;
        this.composantPrixAchat = composantPrixAchat;
        this.composantPrixVente = composantPrixVente;
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

    public String getComposantVal() {
        return composantVal;
    }

    public void setComposantVal(String composantVal) {
        this.composantVal = composantVal;
    }

    public String getComposantImg() {
        return composantImg;
    }

    public void setComposantImg(String composantImg) {
        this.composantImg = composantImg;
    }

    public double getComposantPrixAchat() {
        return composantPrixAchat;
    }

    public void setComposantPrixAchat(double composantPrixAchat) {
        this.composantPrixAchat = composantPrixAchat;
    }

    public double getComposantPrixVente() {
        return composantPrixVente;
    }

    public void setComposantPrixVente(double composantPrixVente) {
        this.composantPrixVente = composantPrixVente;
    }

    @Override
    public String toString() {
        return "RecommandationComposant{" +
                "idRecommandation='" + idRecommandation + '\'' +
                ", mois=" + mois +
                ", annee=" + annee +
                ", idComposant='" + idComposant + '\'' +
                ", composantVal='" + composantVal + '\'' +
                ", composantImg='" + composantImg + '\'' +
                ", composantPrixAchat=" + composantPrixAchat +
                ", composantPrixVente=" + composantPrixVente +
                '}';
    }

    public static List<RecommandationComposant> getFiltreByMoisAnnee(int mois, int annee) {
        List<RecommandationComposant> recommandations = new ArrayList<>();

        String sql = "SELECT DISTINCT id_composant, composant_val, composant_img, " +
                "composant_prix_achat, composant_prix_vente " +
                "FROM RecommandationComposantView " +
                "WHERE mois = ? AND annee = ?";

        try (Connection connection = DatabaseUtil.getConnectionPostgre();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, mois);
            statement.setInt(2, annee);

            // Exécuter la requête
            try (ResultSet resultSet = statement.executeQuery()) {
                // Parcourir les résultats
                while (resultSet.next()) {
                    RecommandationComposant recommandation = new RecommandationComposant(
                            resultSet.getString("id_composant"),
                            resultSet.getString("composant_val"),
                            resultSet.getString("composant_img"),
                            resultSet.getDouble("composant_prix_achat"),
                            resultSet.getDouble("composant_prix_vente")
                    );
                    recommandations.add(recommandation);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recommandations;
    }

    public static List<RecommandationComposant> getAllRecommandation() {
        List<RecommandationComposant> recommandations = new ArrayList<>();

        String sql = "SELECT DISTINCT id_composant, composant_val, composant_img, " +
                "composant_prix_achat, composant_prix_vente, annee " +
                "FROM RecommandationComposantView " +
                "WHERE  annee = 2024";

        try (Connection connection = DatabaseUtil.getConnectionPostgre();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RecommandationComposant recommandation = new RecommandationComposant(
                            resultSet.getString("id_composant"),
                            resultSet.getString("composant_val"),
                            resultSet.getString("composant_img"),
                            resultSet.getDouble("composant_prix_achat"),
                            resultSet.getDouble("composant_prix_vente"),
                            resultSet.getInt("annee")
                    );
                    recommandations.add(recommandation);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recommandations;
    }

    public RecommandationComposant(String idComposant, String composantVal, String composantImg, double composantPrixAchat, double composantPrixVente) {
        this.idComposant = idComposant;
        this.composantVal = composantVal;
        this.composantImg = composantImg;
        this.composantPrixAchat = composantPrixAchat;
        this.composantPrixVente = composantPrixVente;
    }

    public RecommandationComposant(String idComposant, String composantVal, String composantImg, double composantPrixAchat, double composantPrixVente, int annee) {
        this.idComposant = idComposant;
        this.composantVal = composantVal;
        this.composantImg = composantImg;
        this.composantPrixAchat = composantPrixAchat;
        this.composantPrixVente = composantPrixVente;
        this.annee = annee;
    }
}
