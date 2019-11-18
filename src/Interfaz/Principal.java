/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Estructuras.ArbolAVL;
import Estructuras.ListaEnlazada;
import Estructuras.MatrizAdy;
import Estructuras.Nodos.AVLNode;
import Estructuras.Nodos.NodoLista;
import Estructuras.Nodos.Vertice;
import Estructuras.Pila;
import Estructuras.TablaHash;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import proyecto2.Objetos.ArchivoObj;
import proyecto2.Objetos.CarpetaObj;
import proyecto2.Objetos.Registro;
import proyecto2.Objetos.Usuario;
import proyecto2.Objetos.UsuarioError;

/**
 *
 * @author brest12
 */
public class Principal extends javax.swing.JFrame {

    TablaHash users;
    MatrizAdy carpetas;
    Usuario currentUser;
    int foldersCount;
    CarpetaObj currentFolder;
    CarpetaObj lastFolder;
    boolean showRep, showMenu;
    ListaEnlazada listaErrores;
    Archivo selectedFile;
    Carpeta selectedFolder;
    String actualAddress;
    Pila bitacora;
    boolean flag;
    int maxcol;
    public Principal(Usuario username, Boolean admin, TablaHash users, MatrizAdy carpetas, Pila bitacora) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.users = users;
        this.carpetas = carpetas;
        this.currentUser = username;
        this.bitacora = bitacora;
        foldersCount = 0;
        selectedFile = null;
        selectedFolder = null;
        currentFolder = (CarpetaObj)(currentUser.getCarpetas().buscarFila(0).getDato());
        lastFolder = null;
        //panelPrincipal.setLayout(new GridLayout(0,maxcol));
        MigLayout m = new MigLayout("wrap 5");
        panelPrinc.setLayout(m);
        labelUsername.setText(username.getUsername());
        panelAdmin.setVisible(false);
        if(admin){
            panelAdmin.setVisible(true);
        }
        showRep = true;
        showMenu = true;
        flag = true;
        actualAddress = "/";
        txtFldAddres.setText(actualAddress);
        addFolderstoPanel(currentFolder.getNombre());
        addFilestoPanel(currentFolder);
        
        MigLayout ml = new MigLayout("wrap 1");
        panelReports.removeAll();
        panelReports.setLayout(ml);
        panelReports.add(jLabel4);
        panelReports.add(hashTable);
        panelReports.add(grafoBt);
        panelReports.add(matrizAdyBt);
        panelReports.add(avlTreeBt);
        panelReports.add(bitacoraBt);
        panelReports.add(lblBulk);
        panelReports.add(filesBulkload);
        panelReports.add(panelAdmin);
    }
    
    public void addFolderstoPanel(String nombreCarpeta){
        Vertice aux = carpetas.getRoot().getDown();
        Vertice col;
        CarpetaObj folder;
        while (aux != null) {
            folder = (CarpetaObj) aux.getDato();

            if (nombreCarpeta.equals(folder.getNombre())) {
                col = aux.getRight();
                while (col != null) {
                    String nombre = ((CarpetaObj) col.getUp().getDato()).getNombre();
                    Carpeta folderButton = new Carpeta(new CarpetaObj(nombre, 
                            ((CarpetaObj)col.getDato()).getArchivos(),nombre));
                    panelPrinc.add(folderButton);
                    panelPrinc.revalidate();
                    col = col.getRight();
                }
                break;
            }
            aux = aux.getDown();
        }
    }
    
    public void addFilestoPanel(CarpetaObj carpeta) {
        ArbolAVL a = carpeta.getArchivos();
        if(!a.isEmpty()){
            System.out.println("Converting to list...");
            ListaEnlazada archs = a.convertirALista();
            System.out.println("Files: "+archs.getSize());
            NodoLista h = archs.getHead();
            System.out.println(h);
            while (h != null) {
                Archivo file = new Archivo(((ArchivoObj)h.getData()));
                panelPrinc.add(file);
                panelPrinc.revalidate();                
                h = h.getNext();
            }
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        btMenu = new javax.swing.JButton();
        btMenuGraph = new javax.swing.JButton();
        txtFldAddres = new javax.swing.JTextField();
        prevFolder = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelUsername = new javax.swing.JLabel();
        logOutBt = new javax.swing.JButton();
        folderCreateBt = new rsbuttom.RSButtonMetro();
        folderModifyBt = new rsbuttom.RSButtonMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        folderDelBt = new rsbuttom.RSButtonMetro();
        fileCreateBt = new rsbuttom.RSButtonMetro();
        FileDelBt = new rsbuttom.RSButtonMetro();
        fileModifyBt = new rsbuttom.RSButtonMetro();
        fileShareBt = new rsbuttom.RSButtonMetro();
        panelReports = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        grafoBt = new rsbuttom.RSButtonMetro();
        hashTable = new rsbuttom.RSButtonMetro();
        bitacoraBt = new rsbuttom.RSButtonMetro();
        matrizAdyBt = new rsbuttom.RSButtonMetro();
        avlTreeBt = new rsbuttom.RSButtonMetro();
        lblBulk = new javax.swing.JLabel();
        filesBulkload = new rsbuttom.RSButtonMetro();
        panelAdmin = new javax.swing.JPanel();
        UsersBulkLoad = new rsbuttom.RSButtonMetro();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelPrinc = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setForeground(new java.awt.Color(51, 57, 59));

        panelHeader.setBackground(new java.awt.Color(0, 33, 206));
        panelHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/menu.png"))); // NOI18N
        btMenu.setBorder(null);
        btMenu.setContentAreaFilled(false);
        btMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuActionPerformed(evt);
            }
        });
        panelHeader.add(btMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btMenuGraph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/menu.png"))); // NOI18N
        btMenuGraph.setBorder(null);
        btMenuGraph.setContentAreaFilled(false);
        btMenuGraph.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMenuGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuGraphActionPerformed(evt);
            }
        });
        panelHeader.add(btMenuGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, -1, -1));

        txtFldAddres.setBackground(new java.awt.Color(59, 52, 51));
        txtFldAddres.setForeground(new java.awt.Color(211, 211, 211));
        txtFldAddres.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFldAddres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFldAddresFocusLost(evt);
            }
        });
        txtFldAddres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFldAddresActionPerformed(evt);
            }
        });
        txtFldAddres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFldAddresKeyReleased(evt);
            }
        });
        panelHeader.add(txtFldAddres, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 450, 30));

        prevFolder.setBackground(new java.awt.Color(59, 52, 51));
        prevFolder.setForeground(new java.awt.Color(59, 52, 51));
        prevFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/prevFolder.png"))); // NOI18N
        prevFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevFolderActionPerformed(evt);
            }
        });
        panelHeader.add(prevFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        panelMenu.setBackground(new java.awt.Color(183, 183, 183));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(183, 183, 183));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 58, 58));
        jLabel1.setText("CARPETAS");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/Workspace.png"))); // NOI18N

        labelUsername.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelUsername.setForeground(new java.awt.Color(255, 255, 255));
        labelUsername.setText("UsuarioName");

        logOutBt.setBackground(new java.awt.Color(183, 183, 183));
        logOutBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/logout.png"))); // NOI18N
        logOutBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelUsername)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(logOutBt))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(labelUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logOutBt)
                        .addGap(13, 13, 13)))
                .addComponent(jLabel1))
        );

        panelMenu.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, -1));

        folderCreateBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/AddFolder.png"))); // NOI18N
        folderCreateBt.setText("CREAR");
        folderCreateBt.setColorNormal(new java.awt.Color(183, 183, 183));
        folderCreateBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        folderCreateBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        folderCreateBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        folderCreateBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        folderCreateBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        folderCreateBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        folderCreateBt.setIconTextGap(25);
        folderCreateBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderCreateBtActionPerformed(evt);
            }
        });
        panelMenu.add(folderCreateBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 174, 50));

        folderModifyBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/ModifyFolder.png"))); // NOI18N
        folderModifyBt.setText("MODIFICAR");
        folderModifyBt.setColorNormal(new java.awt.Color(183, 183, 183));
        folderModifyBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        folderModifyBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        folderModifyBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        folderModifyBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        folderModifyBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        folderModifyBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        folderModifyBt.setIconTextGap(25);
        folderModifyBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderModifyBtActionPerformed(evt);
            }
        });
        panelMenu.add(folderModifyBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 174, 50));

        jPanel5.setBackground(new java.awt.Color(183, 183, 183));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 58, 58));
        jLabel2.setText("ARCHIVOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        panelMenu.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 174, 50));

        folderDelBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/DeleteFolder.png"))); // NOI18N
        folderDelBt.setText("ELIMINAR");
        folderDelBt.setColorNormal(new java.awt.Color(183, 183, 183));
        folderDelBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        folderDelBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        folderDelBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        folderDelBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        folderDelBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        folderDelBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        folderDelBt.setIconTextGap(25);
        folderDelBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderDelBtActionPerformed(evt);
            }
        });
        panelMenu.add(folderDelBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 174, 50));

        fileCreateBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/NewFile.png"))); // NOI18N
        fileCreateBt.setText("CREAR");
        fileCreateBt.setColorNormal(new java.awt.Color(183, 183, 183));
        fileCreateBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        fileCreateBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        fileCreateBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        fileCreateBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        fileCreateBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fileCreateBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        fileCreateBt.setIconTextGap(25);
        fileCreateBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileCreateBtActionPerformed(evt);
            }
        });
        panelMenu.add(fileCreateBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 174, 50));

        FileDelBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/DeleteFile.png"))); // NOI18N
        FileDelBt.setText("ELIMINAR");
        FileDelBt.setColorNormal(new java.awt.Color(183, 183, 183));
        FileDelBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        FileDelBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        FileDelBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        FileDelBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        FileDelBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FileDelBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        FileDelBt.setIconTextGap(25);
        FileDelBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileDelBtActionPerformed(evt);
            }
        });
        panelMenu.add(FileDelBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 174, 50));

        fileModifyBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/ModifyFile.png"))); // NOI18N
        fileModifyBt.setText("MODIFICAR");
        fileModifyBt.setColorNormal(new java.awt.Color(183, 183, 183));
        fileModifyBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        fileModifyBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        fileModifyBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        fileModifyBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        fileModifyBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fileModifyBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        fileModifyBt.setIconTextGap(25);
        fileModifyBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileModifyBtActionPerformed(evt);
            }
        });
        panelMenu.add(fileModifyBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 174, 50));

        fileShareBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/share.png"))); // NOI18N
        fileShareBt.setText("COMPARTIR");
        fileShareBt.setColorNormal(new java.awt.Color(183, 183, 183));
        fileShareBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        fileShareBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        fileShareBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        fileShareBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        fileShareBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fileShareBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        fileShareBt.setIconTextGap(25);
        fileShareBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileShareBtActionPerformed(evt);
            }
        });
        panelMenu.add(fileShareBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 174, 50));

        panelReports.setBackground(new java.awt.Color(183, 183, 183));
        panelReports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(58, 58, 58));
        jLabel4.setText("REPORTS");
        panelReports.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        grafoBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/hashTable.png"))); // NOI18N
        grafoBt.setText("GRAFO");
        grafoBt.setColorNormal(new java.awt.Color(183, 183, 183));
        grafoBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        grafoBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        grafoBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        grafoBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        grafoBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        grafoBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        grafoBt.setIconTextGap(25);
        grafoBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grafoBtActionPerformed(evt);
            }
        });
        panelReports.add(grafoBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        hashTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/hashTable2.png"))); // NOI18N
        hashTable.setText("HASH TABLE");
        hashTable.setColorNormal(new java.awt.Color(183, 183, 183));
        hashTable.setColorTextHover(new java.awt.Color(58, 58, 58));
        hashTable.setColorTextNormal(new java.awt.Color(58, 58, 58));
        hashTable.setColorTextPressed(new java.awt.Color(58, 58, 58));
        hashTable.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        hashTable.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hashTable.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        hashTable.setIconTextGap(25);
        hashTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hashTableActionPerformed(evt);
            }
        });
        panelReports.add(hashTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 160, 50));

        bitacoraBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/history.png"))); // NOI18N
        bitacoraBt.setText("BITACORA");
        bitacoraBt.setColorNormal(new java.awt.Color(183, 183, 183));
        bitacoraBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        bitacoraBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        bitacoraBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        bitacoraBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        bitacoraBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bitacoraBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bitacoraBt.setIconTextGap(25);
        bitacoraBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bitacoraBtActionPerformed(evt);
            }
        });
        panelReports.add(bitacoraBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 50));

        matrizAdyBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/Matrix.png"))); // NOI18N
        matrizAdyBt.setText("MATRIZ ADY");
        matrizAdyBt.setColorNormal(new java.awt.Color(183, 183, 183));
        matrizAdyBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        matrizAdyBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        matrizAdyBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        matrizAdyBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        matrizAdyBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        matrizAdyBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        matrizAdyBt.setIconTextGap(25);
        matrizAdyBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matrizAdyBtActionPerformed(evt);
            }
        });
        panelReports.add(matrizAdyBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 174, 50));

        avlTreeBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/tree.png"))); // NOI18N
        avlTreeBt.setText("ARBOL AVL");
        avlTreeBt.setColorNormal(new java.awt.Color(183, 183, 183));
        avlTreeBt.setColorTextHover(new java.awt.Color(58, 58, 58));
        avlTreeBt.setColorTextNormal(new java.awt.Color(58, 58, 58));
        avlTreeBt.setColorTextPressed(new java.awt.Color(58, 58, 58));
        avlTreeBt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        avlTreeBt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        avlTreeBt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        avlTreeBt.setIconTextGap(25);
        avlTreeBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avlTreeBtActionPerformed(evt);
            }
        });
        panelReports.add(avlTreeBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 174, 50));

        lblBulk.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblBulk.setForeground(new java.awt.Color(58, 58, 58));
        lblBulk.setText("BULK LOAD");
        panelReports.add(lblBulk, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, 33));

        filesBulkload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/importcsv.png"))); // NOI18N
        filesBulkload.setText("FILES LOAD");
        filesBulkload.setColorNormal(new java.awt.Color(183, 183, 183));
        filesBulkload.setColorTextHover(new java.awt.Color(58, 58, 58));
        filesBulkload.setColorTextNormal(new java.awt.Color(58, 58, 58));
        filesBulkload.setColorTextPressed(new java.awt.Color(58, 58, 58));
        filesBulkload.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        filesBulkload.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        filesBulkload.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        filesBulkload.setIconTextGap(25);
        filesBulkload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filesBulkloadActionPerformed(evt);
            }
        });
        panelReports.add(filesBulkload, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 174, 50));

        panelAdmin.setBackground(new java.awt.Color(183, 183, 183));

        UsersBulkLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaz/Icons/importcsv.png"))); // NOI18N
        UsersBulkLoad.setText("USERS LOAD");
        UsersBulkLoad.setColorNormal(new java.awt.Color(183, 183, 183));
        UsersBulkLoad.setColorTextHover(new java.awt.Color(58, 58, 58));
        UsersBulkLoad.setColorTextNormal(new java.awt.Color(58, 58, 58));
        UsersBulkLoad.setColorTextPressed(new java.awt.Color(58, 58, 58));
        UsersBulkLoad.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        UsersBulkLoad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UsersBulkLoad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        UsersBulkLoad.setIconTextGap(25);
        UsersBulkLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsersBulkLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAdminLayout = new javax.swing.GroupLayout(panelAdmin);
        panelAdmin.setLayout(panelAdminLayout);
        panelAdminLayout.setHorizontalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addComponent(UsersBulkLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAdminLayout.setVerticalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UsersBulkLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelReports.add(panelAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 190, 80));

        panelPrinc.setBackground(new java.awt.Color(59, 52, 51));
        panelPrinc.setForeground(new java.awt.Color(36, 40, 41));
        panelPrinc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelPrincFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panelPrincLayout = new javax.swing.GroupLayout(panelPrinc);
        panelPrinc.setLayout(panelPrincLayout);
        panelPrincLayout.setHorizontalGroup(
            panelPrincLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );
        panelPrincLayout.setVerticalGroup(
            panelPrincLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelPrinc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void folderDelBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderDelBtActionPerformed
        if(selectedFolder != null){
            ListaEnlazada hijos = currentUser.getCarpetas().getHijos(selectedFolder.getCarpeta().getNombre());
            currentUser.getCarpetas().eliminarVertice(selectedFolder.getText());
            //Borrando hijos de la carpeta
            String auxSon;
            for(int i = 0; i < hijos.getSize(); i++){
                auxSon = hijos.obtainCarpeta(i).getNombre();
                currentUser.getCarpetas().eliminarVertice(auxSon);
            }
            agregarRegistro("Se elimino la carpeta \\\""+selectedFolder.getText()+"\\\"");
            panelPrinc.remove(selectedFolder);
            panelPrinc.revalidate();
            panelPrinc.repaint();  
        }else{
            JOptionPane.showMessageDialog(this, "Seleccionar una carpeta primero");            
        }
    }//GEN-LAST:event_folderDelBtActionPerformed

    private void fileCreateBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileCreateBtActionPerformed
        String nombre = JOptionPane.showInputDialog("Nombre de Archivo: ");
        if (nombre != null) {
            if (nombre.length() > 0) {
                Timestamp time = new Timestamp(System.currentTimeMillis());
                ArchivoObj newFile = new ArchivoObj(nombre, "", time.toString(),
                        currentUser.getUsername());
                if (!currentFolder.getArchivos().contains(nombre)) {
                    Archivo file = new Archivo(newFile);
                    panelPrinc.add(file);
                    panelPrinc.revalidate();
                    currentFolder.getArchivos().insert(newFile);
                    agregarRegistro("Se creo un archivo llamado: "+nombre);
                if (selectedFolder != null) {
                    selectedFolder.setSelected(false);
                }
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe un archivo "
                            + "llamado "+nombre);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nombre Invalido!");
            }
        }
    }//GEN-LAST:event_fileCreateBtActionPerformed

    private void FileDelBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileDelBtActionPerformed
        if(selectedFile != null){
            deleteFile(selectedFile.getArchivo());
        }else{
            JOptionPane.showMessageDialog(this, "Seleccionar un Archivo primero");               
        }
    }//GEN-LAST:event_FileDelBtActionPerformed
    
    private void deleteFile(ArchivoObj file){
        agregarRegistro("Se elimino el archivo \\\""+file.getNombre()+"\\\"");
        currentFolder.getArchivos().remove(file);
        panelPrinc.remove(selectedFile);
        panelPrinc.revalidate();
        panelPrinc.repaint();
    }
    
    private void fileModifyBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileModifyBtActionPerformed
        if(selectedFile != null){    
            modifyFile(selectedFile.getArchivo());
        }else{
            JOptionPane.showMessageDialog(this, "Seleccionar un Archivo primero");   
        }
    }//GEN-LAST:event_fileModifyBtActionPerformed

    private void modifyFile(ArchivoObj file){
        String[] options = {"Nombre del Archivo", "Contenido del Archivo", "Nada"};
        int op = JOptionPane.showOptionDialog(this, "Archivo: "+file.getNombre()
                + "\n¿Que desea modificar?",
                "Modificar", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if(op == 0){
            String nombre = JOptionPane.showInputDialog("Nuevo nombre de archivo");
            agregarRegistro("Se modifico el nombre del archivo \\\""+selectedFile.getNombre()
            +" su nuevo nombre es: \\\""+nombre+"\\\"");
            file.setNombre(nombre);
            selectedFile.setText(nombre);
        }else if(op == 1){
            ContentViewer cv = new ContentViewer(file);
            cv.setVisible(true);
            agregarRegistro("Se modifico el contenido del archivo \\\""+selectedFile.realNombre+"\\\"");
        }
    }
    private void btMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMenuActionPerformed
        int pos = panelMenu.getX();
        int posf;
        if(showMenu){
            posf = pos - panelMenu.getWidth();
            Animacion.Animacion.mover_izquierda(pos, posf, 2, 2, panelMenu);
            showMenu = false;
        }else{
            posf = pos + panelMenu.getWidth();
            Animacion.Animacion.mover_derecha(pos, posf, 2, 2, panelMenu);
            showMenu = true;
        }
    }//GEN-LAST:event_btMenuActionPerformed

    private void folderCreateBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderCreateBtActionPerformed
        String nombre = JOptionPane.showInputDialog("Nombre de carpeta: ");
        if (nombre != null) {
            if(nombre.length() > 0){
            CarpetaObj c = new CarpetaObj(nombre, new ArbolAVL(), currentFolder.getNombre());                
                Carpeta folderButton = new Carpeta(c);
                
                panelPrinc.add(folderButton);
                panelPrinc.revalidate();               
                if (selectedFile != null) {
                    selectedFile.setSeleccionado(false);
                }
                currentUser.addCarpeta(currentFolder.getNombre(), c.getNombre());
                agregarRegistro("Se creo una carpeta llamada \\\""+nombre+"\\\"");
            }
        }
    }//GEN-LAST:event_folderCreateBtActionPerformed
    
    private void agregarRegistro(String operacion){
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        bitacora.push(new Registro(dateFormat.format(date),
                hourFormat.format(date), operacion , currentUser.getUsername()));
    }
    private void folderModifyBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderModifyBtActionPerformed
        if(selectedFolder != null){
            String anterior = selectedFolder.getCarpeta().getNombre();
            String nombreAnterior = selectedFolder.getText();
            String nombreNuevo = JOptionPane.showInputDialog("Modificar: "
                    + selectedFolder.getText()
                    + "\nNuevo nombre");
            if(nombreNuevo != null && nombreNuevo.length() > 0){
                MatrizAdy aux = currentUser.getCarpetas();
                ((CarpetaObj)aux.buscarFila(anterior).getDato()).setNombre(nombreNuevo);
                String nombreNodo = currentFolder.getNombre();
                if(nombreNodo.equals("/")){
                    nombreNodo = "/"+nombreAnterior;
                    System.out.println(nombreNodo);
                    ((CarpetaObj)aux.buscarNodo("/",nombreNodo).getDato()).setNombre("/"+nombreNuevo);
                }else{
                    nombreNodo += "/"+nombreAnterior;
                    ((CarpetaObj)aux.buscarNodo(currentFolder.getNombre()
                            ,nombreNodo).getDato())
                            .setNombre(currentFolder.getNombre()+"/"+nombreNuevo);
                }
                selectedFolder.setText(nombreNuevo);
                JOptionPane.showMessageDialog(this, "Carpeta Modificada!");
                agregarRegistro("Se modifico la carpeta \\\""+nombreAnterior+"\\\""
                        + "su nuevo nombre es \\\""+nombreNuevo+"\\\"");
            }else{
                JOptionPane.showMessageDialog(this, "Nombre Invalido!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Seleccionar una carpeta primero");   
        }
    }//GEN-LAST:event_folderModifyBtActionPerformed

    private void UsersBulkLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsersBulkLoadActionPerformed
        JFileChooser choose = new JFileChooser(".");
        BufferedReader br;
        int f = choose.showOpenDialog(null);
        String nombreArch = "";
        listaErrores = new ListaEnlazada();
        int count = 0;
        int counterr = 0;
        String csvFile = "";
        if(f == JFileChooser.APPROVE_OPTION){
            //labelUsername.setText(choose.getSelectedFile());
            nombreArch = choose.getSelectedFile().getName();
            String line;
            int n = 0;
            int colus = 0;
            boolean userIsFirst = false;
            csvFile = choose.getSelectedFile().getAbsolutePath();
            try {
                br = new BufferedReader(new FileReader(csvFile));
                String name;
                String pass;
                String usuario[];

                while((line = br.readLine()) != null){
                    usuario = line.split(",");
                    if(n == 0){
                        
                        if("usuario".equals(usuario[0].toLowerCase())){
                            userIsFirst = true;           
                            colus = 0;
                        }else if("password".equals(usuario[0].toLowerCase())){
                            userIsFirst = false;
                            colus = 1;
                        }else{
                            break;
                        }
                    }else if(n > 0){
                        if(userIsFirst){
                            name = usuario[0];
                            pass = usuario[1];
                            System.out.println(name);;
                        }else{
                            name = usuario[1];
                            pass = usuario[0];
                            
                        }
                        if(!users.contains(name)){
                            if(isLongEnough(pass)){
                                Timestamp time = new Timestamp(System.currentTimeMillis());
                                users.insertar(new Usuario(name, pass, time, false));
                                count++;
                            }else{
                                if(agregarListaError(name, pass, 102, n, colus)){
                                    counterr++;
                                }
                            }
                        }else{
                            if(agregarListaError(name, pass, 101, n, colus)){
                                counterr++;
                            }
                        }
                    }
                    n++;
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(this, "Se cargaron "+count+" usuarios correctamente");
        agregarRegistro("Se cargaron"+count+" usuarios correctamente desde \\\""+csvFile+"\\\"");
        if (listaErrores.getSize() > 0) {
            int op = JOptionPane.showConfirmDialog(this, counterr+" usuarios no se cargaron"
                    + "\n ¿Desea ver la lista?");
            agregarRegistro("Desde \\\""+csvFile+"\\\" no se pudieron cargar "+counterr+" usuarios");
            if (op == JOptionPane.YES_OPTION) {
                FrameErrores err = new FrameErrores(listaErrores, nombreArch);
                err.setVisible(true);
            }
        }
    }//GEN-LAST:event_UsersBulkLoadActionPerformed
    
    private boolean agregarListaError(String user, String pass, int tipoError, int linea, int columna){
        //tipoError = 101 -> User already exists ----- tipoError = 102 --> Password < 8

        String mensaje = "";
        if (tipoError == 101) {
            mensaje = "Nombre de usuario ya existe";
        } else if (tipoError == 102) {
            mensaje = "Contraseña menor a 8 caracteres";
            if (columna == 1) {
                columna = 0;
            } else if (columna == 0) {
                columna = 1;
            }
        }
        UsuarioError err = new UsuarioError(user, pass, linea, columna, mensaje);
        return listaErrores.insertErr(err);
    }
    private boolean isLongEnough(String pass){
        return pass.length() >= 8;
    }
    private void logOutBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtActionPerformed
        int opt = JOptionPane.showConfirmDialog(this, "Log out?");
        if(opt == 0){
            this.dispose();
            LoginFrame lg = new LoginFrame(users, bitacora);
            lg.setVisible(true);
        }
    }//GEN-LAST:event_logOutBtActionPerformed

    private void filesBulkloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filesBulkloadActionPerformed
        JFileChooser choose = new JFileChooser(".");
        BufferedReader br;
        Timestamp time;
        boolean success = true;
        String csvFile = "";
        int f = choose.showOpenDialog(null);
        if(f == JFileChooser.APPROVE_OPTION){
            String line;
            int n = 0;
            boolean fileIsFirst = false;
            csvFile = choose.getSelectedFile().getAbsolutePath();
            try {
                br = new BufferedReader(new FileReader(csvFile));
                String name;
                String content;
                String file[];
                while((line = br.readLine()) != null){
                    file = line.split(",");
                    System.out.println(file[0].toLowerCase());
                    if(n == 0){
                        if("archivo".equals(file[0].toLowerCase())){
                            fileIsFirst = true;
                            System.out.println("file");
                        }else if("contenido".equals(file[0].toLowerCase())){
                            fileIsFirst = false;
                        }else{
                            JOptionPane.showMessageDialog(this, "CSV Invalido!");
                            success = false;
                            break;
                        }
                    }else if(n > 0){
                        if(fileIsFirst){
                            name = file[0];
                            content = file[1];
                            System.out.println(name);
                        }else{
                            name = file[1];
                            content = file[0];
                            
                        }
                        time = new Timestamp(System.currentTimeMillis());
                        ArbolAVL t = currentFolder.getArchivos();
                        AVLNode node = new AVLNode(new ArchivoObj(name, content, time.toString(), currentUser.getUsername()));
                        if(!t.contains(node)){
                            content = content.replaceAll("”", "");
                            content = content.replaceAll("\"", "");
                            content = content.replaceAll("“", "");
                                t.insert(new ArchivoObj(name, content,
                                        time.toString(), currentUser.getUsername()));
                        }else{
                            int opt = JOptionPane.showConfirmDialog(this,
                                    "El archivo "+name+" ya existe!\n"
                                            + "¿Desea sobreescribirlo?");
                            if(opt == 0){
                                content = content.replaceAll("”", "");
                                content = content.replaceAll("\"", "");
                                content = content.replaceAll("“", "");
                                ArchivoObj arch = new ArchivoObj(name, content,
                                        time.toString(), currentUser.getUsername());
                                System.out.println(t.remove(arch));
                                t.insert(arch);
                            }
                        }
                        
                    }
                    n++;
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                success = false;
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                success = false;
            }
        }
        panelPrinc.removeAll();
        addFolderstoPanel(currentFolder.getNombre());
        addFilestoPanel(currentFolder);
        if(success){
            agregarRegistro("Se agregaron archivos masivamente desde: \\\""+csvFile+"\\\"");
        }
    }//GEN-LAST:event_filesBulkloadActionPerformed

    private void btMenuGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMenuGraphActionPerformed
        int pos = panelReports.getX();
        int posf;
        System.out.println(pos);
        if(showRep){
            posf = pos+panelReports.getWidth();
            Animacion.Animacion.mover_derecha(pos, posf, 2, 2, panelReports);
            showRep = false;
        }else{
            posf = pos-panelReports.getWidth();
            Animacion.Animacion.mover_izquierda(pos, posf, 2, 2, panelReports);
            showRep = true;
        }
    }//GEN-LAST:event_btMenuGraphActionPerformed

    private void hashTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hashTableActionPerformed
        users.graficar();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageViewer img = new ImageViewer("./Reports/UsersHashTable.png");
        img.setVisible(true);
    }//GEN-LAST:event_hashTableActionPerformed

    private void grafoBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grafoBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grafoBtActionPerformed

    private void bitacoraBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bitacoraBtActionPerformed
        bitacora.graficar();
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageViewer img = new ImageViewer("./Reports/pila.png");
        img.setVisible(true); 
    }//GEN-LAST:event_bitacoraBtActionPerformed

    private void matrizAdyBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matrizAdyBtActionPerformed
        carpetas.graficar();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageViewer img = new ImageViewer("./Reports/MatrizAdy.png");
        img.setVisible(true);        
    }//GEN-LAST:event_matrizAdyBtActionPerformed

    private void avlTreeBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avlTreeBtActionPerformed
        currentFolder.getArchivos().generateGraph();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageViewer img = new ImageViewer("./Reports/AVLTree.png");
        img.setVisible(true);
    }//GEN-LAST:event_avlTreeBtActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void txtFldAddresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFldAddresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFldAddresActionPerformed

    private void txtFldAddresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFldAddresFocusLost
        System.out.println(flag);
        if(flag){
            System.out.println("hmm.");
            buscarDireccion();
        }else{
            flag = true;
        }
    }//GEN-LAST:event_txtFldAddresFocusLost
    private void buscarDireccion(){
       /*String tryAddres = txtFldAddres.getText();
       System.out.println("1: "+tryAddres);
        if(tryAddres.length() > 0){
            int i;
            if(tryAddres.length() > 1 && tryAddres.endsWith("/")){
                tryAddres = tryAddres.substring(0, tryAddres.lastIndexOf("/"));
                i = tryAddres.lastIndexOf("/");                
            }else{
                tryAddres = "/";
                i = tryAddres.lastIndexOf("/")-1;
            }
            
            System.out.println("2: "+tryAddres.substring(i+1, tryAddres.length()));
            String tryFolder = tryAddres.substring(i+1, tryAddres.length());
            Vertice tryV = currentUser.getCarpetas().buscarFila(tryFolder);
            if (tryV != null) {
                panelPrinc.removeAll();
                panelPrinc.revalidate();
                panelPrinc.repaint();
                String nombre = ((CarpetaObj) tryV.getDato()).getNombre();
                currentFolder = (CarpetaObj) currentUser.getCarpetas()
                        .buscarFila(nombre).getDato();
                addFolderstoPanel(currentFolder.getNombre());
                addFilestoPanel(currentFolder);
                txtFldAddres.setText(tryAddres);
            }else{
                JOptionPane.showMessageDialog(this, "Direccion inexistente..");
                txtFldAddres.setText(actualAddress);                
            }
        }else{
            JOptionPane.showMessageDialog(this, "Direccion inexistente!");
            txtFldAddres.setText(actualAddress);
        }*/        
    }
    private void txtFldAddresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFldAddresKeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            flag = false;
            buscarDireccion();
        }
    }//GEN-LAST:event_txtFldAddresKeyReleased

    private void panelPrincFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelPrincFocusGained
        Component comp = panelPrinc.getComponentAt(panelPrinc.getMousePosition());
        if(comp.getClass().toString().contains("Archivo")){
            if (selectedFile != null && selectedFile != comp) {
                selectedFile.setSeleccionado(false);
                if (selectedFolder != null) {
                    selectedFolder.setSelected(false);
                }
            }
            selectedFile = (Archivo) comp;
        } else if (comp.getClass().toString().contains("Carpeta")) {
            Carpeta carpeta = (Carpeta) comp;
            if (carpeta.isSelected()){
                if(selectedFolder != null && selectedFolder != carpeta){
                    selectedFolder.setSelected(false);
                    if(selectedFile != null){
                        selectedFile.setSeleccionado(false);
                    }
                }
                selectedFolder = carpeta;
            }else if(carpeta.isOpened()){
                panelPrinc.removeAll();
                panelPrinc.revalidate();
                panelPrinc.repaint();
                System.out.println("uhmmm");
                lastFolder = currentFolder;
                if(selectedFolder != null){
                    selectedFolder.setOpen(false);
                }
                selectedFolder = (Carpeta) comp;
                currentFolder = (CarpetaObj)currentUser.getCarpetas()
                    .buscarFila(selectedFolder.getCarpeta().getNombre()).getDato();
                addFolderstoPanel(currentFolder.getNombre());
                addFilestoPanel(currentFolder);
                actualAddress = txtFldAddres.getText()+"/"+currentFolder.getNombre();
                if(txtFldAddres.getText().equals("/")){
                    actualAddress = "/"+currentFolder.getNombre();
                }
                selectedFolder = null;
                txtFldAddres.setText(actualAddress);                
            }
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_panelPrincFocusGained

    private void prevFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevFolderActionPerformed
        if(lastFolder != null){
            lastFolder = (CarpetaObj)currentUser.getCarpetas()
                    .buscarFila(currentFolder.getPadre()).getDato();
            currentFolder = lastFolder;
                panelPrinc.removeAll();
                panelPrinc.revalidate();
                panelPrinc.repaint();
                addFolderstoPanel(currentFolder.getNombre());
                addFilestoPanel(currentFolder);
                
        }
    }//GEN-LAST:event_prevFolderActionPerformed

    private void fileShareBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileShareBtActionPerformed
        int op =JOptionPane.showConfirmDialog(this, 
                "Desea compartir el archivo: "+selectedFile.getNombre());
        if(op == 0){
            String shareUser = JOptionPane.showInputDialog("Escriba el nombre del usuario destino:");   
            if(shareUser != null){
                Usuario tmp = users.buscarPorNombre(shareUser);
                if(tmp != null){
                    if(!shareFile(tmp, selectedFile.getArchivo(), selectedFile.getNombre())){
                        JOptionPane.showMessageDialog(this, "No se compartio nada!");
                    }else{
                        agregarRegistro("Se compartio el archivo \\\""+selectedFile.getNombre()
                        +"\\\" al usuario \\\""+tmp.getUsername()+"\\\"");
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "El usuario no existe!");
                }
            }
            
        }
    }//GEN-LAST:event_fileShareBtActionPerformed
    
    private boolean shareFile(Usuario shareUser, ArchivoObj sArchivo, String nombreArchivo){
        Usuario tmp = shareUser;
        ArchivoObj newFile = new ArchivoObj(nombreArchivo,
                sArchivo.getContenido(),
                 sArchivo.getTimestamp(),
                 tmp.getUsername());
        boolean suc = ((CarpetaObj) tmp.getCarpetas().buscarFila(0).getDato())
                .getArchivos().insert(newFile);
        if (suc) {
            JOptionPane.showMessageDialog(this, "Se compartio el archivo satisfactoriamente con"
                    + " " + tmp.getUsername());
            return true;
        } else {
            String nuevoNombre = JOptionPane.showInputDialog("El usuario " + tmp.getUsername()
                    + " ya tiene un archivo\n con ese nombre.\nEscoja un nuevo nombre");
            if(nuevoNombre != null){
                return shareFile(shareUser, sArchivo, nuevoNombre);
            }
            return false;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal(null,false, null,null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro FileDelBt;
    private rsbuttom.RSButtonMetro UsersBulkLoad;
    private rsbuttom.RSButtonMetro avlTreeBt;
    private rsbuttom.RSButtonMetro bitacoraBt;
    private javax.swing.JButton btMenu;
    private javax.swing.JButton btMenuGraph;
    private rsbuttom.RSButtonMetro fileCreateBt;
    private rsbuttom.RSButtonMetro fileModifyBt;
    private rsbuttom.RSButtonMetro fileShareBt;
    private rsbuttom.RSButtonMetro filesBulkload;
    private rsbuttom.RSButtonMetro folderCreateBt;
    private rsbuttom.RSButtonMetro folderDelBt;
    private rsbuttom.RSButtonMetro folderModifyBt;
    private rsbuttom.RSButtonMetro grafoBt;
    private rsbuttom.RSButtonMetro hashTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JLabel lblBulk;
    private javax.swing.JButton logOutBt;
    private rsbuttom.RSButtonMetro matrizAdyBt;
    private javax.swing.JPanel panelAdmin;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelPrinc;
    private javax.swing.JPanel panelReports;
    private javax.swing.JButton prevFolder;
    private javax.swing.JTextField txtFldAddres;
    // End of variables declaration//GEN-END:variables
}
