/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.repository;

import KaraokeApp.model.User;
import java.util.ArrayList;

/**
 *
 * @author 84374
 */
public class AcountRes {
    ArrayList<User> lstUser = new ArrayList<>();
    public AcountRes() {    
    }
    
    public void  addTolist(){
        lstUser.add(new User("admin","123456"));
    }
    
    public ArrayList<User> getLstUser() {
        return lstUser;
    }

    public void setLstUser(ArrayList<User> lstUser) {
        this.lstUser = lstUser;
    }
    
}
