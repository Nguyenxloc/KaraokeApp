/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.repository;

import KaraokeApp.model.Conn;
import KaraokeApp.model.TypeRoom;
import KaraokeApp.model.roomNo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 84374
 */
public class roomRes {

    ArrayList<roomNo> lstRoom;

    public roomRes() {
        lstRoom = new ArrayList<>();
    }

    public void addToList() {
        TypeRoomRes roomTypeList = new TypeRoomRes();
        roomTypeList.addIntoList();
        ArrayList<TypeRoom> lstTypeRoom = new ArrayList<>();
        lstTypeRoom = roomTypeList.getLstRoomType();
        try {
//            String url = "jdbc:sqlserver://DESKTOP-OOEDQDP\\SQLEXPRESS:1433;databaseName=KARAOKEDATA;encrypt=false";
//            String user = "sa";
//            String password = "123456";
            Connection conn = DriverManager.getConnection(Conn.getInstance().url);
            Statement stmt = conn.createStatement();
            TypeRoom type = new TypeRoom();
            String sql = "SELECT*FROM dbo.ROOMNO \n"
                    + "ORDER BY NUMORDER;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                for (TypeRoom typeOb : lstTypeRoom) {
                    if (rs.getString("TYPEROOM").equals(typeOb.getNameRoomType())) {
                        type = typeOb;
                        break;
                    }
                }
                lstRoom.add(new roomNo(rs.getString("ROOMNO"), type));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToListClone() {

    }

    public ArrayList<roomNo> getLstRoom() {

        return lstRoom;
    }

    public void setLstRoom(ArrayList<roomNo> lstRoom) {
        this.lstRoom = lstRoom;
    }

}
