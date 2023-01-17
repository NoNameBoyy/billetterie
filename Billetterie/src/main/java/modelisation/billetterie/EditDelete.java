/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelisation.billetterie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vanessa Kenniche & Thomas Fernandes
 */
public class EditDelete {

    private static String doc = WriterReader.path()+"\\clients.csv";
    
        //**************DELETE***************\\

    private int finalLine;
    public void setFinalLine(int finalLine) {this.finalLine = finalLine;}    
    public int getFinalLine() {return finalLine;}
    private ArrayList<String> allClients = new ArrayList<String>();
    
    public void Delete(Client c) {
        int cpt = 0;
         String tempDoc = WriterReader.path()+"\\clients2.csv";
         String path = WriterReader.path()+"\\clients.csv";
         File oldDoc = new File(path);
         File newDoc = new File(tempDoc);
    //1 Lit les informations du fichier dans un ArrayList 
        try{
            //******************RECHERCHE DU CLIENT********************\\
            File f = new File(path);
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr); 
            
    
            while(br.ready()){
                cpt++;
                String lecture = br.readLine();
                String[] info = lecture.split(",");
                if(info[7].trim().equalsIgnoreCase(c.getNumTel().trim())   || c.getNumTel().trim().equals("")){
                System.out.format("Trouvé");
                setFinalLine(cpt-1);
                }

            }
            br.close();
            fr.close();
            
            Scanner s = new Scanner(oldDoc);
            while(s.hasNextLine()){
                allClients.add(s.nextLine());
            }
            
    //2 supprime la ligne du client désiré et met à jour la ligne si des informations de mise à jour ont été détectées

            allClients.remove(getFinalLine());
            if(getNewClient()!=null){
                allClients.add(getNewClient());
            }

    //3 réécriture des nouvelles informations dans le nouveau fichier 
            FileWriter fw = new FileWriter(tempDoc, true);
            BufferedWriter writer = new BufferedWriter(fw);
            for(String x: allClients){
                writer.write(x);
                writer.newLine();
            }
            
            writer.flush();
            //s.close();
            writer.close();
            fw.close();
            s.close();
            allClients = null;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        oldDoc.delete();
        File dump = new File(path);
        newDoc.renameTo(dump);
    }
    
    
    private String newClient = null;
    
    //Informations de mise à jour
    public void setNewClient(String newClient){this.newClient = newClient;}

    public String getNewClient() {
        return newClient;
    }

}
