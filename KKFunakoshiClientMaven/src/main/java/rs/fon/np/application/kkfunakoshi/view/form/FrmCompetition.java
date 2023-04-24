/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.view.form;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import rs.fon.np.application.kkfunakoshi.controller.ControllerUI;
import rs.fon.np.application.kkfunakoshi.domain.City;
import rs.fon.np.application.kkfunakoshi.domain.Competition;
import rs.fon.np.application.kkfunakoshi.validation.ValidationException;
import rs.fon.np.application.kkfunakoshi.validation.Validator;

/**
 *
 * @author Jeks
 */
public class FrmCompetition extends javax.swing.JDialog {

    /**
     * Creates new form FrmCompetition
     */
    public FrmCompetition(java.awt.Frame parent, boolean modal) throws Exception {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        cbCity = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCompetitionHall = new javax.swing.JTextField();
        lblHallName = new javax.swing.JLabel();
        lblDateFormat = new javax.swing.JLabel();
        lblNameError = new javax.swing.JLabel();
        lblPhoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Name:");

        jLabel2.setText("Date:");

        jLabel3.setText("City:");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameKeyTyped(evt);
            }
        });

        txtDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDateKeyTyped(evt);
            }
        });

        cbCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSave.setText("Save competition");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel4.setText("Competition hall:");

        lblHallName.setForeground(new java.awt.Color(102, 102, 102));
        lblHallName.setText("<Hall name, Adress>");

        lblDateFormat.setForeground(new java.awt.Color(255, 0, 0));
        lblDateFormat.setText("Error date format");

        lblNameError.setForeground(new java.awt.Color(255, 0, 0));
        lblNameError.setText("Error message name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHallName, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(lblDateFormat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDate)
                    .addComponent(lblNameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCity, 0, 305, Short.MAX_VALUE)
                    .addComponent(txtName)
                    .addComponent(txtCompetitionHall))
                .addGap(20, 20, 20)
                .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lblNameError)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDateFormat)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCompetitionHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHallName)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            validateInput();
            String name= txtName.getText().trim();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String d= txtDate.getText().trim();
            Date date= format.parse(d);
            
            String hall= txtCompetitionHall.getText().trim();
            City c= (City) cbCity.getSelectedItem();
            
            Competition competition= new Competition();
            competition.setName(name);
            competition.setDate(date);
            competition.setCompetitionHall(hall);
            competition.setCity(c);
            
            System.out.println(competition.toString());
            ControllerUI.getInstance().addCompetition(competition);
            
            resetField();
            JOptionPane.showMessageDialog(this, "Competition successfully added!");
            int answer=JOptionPane.showConfirmDialog(this, "Do you want to add more members?");
            if(answer==0){
                
            }
            else{
                 this.dispose();
            }
            
        } catch (ParseException ex) {
           JOptionPane.showMessageDialog(this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"System can't save competition!","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyTyped
         if(txtName.getText().length()<2){
            lblNameError.setText("The competition name must contain at least 2 letters");
        }else{
            lblNameError.setText(null);
        }
    }//GEN-LAST:event_txtNameKeyTyped

    private void txtDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDateKeyTyped
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.parse(txtDate.getText());
            lblDateFormat.setText(null);
        } catch (ParseException ex) {
            lblDateFormat.setText("Date must be in format yyyy-MM-dd!");
            lblDateFormat.setForeground(Color.red);
        }
    }//GEN-LAST:event_txtDateKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbCity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDateFormat;
    private javax.swing.JLabel lblHallName;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JTextField txtCompetitionHall;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    private void prepareView() throws Exception {
        loadCities();
        lblNameError.setText(null);
        lblDateFormat.setText(null);
        ImageIcon img = new ImageIcon("logoIcon.png");
        lblPhoto.setIcon(img);
    }

    private void loadCities() throws Exception {
       List<City> cities= ControllerUI.getInstance().getCities();
       cbCity.setModel(new DefaultComboBoxModel(cities.toArray()));
       cbCity.setSelectedItem(null);
    }

    private void resetField() {
        txtDate.setText(null);
        txtName.setText(null);
        txtCompetitionHall.setText(null);
        cbCity.setSelectedItem(null);
    }

    private void validateInput() throws ValidationException {
        Validator.startValidation().validateNotNullOrEmpty(txtName.getText(), "You must insert a competition name!").
                validateNotNullOrEmpty(txtDate.getText(), "You must insert a date!").
                validateNotNullOrEmpty(txtCompetitionHall.getText(), "You must insert a competition hall!").
                validateValueIsDate(txtDate.getText(), "yyyy-MM-dd", "Date must be in the format yyyy-MM-dd!").throwIfInvalide();
    }
}
