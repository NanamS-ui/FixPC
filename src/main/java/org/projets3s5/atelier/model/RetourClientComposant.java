package org.projets3s5.atelier.model;

public class RetourClientComposant {
    private String idRetour;
    private String retourDescription;
    private String dateRetour;
    private String idClient;
    private String clientNom;
    private String clientTelephone;
    private String clientEmail;
    private String clientDateInscription;
    private String idComposant;
    private String composantVal;
    private String composantImg;
    private double composantPrixAchat;
    private double composantPrixVente;
    private String idTypeRetour;
    private String typeRetourVal;

    // Constructeurs
    public RetourClientComposant() {
    }

    public RetourClientComposant(String idRetour, String retourDescription, String dateRetour, String idClient,
                                 String clientNom, String clientTelephone, String clientEmail, String clientDateInscription,
                                 String idComposant, String composantVal, String composantImg, double composantPrixAchat,
                                 double composantPrixVente, String idTypeRetour, String typeRetourVal) {
        this.idRetour = idRetour;
        this.retourDescription = retourDescription;
        this.dateRetour = dateRetour;
        this.idClient = idClient;
        this.clientNom = clientNom;
        this.clientTelephone = clientTelephone;
        this.clientEmail = clientEmail;
        this.clientDateInscription = clientDateInscription;
        this.idComposant = idComposant;
        this.composantVal = composantVal;
        this.composantImg = composantImg;
        this.composantPrixAchat = composantPrixAchat;
        this.composantPrixVente = composantPrixVente;
        this.idTypeRetour = idTypeRetour;
        this.typeRetourVal = typeRetourVal;
    }

    // Getters et Setters
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

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getClientTelephone() {
        return clientTelephone;
    }

    public void setClientTelephone(String clientTelephone) {
        this.clientTelephone = clientTelephone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientDateInscription() {
        return clientDateInscription;
    }

    public void setClientDateInscription(String clientDateInscription) {
        this.clientDateInscription = clientDateInscription;
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

    public String getIdTypeRetour() {
        return idTypeRetour;
    }

    public void setIdTypeRetour(String idTypeRetour) {
        this.idTypeRetour = idTypeRetour;
    }

    public String getTypeRetourVal() {
        return typeRetourVal;
    }

    public void setTypeRetourVal(String typeRetourVal) {
        this.typeRetourVal = typeRetourVal;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "RetourClientComposant{" +
                "idRetour='" + idRetour + '\'' +
                ", retourDescription='" + retourDescription + '\'' +
                ", dateRetour='" + dateRetour + '\'' +
                ", idClient='" + idClient + '\'' +
                ", clientNom='" + clientNom + '\'' +
                ", clientTelephone='" + clientTelephone + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientDateInscription='" + clientDateInscription + '\'' +
                ", idComposant='" + idComposant + '\'' +
                ", composantVal='" + composantVal + '\'' +
                ", composantImg='" + composantImg + '\'' +
                ", composantPrixAchat=" + composantPrixAchat +
                ", composantPrixVente=" + composantPrixVente +
                ", idTypeRetour='" + idTypeRetour + '\'' +
                ", typeRetourVal='" + typeRetourVal + '\'' +
                '}';
    }
}
