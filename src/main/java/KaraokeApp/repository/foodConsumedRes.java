/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.repository;

import KaraokeApp.model.Conn;
import KaraokeApp.model.FoodConsumed;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 84374
 */
public class foodConsumedRes {

    private ArrayList<FoodConsumed> lstFood;

    public foodConsumedRes() {
        lstFood = new ArrayList<>();
    }

    public void addToList() {
        getFoodConsumedFromSql();
//        lstFood.add(new FoodConsumed("HD14", "f001", "Snack", "Pack", 2.0, 20000.0));
//        lstFood.add(new FoodConsumed("HD15", "f002", "Fruit", "Dish", 2.0, 40000.0));
//        lstFood.add(new FoodConsumed("HD16", "f003", "Bia", "Bottle", 2.0, 30000.0));
//        lstFood.add(new FoodConsumed("HD17", "f004", "Coca", "Bottle", 2.0, 25000.0));
//        lstFood.add(new FoodConsumed("HD18", "f004", "Coca", "Bottle", 2.0, 25000.0));
//        lstFood.add(new FoodConsumed("HD19", "f004", "Coca", "Bottle", 2.0, 25000.0));
    }

    public ArrayList<FoodConsumed> getLstFood() {
        return lstFood;
    }

    public void setLstFood(ArrayList<FoodConsumed> lstFood) {
        this.lstFood = lstFood;
    }

    public double getTotalFood(Double totalSQL) {
        return totalSQL;
    }

    public static void addFoodConsumedToSql(ArrayList<FoodConsumed> lstForReady) {
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            Statement stmt = conn.createStatement();
            StringBuilder x = new StringBuilder();
            for (FoodConsumed foodConsumedOb : lstForReady) {
                x.append("(").append("'").append(foodConsumedOb.getIdBill()).append("'").append(",").append("'").append(foodConsumedOb.getIdFood()).append("'")
                        .append(",").append("'").append(foodConsumedOb.getAmount()).append("'").append(")");
                String sql = "INSERT INTO dbo.FOODCONSUMED VALUES" + x;
                stmt.executeUpdate(sql);
                x = new StringBuilder();
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getFoodConsumedFromSql() {
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            Statement stmt = conn.createStatement();
            String sql = "SELECT IDBILL,A2.IDFOOD,NAMEOFDISH,UNIT,AMOUNT,UNITPRICE FROM dbo.FOODCONSUMED A1\n"
                    + "JOIN dbo.DISHOFFOOD A2\n"
                    + "ON A2.IDFOOD = A1.IDFOOD";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lstFood.add(new FoodConsumed(rs.getString("IDBILL"), rs.getString("IDFOOD"), rs.getString("NAMEOFDISH"), rs.getString("UNIT"), Double.parseDouble(rs.getString("AMOUNT")), Double.parseDouble(rs.getString("UNITPRICE"))));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getTotalRevenueFoodConsumedBaseDateRage(String dayStart, String monthStart, String yearStart, String dayEnd, String monthEnd, String yearEnd) {

        ArrayList<FoodConsumed> lstFoodConsumedForRevenue = new ArrayList<>();
        int dayS = Integer.parseInt(dayStart);
        int monthS = Integer.parseInt(monthStart);
        int yearS = Integer.parseInt(yearStart);
        int dayE = Integer.parseInt(dayEnd);
        int monthE = Integer.parseInt(monthEnd);
        int yearE = Integer.parseInt(yearEnd);
        int count = 0;
        double totalFoodConsed = 0.0;
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
            String sql = "SELECT SUM((CAST(AMOUNT AS FLOAT)*CAST(A2.UNITPRICE AS FLOAT))) AS TOTALFOODCONSUMED,A3.newdate FROM dbo.FOODCONSUMED A1 \n"
                    + "JOIN dbo.DISHOFFOOD A2\n"
                    + "ON A2.IDFOOD = A1.IDFOOD\n"
                    + "JOIN (SELECT IDBILL,TYPEROOM,CONCAT(SUBSTRING(DATEIN,7,4),'-',SUBSTRING(DATEIN,4,2),'-',SUBSTRING(DATEIN,1,2)) AS newdate\n"
                    + "FROM dbo.CUSTOMER cu) A3\n"
                    + "ON  A3.IDBILL = A1.IDBILL\n"
                    + "WHERE A3.newdate " + xo
                    + "GROUP BY A3.newdate;";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                totalFoodConsed += Double.parseDouble(rs.getString("TOTALFOODCONSUMED"));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalFoodConsed;

        //end
    }
}
