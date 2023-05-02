/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.repository;

import KaraokeApp.model.Conn;
import KaraokeApp.model.TypeRoom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 84374
 */
public class TypeRoomRes {
     ArrayList<TypeRoom> lstRoomType;

    public TypeRoomRes() {
        lstRoomType = new ArrayList<>();
    }
     
     public void addIntoList(){
//        lstRoomType.add(new TypeRoom("Phong vip",1000000.0));
//        lstRoomType.add(new TypeRoom("Phong trung",700000.0));
//        lstRoomType.add(new TypeRoom("Phong thuong",500000.0));
        sqlToJava();
     }
     public ArrayList<TypeRoom> sqlToJava(){
         lstRoomType=new ArrayList<>();
         try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user ="sa";
//            String password="123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            Statement stmt =  conn.createStatement();
            String sql = "SELECT *FROM TYPEROOM";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
               lstRoomType.add(new TypeRoom(rs.getString("NAMETYPE"),Double.parseDouble(rs.getString("PRICEONHOUR"))));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstRoomType;
    }
         
     
    public ArrayList<TypeRoom> getLstRoomType() {
        return lstRoomType;
    }

    public void setLstRoomType(ArrayList<TypeRoom> lstRoomType) {
        this.lstRoomType = lstRoomType;
    }
     
}
