/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.form;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *  Predstavlja formu za azuriranje neophodnih podataka za povezivanje sa bazom.
 * @author Jelena Repac
 */
public class FrmSettings extends javax.swing.JDialog {

    /**
     * Konstruktor
     * @param parent forma iz koje je pozvana 
     * @param modal odredjuje da li dijalog treba da bude modalan ili ne 
     * @throws IOException Ukoliko dodje do I/O greske
     */
    public FrmSettings(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();
        setTitle("Settings");
        setLocationRelativeTo(null);
        prepareView();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("URL:");

        jLabel3.setText("Username:");

        jLabel4.setText("Password:");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUrl)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(txtPassword)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Obradjuje dogadjaj kada se pritisne dugme Save.
     * Setuje unete vrednosti koje su neophodne za konektovanje sa bazom.
     * Ukoliko dodje greske, prikazuje se prozor sa porukom o gresci.
     * @param evt dogadjaj koji pokrece ovu metodu
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if("".equals(txtUrl.getText().trim()) || txtUsername.getText().trim().equals("")){
                JOptionPane.showMessageDialog(this, "You must insert a value for url and username!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
        else{
        try {
            
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            properties.setProperty("url", txtUrl.getText().trim());
            properties.setProperty("username", txtUsername.getText().trim());
            properties.setProperty("password", String.copyValueOf(txtPassword.getPassword()));
            properties.store(new FileOutputStream("config/dbconfig.properties"), "url conficuration updated");
            this.dispose();
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUrl;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    /**
     * Priprema prikaza forme.
     * @throws FileNotFoundException Ukoliko putanja do fajla ne postoji
     * @throws IOException Ukoliko dodje do I/O greske
     */
    private void prepareView() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("./src/main/resources/dbconfig.properties"));
        txtUrl.setText(properties.getProperty("url"));
        txtUsername.setText(properties.getProperty("username"));
        txtPassword.setText(properties.getProperty("password"));
    }
}
