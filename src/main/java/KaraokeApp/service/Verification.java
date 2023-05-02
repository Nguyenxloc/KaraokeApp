/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.service;

import KaraokeApp.model.User;
import KaraokeApp.repository.AcountRes;

/**
 *
 * @author 84374
 */
public class Verification {

    public Verification() {
    }    
    
    public Integer verAction(String name, String pass ){
        AcountRes acc = new AcountRes();
        acc.addTolist();
        String nameVer = acc.getLstUser().get(0).getName();
        String passVer = acc.getLstUser().get(0).getPass();
        int stt=0;
        if(name.equals(nameVer)&&pass.equals(passVer)){
            System.out.println("case 1");
            
            stt = 1;
        }
        else if(name.strip().equals("")||pass.strip().equals("")){
            if(name.strip().equals("")){
                stt =  0;//please enter username
            }
            else if(pass.strip().equals("")){
                stt = 3;//please enter username
            }    
        }
        else if(!pass.equals(passVer)&&name.equals(nameVer)){
            System.out.println("case 3");
            stt = 2;//// password not correct
        }
        else{
            System.out.println("case 2");
            System.out.println(nameVer);
            System.out.println(passVer);
            stt = -1;///acount is not valid
        }
        return stt;
    }
}
    

