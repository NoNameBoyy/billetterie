/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package modelisation.billetterie;

import com.formdev.flatlaf.FlatDarkLaf;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Vanessa Kenniche & Thomas Fernandes
 */
public class VisualisationClients extends javax.swing.JFrame {

    /**
     * Creates new form VisualisationClients
     */
    public VisualisationClients() {
        initComponents();
        
        //Création d'un file pour le csv et d'un modèle de table
        File csv_file = new File(WriterReader.path()+"\\Clients.csv");
        DefaultTableModel csv_data = new DefaultTableModel();
        
        try{
            //Peuplement de la table, utilisation d'un boolean pour distinguer
            //la première ligne (nom des colonnes) du reste
            boolean start = true;
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(csv_file));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
            for (CSVRecord csvRecord : csvParser){
                if (start){
                    start = false;
                    csv_data.addColumn("ID Client");
                    csv_data.addColumn(csvRecord.get(1));
                    csv_data.addColumn(csvRecord.get(2));
                    csv_data.addColumn(csvRecord.get(3));
                    csv_data.addColumn(csvRecord.get(4));
                    csv_data.addColumn(csvRecord.get(5));
                    csv_data.addColumn(csvRecord.get(6));
                    csv_data.addColumn(csvRecord.get(7));
                    csv_data.addColumn(csvRecord.get(8));
                    csv_data.addColumn(csvRecord.get(9));
                    csv_data.addColumn(csvRecord.get(10));
                    csv_data.addColumn(csvRecord.get(11));
                } else {
                    Vector<String> row = new Vector<String>();
                    row.add(csvRecord.get(0));
                    row.add(csvRecord.get(1));
                    row.add(csvRecord.get(2));
                    row.add(csvRecord.get(3));
                    row.add(csvRecord.get(4));
                    row.add(csvRecord.get(5));
                    row.add(csvRecord.get(6));
                    row.add(csvRecord.get(7));
                    row.add(csvRecord.get(8));
                    row.add(csvRecord.get(9));
                    row.add(csvRecord.get(10));
                    row.add(csvRecord.get(11));
                    csv_data.addRow(row);
                }
            }
        }
        catch (IOException e){
            System.out.println("Error in Parsing CSV File");
        }
        //Assignation du modèle à la table pour pouvoir ensuite utiliser les fonctions de tri
        jTable1.setModel(csv_data);
    }
    
    public void filter(){
        //Instanciation des variables de tri
        String idFilter      = jTextFieldIDClient.getText();
        String nomFilter     = jTextFieldNom.getText();
        String prenomFilter  = jTextFieldPrenom.getText();
        String jourFilter    = (String) jComboBoxJour.getSelectedItem();
        String moisFilter    = (String) jComboBoxMois.getSelectedItem();
        String anneeFilter   = (String) jComboBoxAnnee.getSelectedItem();
        String numtelFilter  = jTextFieldNumTel.getText();
        String adresseFilter = jTextFieldAdresse.getText();
        String tailleFilter  = jTextFieldTaille.getText();
        String categFilter   = (String) jComboBoxCategorie.getSelectedItem();
        String billetFilter  = (String) jComboBoxBillet.getSelectedItem();
        String ptsfidFilter  = jTextFieldFidelite.getText();
        
        //Instanciation d'un TableRowSorter prenant en paramètre le model de jTable1
        //et assignation du sorter au RowSorter du tableau
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        
        //Instanciation d'une ArrayList comme List de RowFilter pour prouver traiter différents filtres
        //simultanément (filtre record par record et non ligne par ligne dans le cas d'un simple TableRowSorter)
        List<RowFilter<Object, Object>> filters = new ArrayList<>(11);
        filters.add(RowFilter.regexFilter("(?i)" + idFilter,      0));
        filters.add(RowFilter.regexFilter("(?i)" + nomFilter,     1));
        filters.add(RowFilter.regexFilter("(?i)" + prenomFilter,  2));
        filters.add(RowFilter.regexFilter("(?i)" + adresseFilter, 6));
        filters.add(RowFilter.regexFilter("(?i)" + numtelFilter,  7));
        filters.add(RowFilter.regexFilter("(?i)" + tailleFilter,  8));
        filters.add(RowFilter.regexFilter("(?i)" + ptsfidFilter, 11));
        
        //Les filtres ci-dessous sont traités différement, étant à l'origine des combobox
        //S'ils étaient traités comme les TextField ci-dessus, un affichage dynamique
        //de la table serait impossible car le filtre prendrait en permanance l'item selectionné
        //dans le ComboBox, même si aucun n'a été selectionné.
        if (!"Jour".equals(jourFilter))
            filters.add(RowFilter.regexFilter("(?i)" + jourFilter,   3));
        if (!"Mois".equals(moisFilter))
            filters.add(RowFilter.regexFilter("(?i)" + moisFilter,   4));
        if (!"Année".equals(anneeFilter))
            filters.add(RowFilter.regexFilter("(?i)" + anneeFilter,  5));
        if (!"--Sélectionner--".equals(billetFilter))
            filters.add(RowFilter.regexFilter("(?i)" + billetFilter, 9));
        if (!"--Sélectionner--".equals(categFilter))
            filters.add(RowFilter.regexFilter("(?i)" + categFilter, 10));
        
        RowFilter<Object, Object> billeterieFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(billeterieFilter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldIDClient = new javax.swing.JTextField();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPrenom = new javax.swing.JTextField();
        jComboBoxJour = new javax.swing.JComboBox<>();
        jComboBoxMois = new javax.swing.JComboBox<>();
        jComboBoxAnnee = new javax.swing.JComboBox<>();
        jTextFieldAdresse = new javax.swing.JTextField();
        jTextFieldNumTel = new javax.swing.JTextField();
        jTextFieldTaille = new javax.swing.JTextField();
        jComboBoxCategorie = new javax.swing.JComboBox<>();
        jTextFieldFidelite = new javax.swing.JTextField();
        jButtonRetour = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jComboBoxBillet = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Recherche filtrée : ");

        jLabel2.setText("N° Client :");

        jLabel3.setText("Nom :");

        jLabel4.setText("Prénom :");

        jLabel5.setText("Date de naissance :");

        jLabel6.setText("Adresse : ");

        jLabel7.setText("Taille : ");

        jLabel8.setText("Numéro de téléphone : ");

        jLabel9.setText("Dernier billet acheté :  ");

        jLabel10.setText("Catégorie : ");

        jLabel11.setText("Points de fidélité :");

        jTextFieldIDClient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldIDClientKeyReleased(evt);
            }
        });

        jTextFieldNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNomKeyReleased(evt);
            }
        });

        jTextFieldPrenom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPrenomKeyReleased(evt);
            }
        });

        jComboBoxJour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jour", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBoxJour.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxJourItemStateChanged(evt);
            }
        });

        jComboBoxMois.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mois", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" }));
        jComboBoxMois.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMoisItemStateChanged(evt);
            }
        });
        jComboBoxMois.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxMoisKeyReleased(evt);
            }
        });

        jComboBoxAnnee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Année", "1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));
        jComboBoxAnnee.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxAnneeItemStateChanged(evt);
            }
        });

        jTextFieldAdresse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAdresseKeyReleased(evt);
            }
        });

        jTextFieldNumTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumTelActionPerformed(evt);
            }
        });
        jTextFieldNumTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNumTelKeyReleased(evt);
            }
        });

        jTextFieldTaille.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTailleKeyReleased(evt);
            }
        });

        jComboBoxCategorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Sélectionner--", "Senior", "Normal", "Junior" }));
        jComboBoxCategorie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCategorieItemStateChanged(evt);
            }
        });

        jTextFieldFidelite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFideliteKeyReleased(evt);
            }
        });

        jButtonRetour.setText("Retour");
        jButtonRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetourActionPerformed(evt);
            }
        });

        jButtonReset.setText("Réinitialiser filtres");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jComboBoxBillet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Sélectionner--", "T1 : 50e 20 attractions", "T2 : 70e 45 attractions", "T3 : 85e Attractions illimitees" }));
        jComboBoxBillet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxBilletItemStateChanged(evt);
            }
        });
        jComboBoxBillet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBilletActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxJour, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxMois, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jTextFieldIDClient)
                            .addComponent(jTextFieldNom)
                            .addComponent(jTextFieldPrenom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.Alignment.TRAILING, 0, 300, Short.MAX_VALUE)
                            .addComponent(jTextFieldTaille, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNumTel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldFidelite)
                            .addComponent(jComboBoxBillet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextFieldIDClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldNumTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBoxJour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldTaille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBoxBillet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBoxAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldFidelite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRetour)
                    .addComponent(jButtonReset))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNumTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumTelActionPerformed

    //Bouton de redirection vers l'accueil
    private void jButtonRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetourActionPerformed
        Accueil a = new Accueil();
        a.show();
        dispose();
    }//GEN-LAST:event_jButtonRetourActionPerformed

    //Filtrage dynamique du TextField
    private void jTextFieldIDClientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDClientKeyReleased
        filter();
    }//GEN-LAST:event_jTextFieldIDClientKeyReleased

    //Filtrage dynamique du TextField
    private void jTextFieldNomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomKeyReleased
        filter();
    }//GEN-LAST:event_jTextFieldNomKeyReleased

    //Filtrage dynamique du TextField
    private void jTextFieldPrenomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrenomKeyReleased
        filter();
    }//GEN-LAST:event_jTextFieldPrenomKeyReleased

    //Filtrage dynamique du ComboBox
    private void jComboBoxJourItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxJourItemStateChanged
        filter();
    }//GEN-LAST:event_jComboBoxJourItemStateChanged

    //Filtrage dynamique du ComboBox
    private void jComboBoxMoisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxMoisKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMoisKeyReleased

    //Filtrage dynamique du ComboBox
    private void jComboBoxMoisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMoisItemStateChanged
        filter();
    }//GEN-LAST:event_jComboBoxMoisItemStateChanged

    //Filtrage dynamique du TextField
    private void jTextFieldAdresseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAdresseKeyReleased
        filter();
    }//GEN-LAST:event_jTextFieldAdresseKeyReleased

    //Filtrage dynamique du TextField
    private void jTextFieldNumTelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumTelKeyReleased
        filter();
    }//GEN-LAST:event_jTextFieldNumTelKeyReleased

    //Filtrage dynamique du TextField
    private void jTextFieldTailleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTailleKeyReleased
        filter();
    }//GEN-LAST:event_jTextFieldTailleKeyReleased

    //Filtrage dynamique du TextField
    private void jTextFieldFideliteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFideliteKeyReleased
        filter();
    }//GEN-LAST:event_jTextFieldFideliteKeyReleased

    //Filtrage dynamique du ComboBox
    private void jComboBoxCategorieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCategorieItemStateChanged
        filter();
    }//GEN-LAST:event_jComboBoxCategorieItemStateChanged

    //Bouton de réinitialisation des filtres (TextFields et ComboBoxes)
    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        jTextFieldIDClient.setText(null);
        jTextFieldNom.setText(null);
        jTextFieldPrenom.setText(null);
        jComboBoxJour.setSelectedIndex(0);
        jComboBoxMois.setSelectedIndex(0);
        jComboBoxAnnee.setSelectedIndex(0);
        jTextFieldAdresse.setText(null);
        jTextFieldNumTel.setText(null);
        jTextFieldTaille.setText(null);
        jComboBoxBillet.setSelectedIndex(0);
        jComboBoxCategorie.setSelectedIndex(0);
        jTextFieldFidelite.setText(null);
        filter();
    }//GEN-LAST:event_jButtonResetActionPerformed

    //Filtrage dynamique du ComboBox
    private void jComboBoxAnneeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxAnneeItemStateChanged
        filter();
    }//GEN-LAST:event_jComboBoxAnneeItemStateChanged

    private void jComboBoxBilletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBilletActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBilletActionPerformed

    //Filtrage dynamique du ComboBox
    private void jComboBoxBilletItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxBilletItemStateChanged
        filter();
    }//GEN-LAST:event_jComboBoxBilletItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualisationClients().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonRetour;
    private javax.swing.JComboBox<String> jComboBoxAnnee;
    private javax.swing.JComboBox<String> jComboBoxBillet;
    private javax.swing.JComboBox<String> jComboBoxCategorie;
    private javax.swing.JComboBox<String> jComboBoxJour;
    private javax.swing.JComboBox<String> jComboBoxMois;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAdresse;
    private javax.swing.JTextField jTextFieldFidelite;
    private javax.swing.JTextField jTextFieldIDClient;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldNumTel;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldTaille;
    // End of variables declaration//GEN-END:variables
}
