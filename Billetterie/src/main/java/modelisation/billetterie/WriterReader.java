/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelisation.billetterie;
import java.io.*;
import java.util.*;

/**
 *
 * @author Vanessa Kenniche & Thomas Fernandes
 */
public class WriterReader {
    //Déclaration du chemin qui sera mis à jour dans toutes les classes du programme
    public static String path(){
        String path = String.format("C:\\Users\\thoma\\Desktop\\ModelisationParc_VanessaKENNICHE_ThomasFERNANDES\\Code source\\ModelisationParc\\Billetterie\\fichiers");
        return path;
    }
    private static String doc = path()+"\\clients.csv";
    private static FileOutputStream fos;
    private static ArrayList<Client> clientList  = new ArrayList<>();

    //Initialisation du fichier, le créé s'il n'existe pas déjà 
    public static void init() {
        File myfile = new File(doc);
        try {

            boolean exists = myfile.exists();

            if (!exists) {
                myfile.createNewFile();
                fos = new FileOutputStream(myfile, true);
            } else {
                fos = new FileOutputStream(myfile, true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //Ecriture du client dans le fichier
    public static boolean write(Client client) {
        
        try {
            clientList.add(client);
            
            fos.write(client.toString().getBytes());
            fos.flush();
            fos.close();
            System.out.println("write success");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Initialisation de la première ligne (titre) dans le fichier si ce dernier est vide
    public static void emptyy() {
        Client empty = new Client("0", "Nom", "Prenom", "Jour", "Mois", "Annee", "Adresse", "Telephone", "Taille", "Billet", "Categorie", "Fidelite");       
        File file = new File(doc);
        if (file.length() == 0) {
            try {
                fos.write(empty.toString().getBytes());
                System.out.println("empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Lecture des clients du fichier dans un ArrayList de clients
    public static void read() {
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(doc));
			String line = reader.readLine();
			while (line != null) {
                            String[] ps = line.split(",");
                            clientList.add(new Client(ps[0], ps[1], ps[2], ps[3], ps[4], ps[5], ps[6], ps[7], ps[8], ps[9], ps[10], ps[11]));
                            line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    //Affiche de tous les clients du fichier dans le terminal
    public static void affiche(){
        for (Client c: clientList){
            System.out.println(c.toString());
        }
    }

//********************** RECHERCHE **********************\\
    
    private String ligneOut; //Informations en String d'un client
    private int intNextID; //Identifiant du client déterminé par le plus gros identifiant précédent 
                           //(permet d'avoir un identifiant unique même si les indexes changent de place ou son supprimés)
    Client client = new Client("", "", "", "", "", "", "", "", "", "", "", "");  
    public void setLigneOut(String ligne){this.ligneOut = ligne;}  
    
    //Scanner général du fichier qui cherche parmi chaque élément de chaque client, permet de retrouver un client et de déterminer un nouvel identifiant 
    public boolean ScannerGeneral(Client c) {
        BufferedReader reader;
        boolean found = false;
		try {
                    
                    int i = 0;
                    reader = new BufferedReader(new FileReader(doc));
                    String line = reader.readLine();
                    
                    //Cherche jusqu'à tomber sur une correspondance (avec des tolérances pour les champs vides)    
                    while (line != null && !found) {
                        String[] ps = line.split(",");
                        line = reader.readLine();
                        ligneOut = String.format(ps[0]+","+ps[1]+","+ ps[2]+","+ps[3]+","+ps[4]+","+ps[5]+","+ps[6]+","+ps[7]+","+ps[8]+","+ps[9]+","+ps[10]+","+ps[11]);

                          //Détermination IDClient
                        if(i < Integer. valueOf(ps[0])){
                           i = Integer. valueOf(ps[0]);
                           intNextID = i;
                        }
                          
                        if(     (ps[0].trim().equalsIgnoreCase(c.getIDClient().trim()) || c.getIDClient().trim().equals(""))   
                             && (ps[1].trim().equalsIgnoreCase(c.getNom().trim())      || c.getNom().trim().equals("")) 
                             && (ps[2].trim().equalsIgnoreCase(c.getPrenom().trim())   || c.getPrenom().trim().equals("")) 
                             && (ps[3].trim().equalsIgnoreCase(c.getNaiJour().trim())  || c.getNaiJour().trim().equals("Jour")) 
                             && (ps[4].trim().equalsIgnoreCase(c.getNaiMois().trim())  || c.getNaiMois().trim().equals("Mois")) 
                             && (ps[5].trim().equalsIgnoreCase(c.getNaiAnnee().trim()) || c.getNaiAnnee().trim().equals("Année")) 
                             && (ps[6].trim().equalsIgnoreCase(c.getAdresse().trim())  || c.getAdresse().trim().equals("")) 
                             && (ps[7].trim().equalsIgnoreCase(c.getNumTel().trim())   || c.getNumTel().trim().equals("")) 
                             && (ps[8].trim().equalsIgnoreCase(c.getTaille().trim())   || c.getTaille().trim().equals("")) 
                             && (ps[9].trim().equalsIgnoreCase(c.getBillet().trim())   || c.getBillet().trim().equals("--Sélectionner--")) 
                             && (ps[10].trim().equalsIgnoreCase(c.getCategorie().trim())|| c.getCategorie().trim().equals("--Sélectionner--"))
                             && (ps[11].trim().equalsIgnoreCase(c.getFidelite().trim())|| c.getFidelite().trim().equals("")))
                        {
                            found = true;
                            ligneOut = String.format(ps[0]+","+ps[1]+","+ ps[2]+","+ps[3]+","+ps[4]+","+ps[5]+","+ps[6]+","+ps[7]+","+ps[8]+","+ps[9]+","+ps[10]+","+ps[11]); //Déclare le client trouvé dans une String
                            client = new Client(ps[0], ps[1], ps[2], ps[3], ps[4], ps[5], ps[6], ps[7], ps[8], ps[9], ps[10], ps[11]); //Déclare le client trouvé
                            setClient(client);
                        }
                        
                    }
			reader.close();
                        System.out.println(found);
                        return found;
		} catch (IOException e) {
			e.printStackTrace();
		}
                return found;
	}
    
    public void setClient(Client c){this.client = c;}
    public Client getClient(){return client;}
    public String getLigneOut(){return ligneOut;}
    public int getIntNextID(){return intNextID;}
}