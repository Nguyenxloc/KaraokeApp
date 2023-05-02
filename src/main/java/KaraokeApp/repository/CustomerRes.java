/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.repository;

import KaraokeApp.model.Conn;
import KaraokeApp.model.Customer;
import KaraokeApp.model.FoodConsumed;
import KaraokeApp.model.TypeRoom;
import KaraokeApp.model.roomNo;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author 84374
 */
public class CustomerRes {

    ArrayList<Customer> lstCustomer;

    public CustomerRes() {
        this.lstCustomer = new ArrayList<>();
    }

    public void addToList() {
        foodConsumedRes foodList = new foodConsumedRes();
        foodList.addToList();
        TypeRoomRes roomTypeList = new TypeRoomRes();
        roomTypeList.addIntoList();
        roomRes roomNoList = new roomRes();
        roomNoList.addToList();
//        lstCustomer.add(new Customer("Nguyen Van A", "hd001", "2000-03-02", "Nam", roomTypeList.getLstRoomType().get(0), roomNoList.getLstRoom().get(0), "7", "15", "9", "15", "Checked", "2023-03-26", "1000000.0"));
//        lstCustomer.add(new Customer("Nguyen Van B", "hd002", "2001-04-05", "Nu", roomTypeList.getLstRoomType().get(1), roomNoList.getLstRoom().get(1), "7", "15", "", "", "Not yet", "2023-03-26", ""));
//        lstCustomer.add(new Customer("Nguyen Van C", "hd003", "2002-05-06", "Nam", roomTypeList.getLstRoomType().get(2), roomNoList.getLstRoom().get(2), "7", "15", "8", "15", "Checked", "2023-03-26", "500000.0"));
//        lstCustomer.add(new Customer("Nguyen Van D", "hd004", "1998-06-07", "Nu", roomTypeList.getLstRoomType().get(1), roomNoList.getLstRoom().get(3), "7", "15", "", "", "Not yet", "2023-03-26", ""));
//        lstCustomer.add(new Customer("Nguyen Van E", "hd005", "1999-04-09", "Nam", roomTypeList.getLstRoomType().get(1), roomNoList.getLstRoom().get(8), "7", "15", "", "", "Not yet", "2023-03-26", ""));
    }

    public ArrayList<Customer> getLstCustomer() {
        return lstCustomer;
    }

    public void setLstCustomer(ArrayList<Customer> lstCustomer) {
        this.lstCustomer = lstCustomer;
    }

    public static Customer pushCusToSql(Customer cus, ArrayList<TypeRoom> lstRoomType, ArrayList<roomNo> lstRoomNoFull) {
        Customer cusRes = new Customer();
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            String sql = "INSERT INTO dbo.CUSTOMER\n"
                    + "VALUES\n"
                    + "(   ?, -- FULLNAME - varchar(100)\n"
                    + "    ?, -- DOB - varchar(20)\n"
                    + "    ?, -- GENDER - varchar(20)\n"
                    + "    ?, -- TYPEROOM - varchar(50)\n"
                    + "    ?, -- ROOMNO - varchar(50)\n"
                    + "    ?, -- HOURIN - varchar(5)\n"
                    + "    ?, -- MINUTEIN - varchar(5)\n"
                    + "    ?, -- HOUROUT - varchar(5)\n"
                    + "    ?, -- MINUTEOUT - varchar(5)\n"
                    + "    ?, -- DATEIN - varchar(20)\n"
                    + "    ?  -- MONEYTOOK - varchar(20)\n"
                    + "    )";
            PreparedStatement pp = conn.prepareStatement(sql);
            pp.setString(1, cus.getName());
            pp.setString(2, cus.getDOB());
            pp.setString(3, cus.getGender());
            pp.setString(4, cus.getRoomType().getNameRoomType());
            pp.setString(5, cus.getRoomNo().getNameRoom());
            pp.setString(6, cus.getHourIn());
            pp.setString(7, cus.getMinuteIn());
            pp.setString(8, cus.getHourOut());
            pp.setString(9, cus.getMinuteOut());
            pp.setString(10, cus.getDate());
            pp.setString(11, cus.getMoneyTook());
            pp.executeUpdate();
            Statement stmt = conn.createStatement();
            String sqlGetLast = "SELECT TOP 1 *FROM dbo.CUSTOMER\n"
                    + "ORDER BY NUMORDER DESC;";
            ResultSet rs = stmt.executeQuery(sqlGetLast);
            while (rs.next()) {
                //(FULLNAME,DOB,GENDER,TYPEROOM,ROOMNO,HOURIN,MINUTEIN,HOUROUT,MINUTEOUT,DATEIN,MONEYTOOK)
                String sttRes = "";
                if (!rs.getString("MONEYTOOK").equals("")) {
                    sttRes = "Checked";
                } else {
                    sttRes = "Not yet";
                }
                TypeRoom typeRoomOb = new TypeRoom();
                roomNo roomNoOb = new roomNo();
                for (TypeRoom typeRoom : lstRoomType) {
                    if (typeRoom.getNameRoomType().equalsIgnoreCase(rs.getString("TYPEROOM"))) {
                        typeRoomOb = typeRoom;
                        break;
                    }
                }
                for (roomNo no : lstRoomNoFull) {
                    if (no.getNameRoom().equalsIgnoreCase(rs.getString("ROOMNO"))) {
                        roomNoOb = no;
                    }
                }
                cusRes = new Customer(rs.getString("FULLNAME"), rs.getString("IDBILL"), rs.getString("DOB"), rs.getString("GENDER"), typeRoomOb, roomNoOb, rs.getString("HOURIN"), rs.getString("MINUTEIN"), rs.getString("HOUROUT"), rs.getString("MINUTEOUT"), sttRes, rs.getString("DATEIN"), rs.getString("MONEYTOOK"));
            }
            stmt.close();
            pp.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cusRes;
    }

    public static ArrayList<Customer> getDataByDate(String day, String month, String year, ArrayList<TypeRoom> lstRoomTypeRam, ArrayList<roomNo> lstRoomNoRam) {
        ArrayList<Customer> lstCusSqlByDate = new ArrayList<>();
        String dd = "";
        String mm = "";
        String yyyy = "";
        String stt = "";
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            Statement stmt = conn.createStatement();
            StringBuilder x = new StringBuilder();
            x.append("'");
            x.append(day);
            x.append("/");
            x.append(month);
            x.append("/");
            x.append(year);
            x.append("'");
            System.out.println(x);
            String sql = "SELECT *FROM CUSTOMER WHERE DATEIN=" + x;
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(Conn.getInstance().url);
            while (rs.next()) {
                if (!rs.getString("MONEYTOOK").strip().equals("")) {
                    stt = "Checked";
                } else {
                    stt = "Not yet";
                }
                TypeRoom roomTypeOb = new TypeRoom();
                roomNo roomNoOb = new roomNo();
                for (TypeRoom typeRooomObLoop : lstRoomTypeRam) {
                    if (typeRooomObLoop.getNameRoomType().equalsIgnoreCase(rs.getString("TYPEROOM"))) {
                        roomTypeOb = typeRooomObLoop;
                    }
                }
                for (roomNo roomNoObloop : lstRoomNoRam) {
                    if (roomNoObloop.getNameRoom().equalsIgnoreCase(rs.getString("ROOMNO"))) {
                        roomNoOb = roomNoObloop;
                    }
                }
                lstCusSqlByDate.add(new Customer(rs.getString("FULLNAME"), rs.getString("IDBILL"), rs.getString("DOB"), rs.getString("GENDER"), roomTypeOb, roomNoOb, rs.getString("HOURIN"), rs.getString("MINUTEIN"), rs.getString("HOUROUT"), rs.getString("MINUTEOUT"), stt, rs.getString("DATEIN"), rs.getString("MONEYTOOK")));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstCusSqlByDate;
    }

    public static void alterDB(String idBill, String hourNowBill, String minuteNowBill, String moneyTook) {
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            String sql = "UPDATE dbo.CUSTOMER\n"
                    + "SET HOUROUT=?, MINUTEOUT=?, MONEYTOOK=?\n"
                    + "WHERE IDBILL=?;";
            PreparedStatement pp = conn.prepareStatement(sql);
            pp.setString(1, hourNowBill);
            pp.setString(2, minuteNowBill);
            pp.setString(3, moneyTook);
            pp.setString(4, idBill);
            pp.executeUpdate();
            pp.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateCusToSql(String idBillPara, Customer customer, ArrayList<TypeRoom> lstRoomType, ArrayList<roomNo> lstRoomNoFull) {
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);

            String sql = "UPDATE dbo.CUSTOMER\n"
                    + "SET FULLNAME=?,DOB=?,GENDER=?,TYPEROOM=?,ROOMNO=?,HOURIN=?,MINUTEIN=?\n"
                    + "WHERE IDBILL=?; ";
            PreparedStatement pp = conn.prepareStatement(sql);
            pp.setString(1, customer.getName());
            pp.setString(2, customer.getDOB());
            pp.setString(3, customer.getGender());
            pp.setString(4, customer.getRoomType().getNameRoomType());
            pp.setString(5, customer.getRoomNo().getNameRoom());
            pp.setString(6, customer.getHourIn());
            pp.setString(7, customer.getMinuteIn());
            pp.setString(8, idBillPara);
            pp.executeUpdate();
            pp.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getTotalRevenueRoomBaseDateRange(String dayStart, String monthStart, String yearStart, String dayEnd, String monthEnd, String yearEnd, ArrayList<roomNo> lstRoomNoRam, ArrayList<TypeRoom> lstRoomTypeRam) {
        ArrayList<Customer> lstCustomerForRevenue = new ArrayList<>();
        int dayS = Integer.parseInt(dayStart);
        int monthS = Integer.parseInt(monthStart);
        int yearS = Integer.parseInt(yearStart);
        int dayE = Integer.parseInt(dayEnd);
        int monthE = Integer.parseInt(monthEnd);
        int yearE = Integer.parseInt(yearEnd);
        int count = 0;
        double totalRevenueRoom = 0.0;
//        String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//        String user = "sa";
//        String password = "123456";
        String stt = "";
        try {
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            Statement stmt = conn.createStatement();
            StringBuilder xo = new StringBuilder();
            xo.append("BETWEEN")
                    .append("'").append(yearStart).append("-").append(monthStart).append("-").append(dayStart).append("'")
                    .append("AND")
                    .append("'").append(yearEnd).append("-").append(monthEnd).append("-").append(dayEnd).append("'");
            String sql = "SELECT CONCAT(SUBSTRING(DATEIN,7,4),'-',SUBSTRING(DATEIN,4,2),'-',SUBSTRING(DATEIN,1,2)) AS newdate,\n"
                    + "SUM((((CAST(cu.HOUROUT AS FLOAT)+(CAST(cu.MINUTEOUT AS FLOAT)/60))) -(CAST(cu.HOURIN AS FLOAT)+(CAST(cu.MINUTEIN AS FLOAT)/60)))*CAST(ty.PRICEONHOUR AS FLOAT)) AS totalRoomfee\n"
                    + "FROM dbo.CUSTOMER cu\n"
                    + "JOIN dbo.TYPEROOM ty\n"
                    + "ON cu.TYPEROOM=ty.NAMETYPE\n"
                    + "WHERE CONCAT(SUBSTRING(DATEIN,7,4),'-',SUBSTRING(DATEIN,4,2),'-',SUBSTRING(DATEIN,1,2))" + xo
                    + "AND cu.MONEYTOOK!='' GROUP BY CONCAT(SUBSTRING(DATEIN,7,4),'-',SUBSTRING(DATEIN,4,2),'-',SUBSTRING(DATEIN,1,2))";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                totalRevenueRoom += Double.parseDouble(rs.getString("totalRoomfee"));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalRevenueRoom;
    }

}
