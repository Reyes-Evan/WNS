/*
 * Carlos Andrés Reyes Evangelista
 * 157068
 * Ingeniería en Sistemas Computacionales. UDLAP.
 */
package interfaces;

import java.awt.*;

/**
 *
 * @author Reyes
 */
public class ViewInventory extends javax.swing.JPanel {

    /**
     * Creates new form ViewInventory
     */
    public ViewInventory() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setBackground(MainInterface.light);
        setPreferredSize(new java.awt.Dimension(910, 420));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("INVENTARIO");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 482, 34));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
