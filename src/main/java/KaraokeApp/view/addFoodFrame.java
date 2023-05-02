/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package KaraokeApp.view;

import KaraokeApp.model.Customer;
import KaraokeApp.model.FoodConsumed;
import KaraokeApp.model.dishOfFood;
import KaraokeApp.repository.foodConsumedRes;
import KaraokeApp.service.CustomerService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 84374
 */
public class addFoodFrame extends javax.swing.JFrame {

    int pos;
    int i = 0;
    double totalCash = 0.0;
    double total = 0.0;
    CustomerService dataIn = new CustomerService();
    ArrayList<Customer> lstCus = new ArrayList<>();
    ArrayList<dishOfFood> lstDish = dataIn.getLstDishOfFood();
    ArrayList<FoodConsumed> lstFoodConsumed = dataIn.getLstFoodConsumed();
    DefaultComboBoxModel modelCboService = new DefaultComboBoxModel();
    DefaultTableModel modelTableService = new DefaultTableModel();
    ArrayList<FoodConsumed> lstForReady = new ArrayList<>();

    /**
     * Creates new form addFoodFrame
     */
    public addFoodFrame(int para, ArrayList<Customer> lstPara) {
        initComponents();
        pos = para;
        lstCus = lstPara;
        loadDataToCboService();
        loadDataTotxt();
        loadDataToTableService();
    }

    public void loadDataToCboService() {
        modelCboService = (DefaultComboBoxModel) cboService.getModel();
        for (dishOfFood dishOfFoodOb : lstDish) {
            modelCboService.addElement(dishOfFoodOb.getNameFood());
        }
    }

    public void loadDataToTableService() {
        modelTableService = (DefaultTableModel) tableService.getModel();
        modelTableService.setRowCount(0);
        DecimalFormat formater = new DecimalFormat("###,###,###.00");
        for (FoodConsumed foodConsumedOb : lstFoodConsumed) {
            if (foodConsumedOb.getIdBill().equals(lstCus.get(pos).getIdBill())) {
                i++;
                double total = foodConsumedOb.getAmount() * foodConsumedOb.getPriceFood();
                totalCash += total;
                modelTableService.addRow(new Object[]{i, foodConsumedOb.getIdBill(), foodConsumedOb.getNameFood(), foodConsumedOb.getUnit(), foodConsumedOb.getPriceFood(), foodConsumedOb.getAmount(), formater.format(total)});
            }
        }
    }

    public void loadDataTotxt() {
        lblNameCus.setText(lstCus.get(pos).getName());
        lblIDBill.setText(lstCus.get(pos).getIdBill());
    }

    public void displayDetailOfFood() {
        for (dishOfFood dishOb : lstDish) {
            if (dishOb.getNameFood().equals(cboService.getSelectedItem())) {
                lblUnit.setText(dishOb.getUnit());
                lblUnitPrice.setText(String.valueOf(dishOb.getPriceFood()));
            }
        }
    }

    public void del() {
        int pos = tableService.getSelectedRow();
        lstForReady.remove(pos);

    }

    public void addToTable() {
        DecimalFormat formater = new DecimalFormat("###,###,###.00");
        String amount = txtAmountOfService.getText();
        i++;
        String idFood = null;
        modelTableService = (DefaultTableModel) tableService.getModel();
        try {
            if (Double.parseDouble(amount) > 0) {
                for (dishOfFood dishOb : lstDish) {
                    if (dishOb.getNameFood().equals(cboService.getSelectedItem())) {
                        idFood = dishOb.getIdFood();
                        break;
                    }
                }
                lstForReady.add(new FoodConsumed(lblIDBill.getText(), idFood, String.valueOf(cboService.getSelectedItem()), lblUnit.getText(), Double.valueOf(txtAmountOfService.getText()), Double.valueOf(lblUnitPrice.getText())));
                double total = lstForReady.get(lstForReady.size() - 1).getAmount() * lstForReady.get(lstForReady.size() - 1).getPriceFood();
                modelTableService.addRow(new Object[]{i, lblIDBill.getText(), String.valueOf(cboService.getSelectedItem()), lblUnit.getText(), Double.valueOf(lblUnitPrice.getText()), Double.valueOf(txtAmountOfService.getText()), formater.format(total)});
            } else {
                JOptionPane.showMessageDialog(this, "Số lượng không được là số âm");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào số lượng và phải là số");
        }
    }

    public void AddToDB() {
        int choice = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm dịch vụ", "Warning !", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == 0) {
            lstFoodConsumed.addAll(lstForReady);
            dataIn.addFoodConsumedToSql(lstForReady);
            lstForReady = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công");
        }
    }

    public boolean isDouble(String txt) {
        boolean target = false;
        try {
            double x = Double.valueOf(txt);
            target = true;
        } catch (Exception e) {
            target = false;
        }
        return target;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableService = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        btnCorrectOfaddFrame = new javax.swing.JButton();
        btnAddOfaddFrame = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblNameCus = new javax.swing.JLabel();
        lblIDBill = new javax.swing.JLabel();
        txtAmountOfService = new javax.swing.JTextField();
        cboService = new javax.swing.JComboBox<>();
        lblUnitPrice = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblUnit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLayeredPane1.setBackground(new java.awt.Color(51, 0, 51));
        jLayeredPane1.setOpaque(true);

        tableService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID Hóa Đơn", "Dịch Vụ", "Đơn Vị", "Đơn Giá", "Số Lượng", "Thành Tiền"
            }
        ));
        jScrollPane1.setViewportView(tableService);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Dịch Vụ Đang Có");

        btnCorrectOfaddFrame.setBackground(new java.awt.Color(156, 243, 165));
        btnCorrectOfaddFrame.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCorrectOfaddFrame.setText("Xác Nhận");
        btnCorrectOfaddFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorrectOfaddFrameActionPerformed(evt);
            }
        });

        btnAddOfaddFrame.setBackground(new java.awt.Color(156, 243, 165));
        btnAddOfaddFrame.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddOfaddFrame.setText("Thêm");
        btnAddOfaddFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOfaddFrameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Thêm Dịch Vụ");

        lblNameCus.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNameCus.setForeground(new java.awt.Color(255, 255, 255));
        lblNameCus.setText("jLabel5");

        lblIDBill.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblIDBill.setForeground(new java.awt.Color(255, 255, 255));
        lblIDBill.setText("jLabel6");

        txtAmountOfService.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cboService.setBackground(new java.awt.Color(156, 243, 165));
        cboService.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboService.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboServiceItemStateChanged(evt);
            }
        });
        cboService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboServiceActionPerformed(evt);
            }
        });

        lblUnitPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUnitPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblUnitPrice.setText("#unitprice here");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Đơn Giá: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Đơn Vị: ");

        lblUnit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUnit.setForeground(new java.awt.Color(255, 255, 255));
        lblUnit.setText("#unit here");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID  Hóa Đơn:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dịch Vụ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Số Lượng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên Khách Hàng: ");

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnCorrectOfaddFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnAddOfaddFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblNameCus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblIDBill, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtAmountOfService, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboService, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblUnitPrice, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblUnit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNameCus))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIDBill))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAmountOfService, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUnitPrice))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboService, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUnit))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAddOfaddFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCorrectOfaddFrame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboService, txtAmountOfService});

        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblNameCus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblIDBill))
                        .addGap(22, 22, 22)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblUnit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblUnitPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtAmountOfService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCorrectOfaddFrame)
                            .addComponent(btnAddOfaddFrame)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboServiceActionPerformed

    private void cboServiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboServiceItemStateChanged
        // TODO add your handling code here:
        displayDetailOfFood();
    }//GEN-LAST:event_cboServiceItemStateChanged

    private void btnAddOfaddFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOfaddFrameActionPerformed
        // TODO add your handling code here:
        if (!txtAmountOfService.getText().strip().equals("")) {
        if (isDouble(txtAmountOfService.getText().strip())==true) {
            double amount = Double.parseDouble(txtAmountOfService.getText());
            if(amount<100)
                addToTable();
            else
                JOptionPane.showMessageDialog(this,"Số lượng phải nhỏ hơn 100!");
            }
        else{
            JOptionPane.showMessageDialog(this,"Số lượng nhập vào phải là số!");
        }
        }
        else{
            JOptionPane.showMessageDialog(this,"Không được để trống số lượng!");
        }
    }//GEN-LAST:event_btnAddOfaddFrameActionPerformed

    private void btnCorrectOfaddFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorrectOfaddFrameActionPerformed
        // TODO add your handling code here:
        AddToDB();
    }//GEN-LAST:event_btnCorrectOfaddFrameActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddOfaddFrame;
    private javax.swing.JButton btnCorrectOfaddFrame;
    private javax.swing.JComboBox<String> cboService;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIDBill;
    private javax.swing.JLabel lblNameCus;
    private javax.swing.JLabel lblUnit;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JTable tableService;
    private javax.swing.JTextField txtAmountOfService;
    // End of variables declaration//GEN-END:variables
}
