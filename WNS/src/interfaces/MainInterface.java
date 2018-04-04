/*
 * Carlos Andrés Reyes Evangelista
 * 157068
 * Ingeniería en Sistemas Computacionales. UDLAP.
 */
package interfaces;

import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;
import wns.*;
import org.netbeans.lib.awtextra.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *@author Juan Carlos
 *@author Reyes
 *@author Dulio Caggiano
 *@author  Jordi Omar 
 */
public class MainInterface extends javax.swing.JFrame {

    /*
    C O L O R   P A L E T T E :   C H A R L Y 
    */
    static Color base      = new Color (24, 41, 70);
    static Color primary   = new Color (14, 20, 32);
    static Color secondary = new Color (21, 31, 51);
    static Color tertiary  = new Color (21, 44, 86);
    static Color light     = new Color (25, 58, 118);
    static Color highlight = new Color (156, 110, 27);
    public static Color foreground= new Color (220, 220, 220);
    int c           = 20;
    
    ViewInventory viewInventory = new ViewInventory();
    
    java.io.File     file;
    RandomAccessFile raf;
    
    //Set of the file to be used in this program
//        java.io.File file               = new java.io.File("products");
//    //Set of the RAF linked to the File file with read and write properties
//        RandomAccessFile raf    = new RandomAccessFile(file, "rw" );
    
    /*
        A U X I L I A R   M E T H O D S
    */
    
    /**
     * Convert a JLabel (lbl) into a modern button
     * @param lbl
     */
    private void makeButton (JLabel lbl) {
        lbl.setOpaque(true);
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl.setForeground(foreground);
        
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deeperColor(lbl);
            }
        });
        
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lighterColor(lbl);
            }
        });
        
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            }
        });
        
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            }
        });
    }
    
    /**
     * Turn the background of the lbl label c units darker
     * @param lbl
     */
    public void deeperColor (JLabel lbl) {
        Color original  = lbl.getBackground();
        int red         = original.getRed();
        int green       = original.getGreen();
        int blue        = original.getBlue();
        lbl.setBackground
        (new Color 
        (red  - c < 0 ? 0 : red - c,
        green - c < 0 ? 0 : green - c,
        blue  - c < 0 ? 0 : blue - c));
    }
    
    /**
     * Turn the background of the lbl label c units lighter
     * @param lbl
     */
    private void lighterColor (JLabel lbl) {
        Color original  = lbl.getBackground();
        int red         = original.getRed();
        int green       = original.getGreen();
        int blue        = original.getBlue();
        
        lbl.setBackground
        (new Color 
        (red  + c > 255 ? 255 : red   + c,
        green + c > 255 ? 255 : green + c,
        blue  + c > 255 ? 255 : blue  + c));
    }
    
    /**
     * I was trying to make a button looks as disabled when it was pressed, but I still don't know what more to do or how to temporary disable the event methods
     * @param lbl
     */
    private void disabled (JLabel lbl) {
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
    }
    
    private void resetButtons () {
        
        warehouseBtn.setBackground(secondary);
        warehouseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsBtn.setBackground(secondary);
        clientsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        secBtn1.setBackground(highlight);
        secBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        secBtn2.setBackground(highlight);
        secBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        secBtn3.setBackground(highlight);
        secBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        terBtn1.setBackground(tertiary);
        terBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        terBtn2.setBackground(tertiary);
        terBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        terBtn3.setBackground(tertiary);
        terBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        terBtn4.setBackground(tertiary);
        terBtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
    }
    
    /**
     * Creates new form MainInterface
     */
    public MainInterface() {
        initComponents();
        makeButton(settingsMainBtn);
        makeButton(helpMainBtn);
        makeButton(warehouseBtn);
        makeButton(clientsBtn);
        makeButton(secBtn1);
        makeButton(secBtn2);
        makeButton(secBtn3);
        makeButton(terBtn1);
        makeButton(terBtn2);
        makeButton(terBtn3);
        makeButton(terBtn4);
        
    try {
        //Set of the file to be used in this program
        file    = new java.io.File("products");
        //Set of the RAF linked to the File file with read and write properties
        raf     = new RandomAccessFile(file, "rw" );
    }
    catch (FileNotFoundException e) {
        pr("File not found! " + e);
    }
//        warehouseBtnMouseClicked(new java.awt.event.MouseEvent()); I WANT TO INIT BY DEFAULT THIS OPTION
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        appBar = new javax.swing.JPanel();
        logoLbl = new javax.swing.JLabel();
        settingsMainBtn = new javax.swing.JLabel();
        helpMainBtn = new javax.swing.JLabel();
        userImage = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        mainOptionBar = new javax.swing.JPanel();
        warehouseBtn = new javax.swing.JLabel();
        clientsBtn = new javax.swing.JLabel();
        secOptionBar = new javax.swing.JPanel();
        secBtn1 = new javax.swing.JLabel();
        secBtn2 = new javax.swing.JLabel();
        secBtn3 = new javax.swing.JLabel();
        terOptionBar = new javax.swing.JPanel();
        terBtn1 = new javax.swing.JLabel();
        terBtn2 = new javax.swing.JLabel();
        terBtn3 = new javax.swing.JLabel();
        terBtn4 = new javax.swing.JLabel();
        searchBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        search = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(207, 216, 220));
        setMinimumSize(new java.awt.Dimension(1024, 576));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appBar.setBackground(primary);
        appBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/wnslogo.png"))); // NOI18N
        appBar.add(logoLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 40));

        settingsMainBtn.setBackground(primary);
        settingsMainBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/settingsMain.png"))); // NOI18N
        appBar.add(settingsMainBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 30, 40));

        helpMainBtn.setBackground(primary);
        helpMainBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/helpMain.png"))); // NOI18N
        appBar.add(helpMainBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 0, -1, 40));

        userImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/defaultUser.png"))); // NOI18N
        appBar.add(userImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 30, 40));

        userName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        userName.setForeground(new java.awt.Color(204, 204, 204));
        userName.setText("User");
        appBar.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 100, 20));

        getContentPane().add(appBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 40));

        mainOptionBar.setBackground(new java.awt.Color(54, 56, 76));
        mainOptionBar.setPreferredSize(new java.awt.Dimension(1080, 50));
        mainOptionBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        warehouseBtn.setBackground(secondary);
        warehouseBtn.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        warehouseBtn.setForeground(new java.awt.Color(255, 255, 255));
        warehouseBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        warehouseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/box.png"))); // NOI18N
        warehouseBtn.setText("             ALMACÉN                 ");
        warehouseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        warehouseBtn.setOpaque(true);
        warehouseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                warehouseBtnMouseClicked(evt);
            }
        });
        mainOptionBar.add(warehouseBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 50));

        clientsBtn.setBackground(secondary);
        clientsBtn.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        clientsBtn.setForeground(new java.awt.Color(255, 255, 255));
        clientsBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        clientsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/people.png"))); // NOI18N
        clientsBtn.setText("             CLIENTES                  ");
        clientsBtn.setOpaque(true);
        clientsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientsBtnMouseClicked(evt);
            }
        });
        mainOptionBar.add(clientsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 50));

        getContentPane().add(mainOptionBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1020, 50));

        secOptionBar.setBackground(new java.awt.Color(30, 34, 70));
        secOptionBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        secBtn1.setBackground(highlight);
        secBtn1.setText("Opción sec 1");
        secBtn1.setOpaque(true);
        secBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                secBtn1MouseClicked(evt);
            }
        });
        secOptionBar.add(secBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 153));

        secBtn2.setBackground(highlight);
        secBtn2.setText("Opción sec 2");
        secBtn2.setOpaque(true);
        secOptionBar.add(secBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 110, 153));

        secBtn3.setBackground(highlight);
        secBtn3.setText("Opción sec 3");
        secBtn3.setOpaque(true);
        secOptionBar.add(secBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 110, 160));

        getContentPane().add(secOptionBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 110, 460));

        terOptionBar.setBackground(tertiary);
        terOptionBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        terBtn1.setBackground(tertiary);
        terBtn1.setText("Opción ter 1");
        terBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                terBtn1MouseClicked(evt);
            }
        });
        terOptionBar.add(terBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 182, 50));

        terBtn2.setBackground(tertiary);
        terBtn2.setText("Opción ter 2");
        terOptionBar.add(terBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 182, 50));

        terBtn3.setBackground(tertiary);
        terBtn3.setText("Opción ter 3");
        terOptionBar.add(terBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 182, 50));

        terBtn4.setBackground(tertiary);
        terBtn4.setText("Opción ter 4");
        terOptionBar.add(terBtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 182, 50));

        searchBar.setBackground(tertiary);
        searchBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search.png"))); // NOI18N
        searchBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 30));

        jSeparator1.setBackground(foreground);
        searchBar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 180, 10));

        search.setBackground(tertiary);
        search.setForeground(foreground);
        search.setText("Buscar...");
        search.setBorder(null);
        search.setDragEnabled(true);
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        searchBar.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 160, 40));

        terOptionBar.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 0, 190, 50));

        getContentPane().add(terOptionBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 910, 50));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 910, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
        E V E N T S   M E T H O D S
    */
    
    private void warehouseBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_warehouseBtnMouseClicked
//        resetButtons();
//        warehouseBtn.setBackground(secondary);
        deeperColor(warehouseBtn);
        secBtn1.setText("INVENTARIO");
        secBtn2.setText("BAJA DE DISPONIBILIDAD");
        secBtn3.setText("ALTA DE DISPONIBILIDAD");
        disabled(warehouseBtn);
        clientsBtn.setBackground(secondary);
        
        secBtn1MouseClicked(evt);
    }//GEN-LAST:event_warehouseBtnMouseClicked

    private void clientsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientsBtnMouseClicked
//        resetButtons();
//        clientsBtn.setBackground(secondary);
        deeperColor(clientsBtn);
        secBtn1.setText("CLIENTES");
        secBtn2.setText("HISTORIAL");
        secBtn3.setText("");
        disabled(clientsBtn);
        disabled(secBtn3);
        warehouseBtn.setBackground(secondary);
        
        secBtn1MouseClicked(evt);
    }//GEN-LAST:event_clientsBtnMouseClicked

    private void secBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_secBtn1MouseClicked
//        resetButtons();
        secBtn1.setBackground(highlight);
        deeperColor(secBtn1);
        disabled(secBtn1);
        
        final String option[] = {"INVENTARIO", "CLIENTES"};
        
        if (secBtn1.getText().equals(option[0])) {
            terBtn1.setText("VER PRODUCTOS");
            terBtn2.setText("AGREGAR PRODUCTO");
            terBtn3.setText("EDITAR PRODUCTO");
            terBtn4.setText("FILTRAR POR");
            search.setText("Buscar producto...");
        }
        
        else if (secBtn1.getText().equals(option[1])) {
            terBtn1.setText("VER CLIENTES");
            terBtn2.setText("AGREGAR CLIENTE");
            terBtn3.setText("EDITAR CLIENTE");
            terBtn4.setText("FILTRAR POR");
            search.setText("Buscar cliente...");
            
        }
        
    }//GEN-LAST:event_secBtn1MouseClicked

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        search.setText("");
    }//GEN-LAST:event_searchMouseClicked

    private void terBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_terBtn1MouseClicked
        final String option[] = {"VER PRODUCTOS", "VER CLIENTES"};
        
        if (terBtn1.getText().equals(option[0])) {
            File productsFile   = new File(raf);
            ArrayList <Product> products = null;
            try {
                products  = productsFile.allProducts();
            } catch (IOException e) { pr ("IOException: " + e); }
            
            Product product;
            int numberOfProducts    = products == null ? 0 : products.size(); 
            
            getContentPane().add(viewInventory, new AbsoluteConstraints(110, 140, 910, 410));
            
            //x position, y position, width, height, margen, counter, columns
            int x, y, w = 180, h = 60, m = 37, i = 0, co = 4;
            
            while (i < numberOfProducts) {
                x = w * (i % co) + m * (i % co + 1);
                y = h * (i / co) + m * (i / co + 1);
                product = products.get(i);
                viewInventory.add(product.exportAsPane(), new AbsoluteConstraints(x, y, w, h));
                i++;
            }
        }
        
    }//GEN-LAST:event_terBtn1MouseClicked

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
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel appBar;
    private javax.swing.JLabel clientsBtn;
    private javax.swing.JLabel helpMainBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel logoLbl;
    private javax.swing.JPanel mainOptionBar;
    private javax.swing.JTextField search;
    private javax.swing.JPanel searchBar;
    private javax.swing.JLabel secBtn1;
    private javax.swing.JLabel secBtn2;
    private javax.swing.JLabel secBtn3;
    private javax.swing.JPanel secOptionBar;
    private javax.swing.JLabel settingsMainBtn;
    private javax.swing.JLabel terBtn1;
    private javax.swing.JLabel terBtn2;
    private javax.swing.JLabel terBtn3;
    private javax.swing.JLabel terBtn4;
    private javax.swing.JPanel terOptionBar;
    private javax.swing.JLabel userImage;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel warehouseBtn;
    // End of variables declaration//GEN-END:variables

    private void pr(String s) {
        System.out.println(s);
    }
}
