/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelisation.billetterie;

/**
 *
 * @author Vanessa Kenniche & Thomas Fernandes
 */
public class Calcul {
    
    private int annee;
    private String billet;
    private static int numero = 0;
    private String categorie;
    private int fidelite;
    private double reduction = 0;
    private double prix;
    private String stringFidelite;
    private String tauxReduc;
    private String stringPrix;
    private String IDClient;
    
    public Calcul(){}

    public Calcul(int annee, String billet){this.annee = annee; this.billet = billet;}

//**********************ATTRIBUTION DU NUMERO**********************\\
    public String getNumero(Client c){
        WriterReader WR = new WriterReader(); 
        WR.ScannerGeneral(c);
        numero = WR.getIntNextID()+1;
        String strNumero = "00"+String.valueOf(numero);
        setIDClient(strNumero);
        return strNumero;
    }

    public void setIDClient(String IDClient) {
        this.IDClient = IDClient;
    }

    public String getIDClient() {
        return IDClient;
    }
    
    
//**********************DETERMINATION DE LA CATEGORIE**********************\\
    public int getAnnee(){
        return annee;
    }
    
    public String getCategorie(){

        if(getAnnee() >= 2010){
            categorie = "Junior";
        }else{
            if(getAnnee() <= 1957){
                categorie = "Senior";
            }else{
                categorie = "Normal";
            }
        }
        return categorie;
    }
    
//**********************DETERMINATION DE LA REDUCTION**********************\\

    public String getBillet(){
        return billet;
    }
    
    public int getFidelite(){
        if(getBillet().equals("T1 : 50e 20 attractions"))
            fidelite = 100;
        else
            if(getBillet().equals("T2 : 70e 45 attractions"))
                fidelite = 200;
            else
                if(getBillet().equals("T3 : 85e Attractions illimitees"))
                    fidelite = 300;
        return fidelite;
    }
    
        public double getPrix(){
        if(getBillet().equals("T1 : 50e 20 attractions"))
            prix = 50;
        else
            if(getBillet().equals("T2 : 70e 45 attractions"))
                prix = 70;
            else
                if(getBillet().equals("T3 : 85e Attractions illimitees"))
                    prix = 85;
        return prix;  
    }
    
    public String getStringFidelite(){
        stringFidelite = String.valueOf(getFidelite());
        return stringFidelite;
    }
            
    public double reduc(){
        if(getCategorie().equals("Junior"))
            reduction = 0.5;
        else
            if(getCategorie().equals("Senior"))
                reduction = 0.2;
        else
            if(getCategorie().equals("Normal"))
                reduction = 0;
        return reduction;
    }
    
    public String getReduc(){
        if(reduc() == 0.5)
            tauxReduc = "50%";
        else
            if(reduc() == 0.2)
            tauxReduc = "20%";
        else
            if(reduc() == 0)
            tauxReduc = "Tarif standard";
        return tauxReduc;
    }
    
    public double prixTotal(){
        prix = getPrix();
        prix = prix - prix*reduction;
        return prix;
    }
    
    public String prixTotalS(){
        stringPrix = String.valueOf(prixTotal())+"0€";//ou System.out.printf("%.2d€",prixTotal());
        return stringPrix;
    }
    
    //Générateur des années de 1900 à 2022 et des jours d'un mois
        public String [] tabAnnee(){
        String [] tabAnnee = new String[124];
        tabAnnee[0] = "Année";
        for(int i = 1, j = 1900; i <= tabAnnee.length - 1; i++, j++)
            tabAnnee[i] = String.valueOf(j);
        return tabAnnee;
    }

    public String [] tabJour() {
        String [] tabJour = new String[32];
        tabJour[0] = "Jour";
        for (int i = 1; i <= tabJour.length - 1; i++)
            tabJour[i] = String.valueOf(i);
        return tabJour;
    }

    //Vérificateur de caractères strictement numériques
    public boolean notChar(String s){
        if (s.matches("[0-9]+") && s.length() >= 2)
            return true;
        return false;
    }

    //Initialisation d'un client par défaut vide utilisé pour les fonctions à multiples client pour limiter 
    //les copies de nouvelles instances de clients 
    Client clientvide = new Client("","","","","","","","","","","","");

}
