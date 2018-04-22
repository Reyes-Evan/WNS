/*
 * Carlos Andrés Reyes Evangelista
 * 157068
 * Ingeniería en Sistemas Computacionales. UDLAP.
 */
package interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
 *@author Jose Miguel
 *@author  Jordi Omar 
 */
public class MainInterface extends javax.swing.JFrame {

    /*
        C O L O R   P A L E T T E 
    */
    Color primary    = Style.primary; //background
    Color secondary  = Style.secondary; //second background
    Color tertiary   = Style.tertiary; //right now, not used
    Color light      = Style.light; //lighten items
    Color dark       = Style.dark; //items almost black
    Color highlight  = Style.highlight; //still not used
    Color foreground = Style.foreground; //every font and icons (not yet)
    int c           = 20;                               //range of color to increase/decrease when buttons are pressed/released
    
    /*
        W I N D O W   M A N A G E R  S T A T E S
    */
    
    public enum WindowState{
        INV_FULL, INV_SR_NAME, INV_SR_CATEGORY, INV_SR_SUBCATEGORY,
        CLI_FULL, CLI_SR_NAME, CLI_SR_MAIL, CLI_SR_NUM
    }

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

    }
    
    /**
     * Creates new main Interface and do the next instructions every time the program start
     */
    public MainInterface() {
        initComponents();
        
        makeButton(settingsMainBtn);
        makeButton(helpMainBtn);
        makeButton(mainMenu);
        makeButton(inventoryBtn);
        makeButton(clientsBtn);
        makeButton(historyBtn);
        makeButton(productBtn);
        makeButton(cancelBtn);
        makeButton(add);
        
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
        addProductPane.setVisible(false);
        //viewInventory();
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
        filterBtn = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        searchProduct = new javax.swing.JTextField();
        slidingMenu = new javax.swing.JPanel();
        inventoryBtn = new javax.swing.JLabel();
        clientsBtn = new javax.swing.JLabel();
        historyBtn = new javax.swing.JLabel();
        filterMenu = new javax.swing.JPanel();
        filterBox = new javax.swing.JComboBox<>();
        inventory = new javax.swing.JPanel();
        addProductPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        codeField = new javax.swing.JTextField();
        descriptionField = new javax.swing.JTextField();
        brandField = new javax.swing.JTextField();
        modelField = new javax.swing.JTextField();
        categoryField = new javax.swing.JTextField();
        subcategoryField = new javax.swing.JTextField();
        productBtn = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JLabel();
        searchResult = new javax.swing.JLabel();
        clients = new javax.swing.JPanel();

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
        userName.setText("Usuario");
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
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        mainOptionBar.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 140, 40));

        filterBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterBtn.setText("Filtrar ");
        filterBtn.setToolTipText("");
        mainOptionBar.add(filterBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 110, 30));

        searchPanel.setBackground(primary);
        searchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBackground(foreground);
        searchPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 320, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search.png"))); // NOI18N
        searchPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 30, 40));

        searchProduct.setBackground(primary);
        searchProduct.setForeground(foreground);
        searchProduct.setText("Buscar...");
        searchProduct.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        searchProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchProductMouseClicked(evt);
            }
        });
        searchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchProductKeyPressed(evt);
            }
        });
        searchPanel.add(searchProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 4, 320, 30));

        mainOptionBar.add(searchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 330, 40));

        getContentPane().add(mainOptionBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1020, 45));

        slidingMenu.setBackground(primary);
        slidingMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inventoryBtn.setBackground(tertiary);
        inventoryBtn.setText("1");
        inventoryBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryBtnMouseClicked(evt);
            }
        });
        slidingMenu.add(inventoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        clientsBtn.setBackground(tertiary);
        clientsBtn.setText("2");
        clientsBtn.setOpaque(true);
        clientsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientsBtnMouseClicked(evt);
            }
        });
        slidingMenu.add(clientsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 50, 70));

        historyBtn.setBackground(tertiary);
        historyBtn.setText("3");
        slidingMenu.add(historyBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 50, 70));

        getContentPane().add(slidingMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 50, 470));

        filterMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        filterBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Categoria", "Sub Cartegoria" }));
        filterBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterBoxActionPerformed(evt);
            }
        });
        filterMenu.add(filterBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, -1, -1));

        getContentPane().add(filterMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, 100, 470));

        inventory.setBackground(secondary);
        inventory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(inventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 1020, 470));

        addProductPane.setBackground(secondary);
        addProductPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Subcategoría");
        addProductPane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 90, 30));

        jLabel3.setText("Nombre");
        addProductPane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 80, 30));

        jLabel4.setText("Código");
        addProductPane.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 80, 30));

        jLabel5.setText("Descripción");
        addProductPane.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 80, 30));

        jLabel6.setText("Marca");
        addProductPane.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 80, 30));

        jLabel7.setText("Modelo");
        addProductPane.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 80, 30));

        jLabel8.setText("Categoría");
        addProductPane.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 90, 30));

        nameField.setBackground(secondary);
        nameField.setForeground(foreground);
        nameField.setToolTipText("");
        nameField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nameField.setCaretColor(new java.awt.Color(255, 255, 255));
        addProductPane.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 170, 30));

        codeField.setBackground(secondary);
        codeField.setForeground(foreground);
        codeField.setText(" ");
        codeField.setToolTipText("");
        addProductPane.add(codeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 170, 30));

        descriptionField.setBackground(secondary);
        descriptionField.setForeground(foreground);
        descriptionField.setToolTipText("");
        addProductPane.add(descriptionField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 170, 30));

        brandField.setBackground(secondary);
        brandField.setForeground(foreground);
        addProductPane.add(brandField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 170, 30));

        modelField.setBackground(secondary);
        modelField.setForeground(foreground);
        addProductPane.add(modelField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 170, 30));

        categoryField.setBackground(secondary);
        categoryField.setForeground(foreground);
        addProductPane.add(categoryField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 170, 30));

        subcategoryField.setBackground(secondary);
        subcategoryField.setForeground(foreground);
        addProductPane.add(subcategoryField, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 170, 30));

        productBtn.setBackground(secondary);
        productBtn.setForeground(foreground);
        productBtn.setText("Agregar producto");
        productBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productBtnMouseClicked(evt);
            }
        });
        addProductPane.add(productBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 140, 40));

        cancelBtn.setBackground(secondary);
        cancelBtn.setForeground(foreground);
        cancelBtn.setText("Cancelar");
        cancelBtn.setToolTipText("");
        cancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelBtnMouseClicked(evt);
            }
        });
        addProductPane.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 110, 40));

        searchResult.setForeground(new java.awt.Color(255, 255, 255));
        searchResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addProductPane.add(searchResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 320, 20));

        getContentPane().add(addProductPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 1020, 470));

        clients.setBackground(new java.awt.Color(204, 0, 51));
        clients.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(clients, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 85, 1020, 470));
        clients.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inventoryBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryBtnMouseClicked
        windowManager(WindowState.INV_FULL, null);
        //clients.setVisible(false);
        //inventory.setVisible(true);
        //addProductPane.setVisible(false);
    }//GEN-LAST:event_inventoryBtnMouseClicked

    private void search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_search1MouseClicked

    private void mainMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuMouseClicked
        // TODO add your handling code here:
        System.out.println("Main manu clicked");
        slidingMenu.setVisible(!slidingMenu.isVisible());
    }//GEN-LAST:event_mainMenuMouseClicked

    private void clientsBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientsBtnMouseClicked
        // TODO add your handling code here:
        clients.setVisible(true);
        inventory.setVisible(false);
        addProductPane.setVisible(false);
    }//GEN-LAST:event_clientsBtnMouseClicked

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        // TODO add your handling code here:
        clients.setVisible(false);
        inventory.setVisible(false);
        addProductPane.setVisible(true);
        
    }//GEN-LAST:event_addMouseClicked

    private void searchProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchProductMouseClicked
        // TODO add your handling code here:
        searchProduct.setText("");
    }//GEN-LAST:event_searchProductMouseClicked

    private void searchProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchProductKeyPressed
        // TODO add your handling code here:
         String searchKey = searchProduct.getText();
         String filterKey = String.valueOf(filterBox.getSelectedItem()).trim();
         
        if((evt.getKeyCode() == KeyEvent.VK_ENTER) && searchKey!=null){
            
            searchResult.setVisible(true);
            searchResult.setText("Resultado de búsqueda "+searchKey+":");
            
            switch(filterKey){
                
                case "Nombre": windowManager(WindowState.INV_SR_NAME, searchKey);
                break;
                
                case "Categoria": windowManager(WindowState.INV_SR_CATEGORY, searchKey);
                break;
                
                case "SubCategoria": windowManager(WindowState.INV_SR_SUBCATEGORY, searchKey);
                break;
                
                default : windowManager(WindowState.INV_SR_NAME, searchKey);
            }
            
        }
    }//GEN-LAST:event_searchProductKeyPressed
 
    private void filterBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterBoxActionPerformed

    private void productBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productBtnMouseClicked
        // TODO add your handling code here:
        Product newProduct = new Product();
        
        if (nameField.getText().isEmpty() || descriptionField.getText().isEmpty() || categoryField.getText().isEmpty() || subcategoryField.getText().isEmpty() || brandField.getText().isEmpty() || codeField.getText().isEmpty() || modelField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser rellenados", "Error: checar campos", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            newProduct.setName(nameField.getText());
            newProduct.setDescription(descriptionField.getText());
            newProduct.setCategory(categoryField.getText());
            newProduct.setSubcategory(subcategoryField.getText());
            newProduct.setLocation(brandField.getText());
            newProduct.setPrice(Integer.parseInt(codeField.getText()));
            newProduct.setAvailability(Integer.parseInt(modelField.getText()));
            
            //raf.insert(newProduct);
            JOptionPane.showMessageDialog(null, "Producto añadido con éxito", "Confirmación ", JOptionPane.INFORMATION_MESSAGE);
            
            clients.setVisible(false);
            inventory.setVisible(true);
            addProductPane.setVisible(false);
            
            nameField.setText(null);
            descriptionField.setText(null);
            categoryField.setText(null);
            subcategoryField.setText(null);
            brandField.setText(null);
            codeField.setText(null);
            modelField.setText(null);
        }    
    }//GEN-LAST:event_productBtnMouseClicked

    private void cancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelBtnMouseClicked
        // TODO add your handling code here:
        nameField.setText(null);
        descriptionField.setText(null);
        categoryField.setText(null);
        subcategoryField.setText(null);
        brandField.setText(null);
        codeField.setText(null);
        modelField.setText(null);
        
        clients.setVisible(false);
        inventory.setVisible(true);
        addProductPane.setVisible(false);
    }//GEN-LAST:event_cancelBtnMouseClicked

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
    private javax.swing.JPanel addProductPane;
    private javax.swing.JPanel appBar;
    private javax.swing.JTextField brandField;
    private javax.swing.JLabel cancelBtn;
    private javax.swing.JTextField categoryField;
    private javax.swing.JPanel clients;
    private javax.swing.JLabel clientsBtn;
    private javax.swing.JTextField codeField;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JComboBox<String> filterBox;
    private javax.swing.JLabel filterBtn;
    private javax.swing.JPanel filterMenu;
    private javax.swing.JLabel helpMainBtn;
    private javax.swing.JLabel historyBtn;
    private javax.swing.JPanel inventory;
    private javax.swing.JLabel inventoryBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel logoLbl;
    private javax.swing.JLabel mainMenu;
    private javax.swing.JPanel mainOptionBar;
    private javax.swing.JTextField modelField;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel productBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTextField searchProduct;
    private javax.swing.JLabel searchResult;
    private javax.swing.JLabel settingsMainBtn;
    private javax.swing.JPanel slidingMenu;
    private javax.swing.JTextField subcategoryField;
    private javax.swing.JLabel userImage;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables

    private void pr(String s) {
        System.out.println(s);
    }
    
    /*
    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    METHODS CALLED BY EVENTS
    
    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    */
    /*
    private void viewInventory () {
        File productsFile   = new File(raf);
        ArrayList <Product> products = null; //Arraylist to store all the current products in our database
        try {
            products  = productsFile.allProducts();
        } catch (IOException e) { pr ("IOException: " + e); }

        Product product;
        int numberOfProducts    = products == null ? 0 : products.size(); 

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
    */
    
    /*
        PAINT THE INVENTORY PANEL WITH THE GIVEN LIST
    */
    private void paintInventory(ArrayList<Product> productsToShow){
        Product product;
        int numberOfProducts    = productsToShow == null ? 0 : productsToShow.size(); 
        
        inventory.removeAll();
        inventory.revalidate();
        inventory.repaint();
        inventory.setVisible(true);
        
        //x position, y position, width, height, margen, counter, columns
        int x, y, w = 180, h = 60, m = 37, i = 0, co = 4;

        while (i < numberOfProducts) {
            x = w * (i % co) + m * (i % co + 1);
            y = h * (i / co) + m * (i / co + 1);
            product = productsToShow.get(i);
            inventory.add(product.exportAsPane(), new AbsoluteConstraints(x, y, w, h));
            i++;
        }   
    }
    
    /*
        MANAGE ALL THE WINDOW STATES 
    */
    private void windowManager(WindowState state, String searchKey){
       File productsFile   = new File(raf);
       ArrayList <Product> products = null;
        
       switch(state){
           
           case INV_FULL:
               try {
                    products  = productsFile.allProducts();
                    }catch (IOException e) { pr ("IOException: " + e); }
                searchResult.setText(null);
                paintInventory(products);
           break;
           
           case INV_SR_NAME:
               try {
                    products  = productsFile.searchByName(searchKey);
                    } catch (IOException e) { pr ("IOException: " + e); }
                 paintInventory(products);
            break;
            
           case INV_SR_CATEGORY:
               try {
                    products  = productsFile.searchByCategory(searchKey);
                    } catch (IOException e) { pr ("IOException: " + e); }
                 paintInventory(products);
            break;
            
           case INV_SR_SUBCATEGORY: 
               try {
                    products  = productsFile.searchBySubCategory(searchKey);
                    } catch (IOException e) { pr ("IOException: " + e); }
                 paintInventory(products);
            break;
            
           default:
               try {
                    products  = productsFile.allProducts();
                    } catch (IOException e) { pr ("IOException: " + e); }
                 paintInventory(products);
            break;
       }
        
    }
}
