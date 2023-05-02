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
import KaraokeApp.service.LoadImg;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author forre
 */
public class AppFrame extends javax.swing.JFrame {

    int onof = 0;
    boolean flagOfaddCell = false;
    CustomerService dataInCp = new CustomerService();
    CustomerService service = new CustomerService();
    ArrayList<Customer> lstCusAll = service.getLstCustomer();
    ArrayList<Customer> lstCusByDay;
    ArrayList<TypeRoom> lstRoomType = service.getLstRoomType();
    ArrayList<roomNo> lstRoomNoNow = service.getLstRoomNo();

    ArrayList<Customer> cusDataRealtime;

    ArrayList<roomNo> lstRoomNoFull = dataInCp.getLstRoomNo();
    ArrayList<roomNo> lstRoomNoTemp = new ArrayList<>();

    DefaultTableModel modelTable = new DefaultTableModel();
    DefaultComboBoxModel modelCboTypeRoom = new DefaultComboBoxModel();
    DefaultComboBoxModel modelCboRoomNo = new DefaultComboBoxModel();
    LoadImg loadImg = new LoadImg();
    public int pos = -1;
    public int i = 0;
    public String dayNow;
    public String monthNow;
    public String yearNow;
    public String hourNow;
    public String minuteNow;
    public String secondNow;
    public String timeStamp;

    public AppFrame() {
        initComponents();
        loadDataInToCboRoomType();
        setDefaultDate();
        disPlayBaseOndate();
        loadDataIntoCboRoomNoWithFilter();
    }

    public void clearAll() {
        txtName.setText("");
        lblID.setText("-------------");
        cboGender.setSelectedItem("Nam");
        cboTypeRoom.setSelectedIndex(0);
        txtHIn.setText("");
        txtMIn.setText("");
        lblStt.setText("-------------");
        lblDateOfbooking.setText(timeStamp);
    }

    public boolean isString(String txt) {
        boolean flag;
        try {
            flag = true;
            int x = Integer.parseInt(txt);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public boolean checkInput() {
        String mes = "";
        int count = 0;
        String name = txtName.getText();
        String hourIn = txtHIn.getText();
        String minuteIn = txtMIn.getText();
        boolean flag = false;
        if (!name.strip().equals("") && !hourIn.strip().equals("") && !minuteIn.strip().equals("") && cboRoomNo.getSelectedIndex() != -1) {
            System.out.println("check input case 1");
            if (isInt(hourIn) == true && isInt(minuteIn) == true) {
                if (Integer.parseInt(txtHIn.getText()) >= 0 && Integer.parseInt(txtHIn.getText()) <= 24
                        && Integer.parseInt(txtMIn.getText()) >= 0 && Integer.parseInt(txtMIn.getText()) <= 60
                        && !name.matches(".*\\d.*") && name.length() < 25) {
                    flag = true;
                } else if (name.matches(".*\\d.*") || name.length() > 25
                        || Integer.parseInt(txtHIn.getText()) < 0 || Integer.parseInt(txtHIn.getText()) > 24
                        || Integer.parseInt(txtMIn.getText()) < 0 || Integer.parseInt(txtMIn.getText()) > 60) {
                    if (name.length() > 25) {
                        count++;
                        mes += "\nTên phải nhỏ hơn 25 kí tự";
                    }
                    if (name.matches(".*\\d.*")) {
                        count++;
                        mes += "\nTên không được chứa số";
                    }
                    if (Integer.parseInt(txtHIn.getText()) < 0 || Integer.parseInt(txtHIn.getText()) > 24
                            || Integer.parseInt(txtMIn.getText()) < 0 || Integer.parseInt(txtMIn.getText()) > 60) {
                        count++;
                        mes += "Vui lòng nhập đúng định dạng giờ";
                    }

                    if (count > 0) {
                        flag = false;
                        JOptionPane.showMessageDialog(this, mes);
                        count = 0;
                        mes = "";
                    }

                }
            } else {
                count++;
                mes += "Vui lòng nhập đúng định dạng giờ";
                if (name.matches(".*\\d.*") || name.length() > 25) {
                    if (name.length() > 25) {
                        count++;
                        mes += "\nTên phải nhỏ hơn 25 kí tự";
                    }
                    if (name.matches(".*\\d.*")) {
                        count++;
                        mes += "\nTên không được chứa số";
                    }
                    if (count > 0) {
                        flag = false;
                        JOptionPane.showMessageDialog(this, mes);
                        count = 0;
                        mes = "";
                    }

                } else if (name.matches(".*\\d.*") || name.length() < 25) {
                    if (name.matches(".*\\d.*")) {
                        count++;
                        mes += "\nTên không được chứa số";
                    }

                    if (count > 0) {
                        flag = false;
                        JOptionPane.showMessageDialog(this, mes);
                        count = 0;
                        mes = "";
                    }
                }
            }
        } else if (isInt(hourIn) == true && isInt(minuteIn) == true) {

            if (name.strip().equals("") || hourIn.strip().equals("") || minuteIn.strip().equals("") || cboRoomNo.getSelectedIndex() == -1) {
                System.out.println("check input case 2");
                if (name.strip().equals("")) {
                    mes += "Vui lòng nhập tên khách hàng !";
                    count++;
                }
                if (txtHIn.getText().strip().equals("") || txtMIn.getText().strip().equals("")) {/////mark
                    mes += "\n Vui lòng nhập thời gian khách hàng vào !";
                    count++;
                }
                if (Integer.parseInt(txtHIn.getText()) < 0 || Integer.parseInt(txtHIn.getText()) > 24
                        || Integer.parseInt(txtMIn.getText()) < 0 || Integer.parseInt(txtMIn.getText()) > 60) {
                    count++;
                    mes += "Vui lòng nhập đúng định dạng giờ";
                }
                if (!name.strip().equals("")) {
                    if (name.matches(".*\\d.*")) {
                        count++;
                        mes += "\n Tên phải là chữ";
                    }
                }
                if (cboRoomNo.getSelectedIndex() == -1) {
                    count++;
                    mes += "\n Vui lòng phập số phòng";
                }
                if (name.length() > 25) {
                    count++;
                    mes += "\nTên phải nhỏ hơn 25 kí tự";
                }
                if (name.matches(".*\\d.*")) {
                    count++;
                    mes += "\nTên không được chứa số";
                }

                if (count > 0) {
                    JOptionPane.showMessageDialog(this, mes);
                    mes = "";
                    count = 0;
                    flag = false;
                }
            }
        } else if (isInt(hourIn) == false || isInt(minuteIn) == false) {
            if (name.strip().equals("") || hourIn.strip().equals("") || minuteIn.strip().equals("") || cboRoomNo.getSelectedIndex() == -1) {
                System.out.println("checkInput case 3");
                if (name.strip().equals("")) {
                    mes += "Vui lòng nhập tên khách hàng !";
                    count++;
                }
                if (txtHIn.getText().strip().equals("") || txtMIn.getText().strip().equals("")) {/////mark
                    mes += "\n Vui lòng nhâp thời gian khách hàng vào !";
                    count++;
                }
                if (!name.strip().equals("")) {
                    if (name.matches(".*\\d.*")) {
                        count++;
                        mes += "\n Tên phải là chữ";
                    }
                }
                if (cboRoomNo.getSelectedIndex() == -1) {
                    count++;
                    mes += "\n Vui lòng nhập số phòng";
                }
                if (name.length() > 25) {
                    count++;
                    mes += "\nTên pahir nhoe hơn 25 kí tự";
                }
                if (name.matches(".*\\d.*")) {
                    count++;
                    mes += "\nTên không được chứa số";
                }
                if (!hourIn.strip().equals("") && !minuteIn.strip().equals("")) {
                    if (isInt(hourIn) == false || isInt(minuteIn) == false) {
                        count++;
                        mes += "\n Vui lòng nhập đúng định dạng giờ!";
                    }
                }
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, mes);
                    mes = "";
                    count = 0;
                    flag = false;
                }
            }
        }

        return flag;
    }

    public boolean isInt(String txt) {
        boolean target = false;
        try {
            int x = Integer.valueOf(txt);
            target = true;
        } catch (Exception e) {
            target = false;
        }
        return target;
    }

    public void addCellTest() {
        if (onof == 0) {
            //String id;
            flagOfaddCell = false;
            String mes = "";
            int count = 0;
            String name = txtName.getText();
            String DOB = cboDobDay.getSelectedItem().toString() + "/" + cboDobMonth.getSelectedItem().toString() + "/" + cboDobYear.getSelectedItem().toString();
            String gender = (String) cboGender.getSelectedItem();
            TypeRoom roomType = new TypeRoom();
            roomNo roomNo = new roomNo();
            FoodConsumed food = new FoodConsumed();
            String hourIn = txtHIn.getText();
            String minuteIn = txtMIn.getText();
            for (TypeRoom roomTypeObj : lstRoomType) {
                if (roomTypeObj.getNameRoomType().equals(cboTypeRoom.getSelectedItem())) {
                    roomType = roomTypeObj;
                    break;
                }
            }
            for (roomNo lstRoomNoObj : lstRoomNoFull) {
                if (lstRoomNoObj.getNameRoom().equals(cboRoomNo.getSelectedItem())) {
                    roomNo = lstRoomNoObj;
                }
            }
            if (checkInput() == true) {
                try {
                    System.out.println("test new func ");
                    int hour = Integer.parseInt(hourIn);
                    int minute = Integer.parseInt(minuteIn);
                    i++;
                    Customer cusForPushDown = service.addDataToDb(new Customer(name, "", DOB, gender, roomType, roomNo, hourIn, minuteIn, "", "", "Not yet", lblDateOfbooking.getText(), ""), lstRoomType, lstRoomNoFull);
                    cusDataRealtime.add(cusForPushDown);
                    loadDataIntoCboRoomNoWithFilter();
                    modelTable.addRow(new Object[]{i, cusForPushDown.getName(), cusForPushDown.getIdBill(), cusForPushDown.getDOB(), cusForPushDown.getGender(), cusForPushDown.getRoomType().getNameRoomType(), cusForPushDown.getRoomNo().getNameRoom(), cusForPushDown.getHourIn() + ":" + cusForPushDown.getMinuteIn(), "", cusForPushDown.getStt(), cusForPushDown.getDate()});
                    JOptionPane.showMessageDialog(this, "Thêm thành công !");
                    flagOfaddCell = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng refresh lại dữ liệu");
        }
    }

    public void setDefaultDate() {
        timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        dayNow = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        monthNow = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
        yearNow = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        hourNow = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
        minuteNow = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
        secondNow = new SimpleDateFormat("ss").format(Calendar.getInstance().getTime());
        cboDay.setSelectedItem(dayNow);
        cboMonth.setSelectedItem(monthNow);
        cboYear.setSelectedItem(yearNow);
        lblDateOfbooking.setText(timeStamp);
    }

    public void disPlayBaseOndate() {
        i = 0;
        String day = (String) cboDay.getSelectedItem();
        String month = (String) cboMonth.getSelectedItem();
        String year = (String) cboYear.getSelectedItem();
        cusDataRealtime = service.getCusByDate(day, month, year, lstRoomType, lstRoomNoFull);
        lstCusByDay = new ArrayList<>();
        modelTable = (DefaultTableModel) table.getModel();
        modelTable.setRowCount(0);
        for (Customer cusOb : cusDataRealtime) {
            i++;
            lstCusByDay.add(cusOb);
            modelTable.addRow(new Object[]{i, cusOb.getName(), cusOb.getIdBill(), cusOb.getDOB(), cusOb.getGender(), cusOb.getRoomType().getNameRoomType(), cusOb.getRoomNo().getNameRoom(), cusOb.getHourIn() + ":" + cusOb.getMinuteIn(), cusOb.getHourOut() + ":" + cusOb.getMinuteOut(), cusOb.getStt(), cusOb.getDate()});
        }
    }

    public void loadDataInToCboRoomType() {
        modelCboTypeRoom = (DefaultComboBoxModel) cboTypeRoom.getModel();
        for (TypeRoom RoomTypeOb : lstRoomType) {
            modelCboTypeRoom.addElement(RoomTypeOb.getNameRoomType());
        }
    }

    public void loadDataIntoRoomNo() {
        modelCboRoomNo = (DefaultComboBoxModel) cboRoomNo.getModel();
        modelCboRoomNo.removeAllElements();
        for (roomNo RoomNoOb : lstRoomNoFull) {
            modelCboRoomNo.addElement(RoomNoOb.getNameRoom());
        }
    }

    public void loadDataIntoCboRoomNoWithFilter() {
        dayNow = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        monthNow = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
        yearNow = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        modelCboRoomNo = (DefaultComboBoxModel) cboRoomNo.getModel();
        modelCboRoomNo.removeAllElements();
        if (cboDay.getSelectedItem().equals(dayNow)
                && cboMonth.getSelectedItem().equals(monthNow)
                && cboYear.getSelectedItem().equals(yearNow)) {
            //remove room is used
            for (Customer cusOb : cusDataRealtime) {
                if (cusOb.getStt().equals("Not yet")) {
                    lstRoomNoNow.remove(cusOb.getRoomNo());

                }
            }

            for (roomNo roomNoOb : lstRoomNoNow) {
                if (roomNoOb.getTypeRoom().getNameRoomType().equals(cboTypeRoom.getSelectedItem())) {
                    modelCboRoomNo.addElement(roomNoOb.getNameRoom());
                }
            }

        } else {

            for (roomNo roomNoOb : lstRoomNoNow) {
                if (roomNoOb.getTypeRoom().equals(cboTypeRoom.getSelectedItem())) {
                    modelCboRoomNo.addElement(roomNoOb.getNameRoom());
                }
            }
        }

    }

    public void loadDataIntoTableCustomerList() {
        int ii = 0;
        modelTable.setRowCount(0);
        modelTable = (DefaultTableModel) table.getModel();
        for (Customer cusOb : lstCusAll) {
            ii++;
            modelTable.addRow(new Object[]{ii, cusOb.getName(), cusOb.getIdBill(), cusOb.getDOB(), cusOb.getGender(), cusOb.getRoomType(), cusOb.getRoomNo(), cusOb.getHourIn() + ":" + cusOb.getMinuteIn(), cusOb.getHourOut() + ":" + cusOb.getMinuteOut(), null, cusOb.getDate()});
        }
    }

    public void disPlayData() {
        txtName.setText(cusDataRealtime.get(pos).getName());
        lblID.setText(cusDataRealtime.get(pos).getIdBill());
        String DOB = cboDobDay.getSelectedItem().toString() + "/" + cboDobMonth.getSelectedItem().toString() + "/" + cboDobYear.getSelectedItem().toString();
        cboDobDay.setSelectedItem(cusDataRealtime.get(pos).getDOB().substring(0, 2));
        cboDobMonth.setSelectedItem(cusDataRealtime.get(pos).getDOB().substring(3, 5));
        cboDobYear.setSelectedItem(cusDataRealtime.get(pos).getDOB().substring(6, 10));
        cboGender.setSelectedItem(cusDataRealtime.get(pos).getGender());
        cboTypeRoom.setSelectedItem(cusDataRealtime.get(pos).getRoomType().getNameRoomType());
        cboRoomNo.setSelectedItem(cusDataRealtime.get(pos).getRoomNo().getNameRoom());
        txtHIn.setText((cusDataRealtime.get(pos).getHourIn()));
        txtMIn.setText((cusDataRealtime.get(pos).getMinuteIn()));
        lblStt.setText(cusDataRealtime.get(pos).getStt());
        lblDateOfbooking.setText(cusDataRealtime.get(pos).getDate());
    }

    public void fixCusBillToSql() {
        roomNo roomNoTraceChange = cusDataRealtime.get(pos).getRoomNo();
        String mes = "";
        int count = 0;
        String name = txtName.getText();
        String DOB = cboDobDay.getSelectedItem().toString() + "/" + cboDobMonth.getSelectedItem().toString() + "/" + cboDobYear.getSelectedItem().toString();
        String gender = (String) cboGender.getSelectedItem();
        TypeRoom roomType = new TypeRoom();
        roomNo roomNo = new roomNo();
        for (TypeRoom roomTypeObj : lstRoomType) {
            if (roomTypeObj.getNameRoomType().equals(cboTypeRoom.getSelectedItem())) {
                roomType = roomTypeObj;
                break;
            }
        }
        for (roomNo lstRoomNoObj : lstRoomNoFull) {
            if (lstRoomNoObj.getNameRoom().equals(cboRoomNo.getSelectedItem())) {
                roomNo = lstRoomNoObj;
            }
        }
        FoodConsumed food = new FoodConsumed();
        try {
            String hourIn = txtHIn.getText();
            String minuteIn = txtMIn.getText();
            int hour = Integer.parseInt(hourIn);
            int minute = Integer.parseInt(minuteIn);
            String idBillPara = lblID.getText();
            service.updateCusToSql(idBillPara, new Customer(name, "", DOB, gender, roomType, roomNo, hourIn, minuteIn, "", "", "Not yet", lblDateOfbooking.getText(), ""), lstRoomType, lstRoomNoFull);
            cusDataRealtime.get(pos).setName(name);
            cusDataRealtime.get(pos).setDOB(DOB);
            cusDataRealtime.get(pos).setGender(gender);
            cusDataRealtime.get(pos).setRoomType(roomType);
            cusDataRealtime.get(pos).setRoomNo(roomNo);
            cusDataRealtime.get(pos).setHourIn(hourIn);
            cusDataRealtime.get(pos).setMinuteIn(minuteIn);
            lstRoomNoNow.add(roomNoTraceChange);
            modelTable.setValueAt(name, pos, 1);//name
            modelTable.setValueAt(idBillPara, pos, 2);//name
            modelTable.setValueAt(DOB, pos, 3);//dob
            modelTable.setValueAt(gender, pos, 4);//gender
            modelTable.setValueAt(roomType.getNameRoomType(), pos, 5);//roomtype
            modelTable.setValueAt(roomNo.getNameRoom(), pos, 6);//roomno
            modelTable.setValueAt(hourIn + ":" + minuteIn, pos, 7);
            JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công");
        } catch (Exception e) {
            e.printStackTrace();
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cboDay = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cboMonth = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHIn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMIn = new javax.swing.JTextField();
        btnGenBill = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnNow = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblDateOfbooking = new javax.swing.JLabel();
        cboRoomNo = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblStt = new javax.swing.JLabel();
        cboGender = new javax.swing.JComboBox<>();
        lblID = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboTypeRoom = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnIncomeOnDate = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnNewBill = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnAddFood = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        cboDobDay = new javax.swing.JComboBox<>();
        cboDobMonth = new javax.swing.JComboBox<>();
        cboDobYear = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 153, 255));
        setForeground(java.awt.Color.orange);

        jLayeredPane1.setBackground(new java.awt.Color(51, 0, 51));
        jLayeredPane1.setOpaque(true);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ Tên", "ID hóa đơn", "Ngày sinh", "Giới tính", "Loại phòng", "Phòng số", "Giờ vào", "Giờ đi", "Trạng thái thanh toán", "Ngày"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        cboDay.setBackground(new java.awt.Color(156, 243, 165));
        cboDay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tháng");

        cboMonth.setBackground(new java.awt.Color(156, 243, 165));
        cboMonth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Năm");

        cboYear.setBackground(new java.awt.Color(156, 243, 165));
        cboYear.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022", "2023" }));

        btnFilter.setBackground(new java.awt.Color(156, 243, 165));
        btnFilter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFilter.setText("Lọc Dữ Liệu");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ngày");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Giờ");

        txtHIn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtHIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHInActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Phút");

        txtMIn.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        //ImageIcon imgIconGenBill = new ImageIcon(loadImg.getGenBillImg());
        //Image imgNewGenBill = imgIconGenBill.getImage();
        //Image newImgGenBill =  imgNewGenBill.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        //ImageIcon newImageIconGenBill = new ImageIcon(newImgGenBill);
        //btnGenBill.setIcon(newImageIconGenBill);
        btnGenBill.setBackground(new java.awt.Color(156, 243, 165));
        btnGenBill.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGenBill.setText("Tạo Hóa Đơn");
        btnGenBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenBillActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(156, 243, 165));
        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton12.setText("Tìm Kiếm");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Phòng Số:");

        btnNow.setBackground(new java.awt.Color(156, 243, 165));
        btnNow.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNow.setText("Nhập Giờ");
        btnNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNowActionPerformed(evt);
            }
        });

        //ImageIcon imgIconUpdate = new ImageIcon(loadImg.getAddImg());
        //Image imgNewUpdate = imgIconUpdate.getImage();
        //Image newImgUpdate =  imgNewUpdate.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        //ImageIcon newImageIconUpdate = new ImageIcon(newImgUpdate);
        //btnUpdate.setIcon(newImageIconUpdate);
        btnUpdate.setBackground(new java.awt.Color(156, 243, 165));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Ngày Vào:");

        LoadImg loadImg = new LoadImg();
        ImageIcon imgIconNew = new ImageIcon(loadImg.getDirToImg());
        Image imgNew = imgIconNew.getImage();
        Image newImgNew =  imgNew.getScaledInstance(400,45,Image.SCALE_SMOOTH);
        ImageIcon newImageIconNew = new ImageIcon(newImgNew);
        jLabel7.setIcon(newImageIconNew);
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        lblDateOfbooking.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDateOfbooking.setForeground(new java.awt.Color(255, 255, 255));
        lblDateOfbooking.setText("--------------");

        cboRoomNo.setBackground(new java.awt.Color(156, 243, 165));
        cboRoomNo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Trạng Thái:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Giới Tính:");

        lblStt.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblStt.setForeground(new java.awt.Color(255, 255, 255));
        lblStt.setText("--------------");

        cboGender.setBackground(new java.awt.Color(156, 243, 165));
        cboGender.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nu", " " }));

        lblID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setText("----------");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Loại Phòng:");

        cboTypeRoom.setBackground(new java.awt.Color(156, 243, 165));
        cboTypeRoom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cboTypeRoom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTypeRoomItemStateChanged(evt);
            }
        });
        cboTypeRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTypeRoomMouseClicked(evt);
            }
        });
        cboTypeRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTypeRoomActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Họ Tên:");

        btnIncomeOnDate.setBackground(new java.awt.Color(156, 243, 165));
        btnIncomeOnDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnIncomeOnDate.setText("Doanh Thu");
        btnIncomeOnDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncomeOnDateActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ID Hóa Đơn:");

        btnNewBill.setBackground(new java.awt.Color(156, 243, 165));
        //ImageIcon imgIconAdd = new ImageIcon(loadImg.getAddImg());
        //Image imgNewAdd = imgIconAdd.getImage();
        //Image newImgAdd =  imgNewAdd.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        //ImageIcon newImageIconAdd = new ImageIcon(newImgAdd);
        //btnNewBill.setIcon(newImageIconAdd);
        btnNewBill.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNewBill.setText("Tạo Mới");
        btnNewBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewBillActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày Sinh:");

        btnAddFood.setBackground(new java.awt.Color(156, 243, 165));
        btnAddFood.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddFood.setText("Thêm Dịch Vụ");
        btnAddFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFoodActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Giờ vào:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Thêm Đồ Ăn:");

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnRefresh.setBackground(new java.awt.Color(156, 243, 165));
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        cboDobDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cboDobMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cboDobYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ngày");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tháng");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Năm");

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboDay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboMonth, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnFilter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtHIn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtMIn, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnGenBill, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnNow, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnUpdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblDateOfbooking, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboRoomNo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblStt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboGender, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboTypeRoom, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnIncomeOnDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnNewBill, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnAddFood, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnRefresh, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboDobDay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboDobMonth, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboDobYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))
                                .addGap(44, 44, 44)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnNow, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboDobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(24, 24, 24)
                                                .addComponent(jLabel12)
                                                .addGap(13, 13, 13)
                                                .addComponent(cboDobYear, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNewBill, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGenBill))
                                .addGap(49, 49, 49)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 59, Short.MAX_VALUE)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnIncomeOnDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRefresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel14))
                        .addGap(39, 39, 39)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHIn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMIn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDateOfbooking, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStt)
                                    .addComponent(lblID)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboDobDay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cboGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboTypeRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboRoomNo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddFood))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)))))
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFilter)))
                .addContainerGap())
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddFood, cboGender, cboRoomNo, cboTypeRoom});

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnUpdate, jButton12});

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnIncomeOnDate, btnRefresh});

        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(cboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFilter))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblID))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)
                            .addComponent(cboDobDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(cboDobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(cboDobYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cboTypeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboRoomNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtHIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtMIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNow)
                            .addComponent(jLabel4))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(lblStt))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lblDateOfbooking))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(btnAddFood))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNewBill, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncomeOnDate))
                        .addGap(21, 21, 21)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRefresh)
                            .addComponent(jButton12)
                            .addComponent(btnGenBill)))
                    .addComponent(jScrollPane1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnIncomeOnDate, btnRefresh});

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUpdate, jButton12});

        jLayeredPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnGenBill, btnNewBill});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenBillActionPerformed
        // TODO add your handling code here:
        if (pos != -1) {
            roomNo roomTemp = cusDataRealtime.get(pos).getRoomNo();
            hourNow = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
            minuteNow = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
            if (cusDataRealtime.get(pos).getStt().strip().equals("Checked")) {
                finalBillView finalBillDisplay = new finalBillView(pos, cboDay.getSelectedItem().toString(), cboMonth.getSelectedItem().toString(), cboYear.getSelectedItem().toString(), lstRoomType, lstRoomNoFull);
                finalBillDisplay.setVisible(true);
            } else {
                try {
                    modelTable = (DefaultTableModel) table.getModel();
                    billFrame billDisplay = new billFrame(pos, hourNow, minuteNow, cboDay.getSelectedItem().toString(), cboMonth.getSelectedItem().toString(), cboYear.getSelectedItem().toString(), lstRoomType, lstRoomNoFull, modelTable, lstRoomNoNow, cusDataRealtime);
                    billDisplay.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Vui lòng tạo thông tin truớc khi tạo hóa đơn");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn bạn muốn tạo trước");
        }
    }//GEN-LAST:event_btnGenBillActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        SearchFrame searchDisplay = new SearchFrame(cusDataRealtime, table);
        searchDisplay.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnAddFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFoodActionPerformed
        // TODO add your handling code here:
        if (pos != -1) {
            if (cusDataRealtime.get(pos).getStt().equals("Not yet")) {
                try {
                    addFoodFrame foodDisplay = new addFoodFrame(pos, cusDataRealtime);
                    foodDisplay.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Chọn hóa đơn trước khi thêm dịch vụ");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hoa đơn đã đóng,không được thêm dịch vụ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thêm dịch vụ");
        }

    }//GEN-LAST:event_btnAddFoodActionPerformed

    private void btnNewBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewBillActionPerformed
        // TODO add your handling code here:
        addCellTest();
        if (flagOfaddCell == true) {
            clearAll();
//            setDefaultDate();
//            disPlayBaseOndate();
            loadDataIntoCboRoomNoWithFilter();
            onof = 0;
            flagOfaddCell = false;
        }
    }//GEN-LAST:event_btnNewBillActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        modelCboRoomNo = (DefaultComboBoxModel) cboRoomNo.getModel();
        onof = 1;
        pos = table.getSelectedRow();
        cboTypeRoom.setSelectedItem(cusDataRealtime.get(pos).getRoomType().getNameRoomType());
        System.out.println(cusDataRealtime.get(pos));
        if (cusDataRealtime.get(pos).getMoneyTook().equalsIgnoreCase("")) {
            modelCboRoomNo.removeAllElements();
            loadDataIntoCboRoomNoWithFilter();
        } else {
            loadDataIntoCboRoomNoWithFilter();
            modelCboRoomNo.removeAllElements();
        }

//        if (cusDataRealtime.get(pos).getMoneyTook().equalsIgnoreCase("")) {
        System.out.println("check room ");
        modelCboRoomNo.addElement(cusDataRealtime.get(pos).getRoomNo().getNameRoom());
        for (roomNo lstOb : lstRoomNoNow) {
            if (lstOb.getNameRoom().equals(cusDataRealtime.get(pos).getRoomNo())) {
                return;
            }
            modelCboRoomNo.removeElement(cusDataRealtime.get(pos).getRoomNo());
            break;
        }
        if (!cboDay.getSelectedItem().equals(dayNow) || !cboMonth.getSelectedItem().equals(monthNow) || !cboYear.getSelectedItem().equals(yearNow)) {
            modelCboRoomNo.addElement(cusDataRealtime.get(pos).getRoomNo().getNameRoom());
        }
        disPlayData();
    }//GEN-LAST:event_tableMouseClicked

    private void txtHInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHInActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        disPlayBaseOndate();
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        clearAll();
        setDefaultDate();
        disPlayBaseOndate();
        loadDataIntoCboRoomNoWithFilter();
        onof = 0;
        pos = -1;
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cboTypeRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTypeRoomMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTypeRoomMouseClicked

    private void cboTypeRoomItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTypeRoomItemStateChanged
        // TODO add your handling code here:
        modelCboRoomNo.removeAllElements();
        loadDataIntoCboRoomNoWithFilter();
    }//GEN-LAST:event_cboTypeRoomItemStateChanged

    private void btnNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNowActionPerformed
        // TODO add your handling code here:
        txtHIn.setText(hourNow);
        txtMIn.setText(minuteNow);
    }//GEN-LAST:event_btnNowActionPerformed

    private void cboTypeRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTypeRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTypeRoomActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (pos != -1) {
            if (cusDataRealtime.get(pos).getStt().equals("Not yet")) {
                if (checkInput() == true) {
                    int choice = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa hóa đơn ?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        pos = table.getSelectedRow();
                        fixCusBillToSql();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Hóa đơn đã đóng không được sửa !");
            }
    }//GEN-LAST:event_btnUpdateActionPerformed
        else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần sửa");
        }
    }

    private void btnIncomeOnDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncomeOnDateActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                revenueFrame reVeFrame = new revenueFrame(lstRoomNoFull, lstRoomType);
                reVeFrame.setVisible(true);
                
            }
        });
    }//GEN-LAST:event_btnIncomeOnDateActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AppFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AppFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFood;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnGenBill;
    private javax.swing.JButton btnIncomeOnDate;
    private javax.swing.JButton btnNewBill;
    private javax.swing.JButton btnNow;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboDay;
    private javax.swing.JComboBox<String> cboDobDay;
    private javax.swing.JComboBox<String> cboDobMonth;
    private javax.swing.JComboBox<String> cboDobYear;
    private javax.swing.JComboBox<String> cboGender;
    private javax.swing.JComboBox<String> cboMonth;
    private javax.swing.JComboBox<String> cboRoomNo;
    private javax.swing.JComboBox<String> cboTypeRoom;
    private javax.swing.JComboBox<String> cboYear;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDateOfbooking;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblStt;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtHIn;
    private javax.swing.JTextField txtMIn;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
