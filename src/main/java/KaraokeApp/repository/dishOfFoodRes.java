/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.repository;

import KaraokeApp.model.Conn;
import KaraokeApp.model.dishOfFood;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author 84374
 */
public class dishOfFoodRes {
        ArrayList<dishOfFood> lstDish;

    public dishOfFoodRes() {
        lstDish = new ArrayList<>();
        
    }
    public void addDataToLst(){
          getDishFromSql();
//        lstDish.add(new dishOfFood("f001","Snack","Pack",20000.0));
//        lstDish.add(new dishOfFood("f002","Fruit","Dish",40000.0));
//        lstDish.add(new dishOfFood("f003","Beer","Bottle",30000.0));
//        lstDish.add(new dishOfFood("f004","Coca","Bottle",25000.0));
    }

    public ArrayList<dishOfFood> getLstDish() {
        return lstDish;
    }

    public void setLstDish(ArrayList<dishOfFood> lstDish) {
        this.lstDish = lstDish;
    }
    public void getDishFromSql(){
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            Statement stmt = conn.createStatement();
            StringBuilder x = new StringBuilder();
            String sql ="SELECT*FROM dbo.DISHOFFOOD";
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                lstDish.add(new dishOfFood(rs.getString("IDFOOD"),rs.getString("NAMEOFDISH"),rs.getString("UNIT"),Double.parseDouble(rs.getString("UNITPRICE"))));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
