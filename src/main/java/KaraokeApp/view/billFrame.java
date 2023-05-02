/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package KaraokeApp.view;

import KaraokeApp.model.Customer;
import KaraokeApp.model.FoodConsumed;
import KaraokeApp.model.TypeRoom;
import KaraokeApp.model.roomNo;
import KaraokeApp.service.CustomerService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 84374
 */
public class billFrame extends javax.swing.JFrame {

    int pos;
    int i = 1;
    double globalTotalCheck = 0.0;
    double moneyT = 0.0;
    String day = "";
    String month = "";
    String year = "";
    String hourNowBill;
    String minuteNowBill;
    CustomerService dataIn = new CustomerService();
    ArrayList<Customer> lstCus;
    ArrayList<Customer> lstCusOfAppFrame;
    ArrayList<TypeRoom> lstTypeRoom;
    ArrayList<roomNo> lstRoomNo;
    ArrayList<roomNo> lstRoomFull;
    DefaultTableModel modelTable = new DefaultTableModel();
    DefaultTableModel modelTableApp = new DefaultTableModel();
    ArrayList<FoodConsumed> lstFoodConsumed = dataIn.getLstFoodConsumed();

    /**
     * Creates new form billFrame
     */
    public billFrame(int para, String hourNow, String minuteNow, String day, String month, String year, ArrayList<TypeRoom> lstRoomTypeRam, ArrayList<roomNo> lstRoomNoRam, DefaultTableModel modelPara, ArrayList<roomNo> lstRoomNowPara, ArrayList<Customer> cusdataOfAppFrame) {
        lstCus = dataIn.getCusByDate(day, month, year, lstRoomTypeRam, lstRoomNoRam);
        lstCusOfAppFrame = cusdataOfAppFrame;
        modelTableApp = modelPara;
        lstTypeRoom = lstRoomTypeRam;
        lstRoomFull = lstRoomNoRam;
        lstRoomNo = lstRoomNowPara;
        this.day = day;
        this.month = month;
        this.year = year;
        hourNowBill = hourNow;
        minuteNowBill = minuteNow;
        pos = para;
        initComponents();
        disPlayData();
        loadDataToTable();
    }

    public void disPlayData() {
        CustomerService dataIn = new CustomerService();
        lblName.setText(lstCus.get(pos).getName());
        lblIdBill.setText(lstCus.get(pos).getIdBill());
        lblDOB.setText(lstCus.get(pos).getDOB());
        lblGender.setText(lstCus.get(pos).getGender());
        lblRoomType.setText(lstCus.get(pos).getRoomType().getNameRoomType());
        lblRoomNo.setText(lstCus.get(pos).getRoomNo().getNameRoom());
        lblTimeIn.setText((lstCus.get(pos).getHourIn() + ":" + lstCus.get(pos).getMinuteIn()));
        lblTimeOut.setText(hourNowBill + ":" + minuteNowBill);
        lblDay.setText(day);
        lblMonth.setText(month);
        lblYear.setText(year);
    }

    public void loadDataToTable() {
        modelTable = (DefaultTableModel) tableBill.getModel();
        modelTable.setRowCount(0);
        double totalMinuteIn = Double.parseDouble(lstCus.get(pos).getHourIn()) * 60 + Double.parseDouble(lstCus.get(pos).getMinuteIn());
        double totalMinuteOut = Double.parseDouble(hourNowBill) * 60 + Double.parseDouble(minuteNowBill);
        double totalTime = totalMinuteOut - totalMinuteIn;
        double totalCash = (totalTime / 60) * lstCus.get(pos).getRoomType().getPriceOnHour();
        DecimalFormat formater = new DecimalFormat("###,###,###.00");
        DecimalFormat formater1 = new DecimalFormat("0.00");
        modelTable.addRow(new Object[]{i, lstCus.get(pos).getIdBill(), lstCus.get(pos).getRoomType().getNameRoomType(), lstCus.get(pos).getRoomType().getPriceOnHour(), formater1.format(totalTime / 60) + "h", formater.format((totalTime / 60) * lstCus.get(pos).getRoomType().getPriceOnHour())});
        for (FoodConsumed foodConsumedObj : lstFoodConsumed) {
            if (foodConsumedObj.getIdBill().equals(lblIdBill.getText())) {
                i++;
                double total = foodConsumedObj.getAmount() * foodConsumedObj.getPriceFood();
                totalCash += total;
                modelTable.addRow(new Object[]{i, foodConsumedObj.getIdBill(), foodConsumedObj.getNameFood(), foodConsumedObj.getPriceFood(), foodConsumedObj.getAmount(), formater.format(total)});
            }
        }
        lblTotalCheck.setText(formater.format(totalCash) + "VND");
        globalTotalCheck = totalCash;
    }

    public void finishBill() {
        String moneyTook = txtMoneyTook.getText();
        String idBill = lblIdBill.getText();
        if (!moneyTook.strip().equals("")) {
            if (validateMoney() == true) {
                try {
                    double mT = Double.parseDouble(moneyTook);
                    dataIn.AlterSql(idBill, hourNowBill, minuteNowBill, moneyTook);
                    finalBillView finalBill = new finalBillView(pos, day, month, year, lstTypeRoom, lstRoomFull);
                    finalBill.setVisible(true);
                    lstCusOfAppFrame.get(pos).setHourOut(hourNowBill);
                    lstCusOfAppFrame.get(pos).setMinuteOut(minuteNowBill);
                    lstCusOfAppFrame.get(pos).setMoneyTook(txtMoneyTook.getText());
                    lstCusOfAppFrame.get(pos).setStt("Checked");
                    lstRoomNo.add(lstCus.get(pos).getRoomNo());
                    System.out.println(lstRoomNo);
                    modelTableApp.setValueAt(hourNowBill + ":" + minuteNowBill, pos, 8);
                    modelTableApp.setValueAt("Checked", pos, 9);
                    JOptionPane.showMessageDialog(this, "Giao dịch thành công!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Vui lòng nhậap đúng định dạng tiền tệ");
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập vào số tiền thanh toán!");
        }

    }

    public void displayChangeMoney() {
        Double changeCash = Double.parseDouble(txtMoneyTook.getText()) - globalTotalCheck;
        lblChangeMoney.setText(String.valueOf(changeCash));
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

    public boolean validateMoney() {
        boolean flag = false;
        if (isDouble(txtMoneyTook.getText()) == true) {
            moneyT = Double.parseDouble(txtMoneyTook.getText());
            if (moneyT > globalTotalCheck&&moneyT<1000000000) {
                flag = true;
            } else {
                if (moneyT < 0) {
                    flag = false;
                    JOptionPane.showMessageDialog(this, "Số tiền nhập vào không được âm");
                }
                else if(moneyT>=1000000000){
                    flag=false;
                    JOptionPane.showMessageDialog(this,"Số tiền nhập vào phải nhỏ hơn 1 tỷ VND");
                }
                else if(moneyT < globalTotalCheck) {
                    flag = false;
                    JOptionPane.showMessageDialog(this, "Số tiền nhập vào phải lớn hơn tổng tiền hóa đơn");
                }
            }
        } else {
            flag = false;
            JOptionPane.showMessageDialog(this, "Số tiền nhập vào phải là số");
        }
        return flag;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        btnGenBill = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        lblDay = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblMonth = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblChangeMoney = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMoneyTook = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblTotalCheck = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBill = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblIdBill = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblRoomType = new javax.swing.JLabel();
        lblTimeIn = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        lblRoomNo = new javax.swing.JLabel();
        lblTimeOut = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        btnGenBill.setBackground(new java.awt.Color(156, 243, 165));
        btnGenBill.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGenBill.setText("Tạo Hóa Đơn");
        btnGenBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenBillActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Hà Nội, Ngày");
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        
        
        lblDay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDay.setText("jLabel10");
        lblDay.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Tháng");
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));

        lblMonth.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMonth.setText("jLabel24");
        lblMonth.setForeground(new java.awt.Color(0, 0, 0));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Năm");
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));

        lblYear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblYear.setText("jLabel21");
        lblYear.setForeground(new java.awt.Color(0, 0, 0));

        jButton1.setBackground(new java.awt.Color(156, 243, 165));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Tính Tiền");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblChangeMoney.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblChangeMoney.setText("#change cash");
        lblChangeMoney.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Số Tiền Nhận Vào");
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Số Tiền Phải Trả Lại");
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));

        txtMoneyTook.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Tổng Cộng");
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));

        lblTotalCheck.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotalCheck.setText("#total check here");
        lblTotalCheck.setForeground(new java.awt.Color(0, 0, 0));

        tableBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID", "Tên dich vu", "Ðon giá", "So luong", "Thành tiên"
            }
        ));
        jScrollPane2.setViewportView(tableBill);
        if (tableBill.getColumnModel().getColumnCount() > 0) {
            tableBill.getColumnModel().getColumn(0).setResizable(false);
            tableBill.getColumnModel().getColumn(1).setResizable(false);
            tableBill.getColumnModel().getColumn(2).setResizable(false);
            tableBill.getColumnModel().getColumn(3).setResizable(false);
            tableBill.getColumnModel().getColumn(4).setResizable(false);
            tableBill.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("HÓA ĐƠN THANH TOÁN");
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Giới Tính:");
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Loại Phòng");
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Thời Gian Vào");
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Tên Khách Hàng:");
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));

        lblIdBill.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        lblIdBill.setText("#id here");
        lblIdBill.setForeground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Số Phòng");
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Thời Gian Ra");
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Ngày Sinh:");
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));

        lblName.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        lblName.setText("name h?");
        lblName.setForeground(new java.awt.Color(0, 0, 0));

        lblGender.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        lblGender.setText("jLabel9");
        lblGender.setForeground(new java.awt.Color(0, 0, 0));

        lblRoomType.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        lblRoomType.setText("jLabel10");
        lblRoomType.setForeground(new java.awt.Color(0, 0, 0));

        lblTimeIn.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        lblTimeIn.setText("jLabel14");
        lblTimeIn.setForeground(new java.awt.Color(0, 0, 0));

        lblDOB.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        lblDOB.setText("jLabel8");
        lblDOB.setForeground(new java.awt.Color(0, 0, 0));

        lblRoomNo.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        lblRoomNo.setText("jLabel12");
        lblRoomNo.setForeground(new java.awt.Color(0, 0, 0));

        lblTimeOut.setFont(new java.awt.Font("Serif", 0, 16)); // NOI18N
        lblTimeOut.setText("jLabel16");
        lblTimeOut.setForeground(new java.awt.Color(0, 0, 0));

        jLayeredPane1.setLayer(btnGenBill, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblDay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblMonth, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblChangeMoney, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtMoneyTook, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblTotalCheck, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblIdBill, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblGender, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblRoomType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblTimeIn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblDOB, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblRoomNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblTimeOut, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(53, 53, 53)
                        .addComponent(lblIdBill)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(txtMoneyTook, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton1))
                                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(lblChangeMoney))))
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnGenBill))
                        .addGap(25, 25, 25))))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalCheck))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGender)
                            .addComponent(lblTimeIn)
                            .addComponent(lblName)
                            .addComponent(lblRoomType))
                        .addGap(89, 89, 89)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11))
                        .addGap(32, 32, 32)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDOB)
                            .addComponent(lblRoomNo)
                            .addComponent(lblTimeOut))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(10, 10, 10))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblIdBill))
                .addGap(22, 22, 22)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel6)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel13))
                        .addGroup(jLayeredPane1Layout.createSequentialGroup()
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(lblRoomNo))
                            .addGap(10, 10, 10)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(lblTimeOut))))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName)
                            .addComponent(jLabel4)
                            .addComponent(lblDOB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRoomType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTimeIn)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTotalCheck))
                .addGap(22, 22, 22)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblDay)
                    .addComponent(jLabel16)
                    .addComponent(lblMonth)
                    .addComponent(jLabel22)
                    .addComponent(lblYear))
                .addGap(22, 22, 22)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMoneyTook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(20, 20, 20)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblChangeMoney))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnGenBill)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>                        

    private void btnGenBillActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        finishBill();
    }                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here://///////mark
        String moneyTook = txtMoneyTook.getText();
        DecimalFormat formater = new DecimalFormat("###,###,###.00");
                if (!moneyTook.strip().equals("")) {
                if(validateMoney()==true)
                lblChangeMoney.setText(String.valueOf(formater.format((moneyT - globalTotalCheck))));
    }                                        
                else{
                    JOptionPane.showMessageDialog(this,"Vui lòng nhập vào số tiền!");
                }
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnGenBill;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblChangeMoney;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblIdBill;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRoomNo;
    private javax.swing.JLabel lblRoomType;
    private javax.swing.JLabel lblTimeIn;
    private javax.swing.JLabel lblTimeOut;
    private javax.swing.JLabel lblTotalCheck;
    private javax.swing.JLabel lblYear;
    private javax.swing.JTable tableBill;
    private javax.swing.JTextField txtMoneyTook;
    // End of variables declaration                   
}
