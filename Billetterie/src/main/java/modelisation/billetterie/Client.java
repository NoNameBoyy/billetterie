/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelisation.billetterie;

/**
 *
 * @author Vanessa Kenniche & Thomas Fernandes
 */

//Classe modélisant un client
public class Client {
    
    private String IDclient;
    private String nom;
    private String prenom;
    private String naiJour;
    private String naiMois;
    private String naiAnnee;
    private String adresse;
    private String numTel;
    private String taille;
    private String billet;
    private String categorie;
    private String fidelite;

    public Client(String IDClient, String nom, String prenom, String naiJour, String naiMois, String naiAnnee, String adresse, String numTel, String taille, String billet, String categorie, String fidelite)
    {
        this.IDclient = IDClient;
        this.nom      = nom;
        this.prenom   = prenom;
        this.naiJour  = naiJour;
        this.naiMois  = naiMois;
        this.naiAnnee = naiAnnee;
        this.adresse  = adresse;
        this.numTel   = numTel;
        this.taille   = taille;
        this.billet   = billet;
        this.categorie= categorie;
        this.fidelite = fidelite;
    }
    
//********************

    public String getIDClient() {
        return IDclient;
    }

    public void setIDClient(String IDclient) {
        this.IDclient = IDclient;
    }
    
//********************
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }

//********************
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    
//********************
    
    public String getNaiJour() {
        return naiJour;
    }
    
    public void setNaiJour(String naiJour){
        this.naiJour = naiJour;
    }
        
//********************
    
    public String getNaiMois() {
        return naiMois;
    }
    
    public void setNaiMois(String naiMois){
        this.naiMois = naiMois;
    }
    
//********************
    
    public String getNaiAnnee() {
        return naiAnnee;
    }
    
    public void setNaiAnnee(String naiAnnee){
        this.naiAnnee = naiAnnee;
    }
        
//********************
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    
//********************
    
    public String getNumTel() {
        return numTel;
    }
    
    public void setNumTel(String numTel){
        this.numTel = numTel;
    }
        
//********************
    
    public String getTaille() {
        return taille;
    }
    
    public void setTaille(String taille){
        this.taille = taille;
    }
    
//********************
    
    public String getBillet() {
        return billet;
    }
    
    public void setBillet(String billet){
        this.billet = billet;
    }
    
//********************
        
    public String getCategorie() {
        return categorie;
    }
    
    public void setCategorie(String categorie){
        this.categorie = categorie;
    }
    
//********************
        
    public String getFidelite() {
        return fidelite;
    }
    
    public void setFidelite(String fidelite){
        this.fidelite = fidelite;
    }
    
//********************
    
    public String toString()
    {
        return IDclient+","+nom+","+prenom+","+naiJour+","+naiMois+","+naiAnnee+","+adresse+","+numTel+","+taille+","+billet+","+categorie+","+fidelite+"\n";
    }
}

