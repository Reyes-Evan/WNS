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
import resources.Style;

/**
 *@author Juan Carlos
 *@author Reyes
 *@author Dulio Caggiano
 *@author  Jordi Omar 
 */
public class MainInterface extends javax.swing.JFrame {

//    /*
//    C O L O R   P A L E T T E :   C H A R L Y 
//    */
//    static Color base      = new Color (24, 41, 70);
//    static Color primary   = new Color (14, 20, 32);
//    static Color secondary = new Color (21, 31, 51);
//    static Color tertiary  = new Color (136, 90, 27);
////    static Color tertiary  = new Color (21, 44, 86);
//    static Color light     = new Color (25, 58, 118);
//    static Color highlight = new Color (156, 110, 27);
//    public static Color foreground= new Color (220, 220, 220);
//    int c           = 20;
//    
    /*
        C O L O R   P A L E T T E :   D A R K
    */
    Style style = new Style();
    static Color primary    = Style.primary; //background
    static Color secondary  = Style.secondary; //second background
    static Color tertiary   = Style.tertiary; //right now, not used
    static Color light      = Style.light; //lighten items
    static Color dark       = Style.dark; //items almost black
    static Color highlight  = Style.highlight; //still not used
    static Color foreground = Style.foreground; //every font and icons (not yet)
    int c           = 20;                               //range of color to increase/decrease when buttons are pressed/released
    
    ViewInventory viewInventory = new ViewInventory();
    
    java.io.File     file;
    RandomAccessFile raf;
    
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
    
    //previous design method, now not used
    private void resetButtons () {
        
//        warehouseBtn.setBackground(secondary);
//        warehouseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        clientsBtn.setBackground(secondary);
//        clientsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
//        secBtn1.setBackground(highlight);
//        secBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        secBtn2.setBackground(highlight);
//        secBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        secBtn3.setBackground(highlight);
//        secBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        terBtn1.setBackground(tertiary);
        terBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        terBtn2.setBackground(tertiary);
        terBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        terBtn3.setBackground(tertiary);
        terBtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
    }
    
    /**
     * Creates new main Interface and do the next instructions every time the program start
     */
    public MainInterface() {
        initComponents();
        
        makeButton(settingsMainBtn);
        makeButton(helpMainBtn);
        makeButton(mainMenu);
//        makeButton(warehouseBtn);
//        makeButton(clientsBtn);
//        makeButton(secBtn1);
//        makeButton(secBtn2);
//        makeButton(secBtn3);
        makeButton(terBtn1);
        makeButton(terBtn2);
        makeButton(terBtn3);
        
        try {
            //Set the file to be used in this program to store the products
            file    = new java.io.File("products");
            //Set the RAF linked to the File file with read and write properties
            raf     = new RandomAccessFile(file, "rw" );
        }
        catch (FileNotFoundException e) {
            pr("File not found! " + e);
        }
        
        inventory.setVisible(true);
        slidingMenu.setVisible(false);
        
        
        //THESE ARE THE SAME INSTRUCTIONS THAN THE terBtn1MouseClicked... I DON'T KNOW HOW TO CALL THOSE INSTRUCTIONS WITHOUT HAVING TO REWRITE (CAN'T CALL THE METHOD) OR DOING ANOTHER INDEPENDIENT METHOD. IDEAS ARE ACCEPTED.
        File productsFile   = new File(raf);
            ArrayList <Product> products = null; //Arraylist to store all the current products in our database
            try {
                products  = productsFile.allProducts();
            } catch (IOException e) { pr ("IOException: " + e); }
            
            Product product;
            int numberOfProducts    = products == null ? 0 : products.size(); 
            
//            getContentPane().add(viewInventory, new AbsoluteConstraints(110, 140, 910, 410));
            
            inventory.setVisible(true);
            //x position, y position, width, height, margen, counter, columns
            int x, y, w = 180, h = 60, m = 37, i = 0, co = 4;
            
            while (i < numberOfProducts) {
                x = w * (i % co) + m * (i % co + 1);
                y = h * (i / co) + m * (i / co + 1);
                product = products.get(i);
                inventory.add(product.exportAsPane(), new AbsoluteConstraints(x, y, w, h));
                i++;
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

        jOptionPane1 = new javax.swing.JOptionPane();
        jSeparator2 = new javax.swing.JSeparator();
        appBar = new javax.swing.JPanel();
        logoLbl = new javax.swing.JLabel();
        settingsMainBtn = new javax.swing.JLabel();
        helpMainBtn = new javax.swing.JLabel();
        userImage = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        mainOptionBar = new javax.swing.JPanel();
        mainMenu = new javax.swing.JLabel();
        add = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        slidingMenu = new javax.swing.JPanel();
        terBtn1 = new javax.swing.JLabel();
        terBtn2 = new javax.swing.JLabel();
        terBtn3 = new javax.swing.JLabel();
        inventory = new javax.swing.JPanel();

        jSeparator2.setBackground(foreground);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(207, 216, 220));
        setMinimumSize(new java.awt.Dimension(1024, 576));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        appBar.setBackground(primary);
        appBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/wnslogo.png"))); // NOI18N
        appBar.add(logoLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 140, 40));

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

        mainOptionBar.setBackground(primary);
        mainOptionBar.setPreferredSize(new java.awt.Dimension(1080, 50));
        mainOptionBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainMenu.setBackground(primary);
        mainMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mainMenu.png"))); // NOI18N
        mainMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainMenuMouseClicked(evt);
            }
        });
        mainOptionBar.add(mainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        add.setBackground(primary);
        add.setForeground(foreground);
        add.setText("Agregar producto");
        mainOptionBar.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 110, 40));

        searchPanel.setBackground(primary);
        searchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBackground(foreground);
        searchPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 320, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search.png"))); // NOI18N
        searchPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 30, 40));

        jTextField1.setBackground(primary);
        jTextField1.setForeground(foreground);
        jTextField1.setText("Buscar...");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        searchPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 4, 320, 30));

        mainOptionBar.add(searchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 330, 40));

        getContentPane().add(mainOptionBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1020, 45));

        slidingMenu.setBackground(primary);
        slidingMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        terBtn1.setBackground(tertiary);
        terBtn1.setText("1");
        terBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                terBtn1MouseClicked(evt);
            }
        });
        slidingMenu.add(terBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 140));

        terBtn2.setBackground(tertiary);
        terBtn2.setText("2");
        slidingMenu.add(terBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 50, 136));

        terBtn3.setBackground(tertiary);
        terBtn3.setText("3");
        slidingMenu.add(terBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 50, 136));

        getContentPane().add(slidingMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 50, 470));

        inventory.setBackground(secondary);
        inventory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(inventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 1020, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void terBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_terBtn1MouseClicked
//        final String option[] = {"VER PRODUCTOS", "VER CLIENTES"};
        
//        if (terBtn1.getText().equals(option[0])) {
            File productsFile   = new File(raf);
            ArrayList <Product> products = null; //Arraylist to store all the current products in our database
            try {
                products  = productsFile.allProducts();
            } catch (IOException e) { pr ("IOException: " + e); }
            
            Product product;
            int numberOfProducts    = products == null ? 0 : products.size(); 
            
//            getContentPane().add(viewInventory, new AbsoluteConstraints(110, 140, 910, 410));
            
            inventory.setVisible(true);
            //x position, y position, width, height, margen, counter, columns
            int x, y, w = 180, h = 60, m = 37, i = 0, co = 4;
            
            while (i < numberOfProducts) {
                x = w * (i % co) + m * (i % co + 1);
                y = h * (i / co) + m * (i / co + 1);
                product = products.get(i);
                inventory.add(product.exportAsPane(), new AbsoluteConstraints(x, y, w, h));
                i++;
            }
//        }
        
    }//GEN-LAST:event_terBtn1MouseClicked

    private void search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_search1MouseClicked

    private void mainMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuMouseClicked
        // TODO add your handling code here:
        System.out.println("Main manu clicked");
        slidingMenu.setVisible(!slidingMenu.isVisible());
    }//GEN-LAST:event_mainMenuMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
    private javax.swing.JLabel add;
    private javax.swing.JPanel appBar;
    private javax.swing.JLabel helpMainBtn;
    private javax.swing.JPanel inventory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel logoLbl;
    private javax.swing.JLabel mainMenu;
    private javax.swing.JPanel mainOptionBar;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JLabel settingsMainBtn;
    private javax.swing.JPanel slidingMenu;
    private javax.swing.JLabel terBtn1;
    private javax.swing.JLabel terBtn2;
    private javax.swing.JLabel terBtn3;
    private javax.swing.JLabel userImage;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables

    private void pr(String s) {
        System.out.println(s);
    }
}
