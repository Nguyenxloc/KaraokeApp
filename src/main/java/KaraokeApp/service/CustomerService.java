/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.service;

import KaraokeApp.model.Customer;
import KaraokeApp.model.FoodConsumed;
import KaraokeApp.model.TypeRoom;
import KaraokeApp.model.dishOfFood;
import KaraokeApp.model.roomNo;
import KaraokeApp.repository.CustomerRes;
import KaraokeApp.repository.TypeRoomRes;
import KaraokeApp.repository.dishOfFoodRes;
import KaraokeApp.repository.foodConsumedRes;
import KaraokeApp.repository.roomRes;
import java.util.ArrayList;

/**
 *
 * @author 84374
 */
public class CustomerService {
    
    public CustomerService() {
    }
    /////////get data from sql/////////////
    public ArrayList<Customer> getLstCustomer(){
        CustomerRes customerResOb = new CustomerRes();
        customerResOb.addToList();
        ArrayList cusTomerDataForView= customerResOb.getLstCustomer();
        return cusTomerDataForView;
    }
    
    public ArrayList<FoodConsumed> getLstFood(){
        foodConsumedRes foodConsumedOb = new foodConsumedRes();
        foodConsumedOb.addToList();
        ArrayList foodDataForView  = foodConsumedOb.getLstFood();
        return foodDataForView;
    }
    
    public ArrayList<TypeRoom> getLstRoomType(){
         TypeRoomRes TypeRoomResOb = new TypeRoomRes();
         TypeRoomResOb.addIntoList();
         ArrayList roomTypeDataForView = TypeRoomResOb.getLstRoomType();
         return roomTypeDataForView;
    }
    public ArrayList<roomNo> getLstRoomNo(){
         roomRes  roomNoDataOb = new roomRes();
         roomNoDataOb.addToList();
         ArrayList roomNoDataForView  = roomNoDataOb.getLstRoom();
         return roomNoDataForView;
    }
    public ArrayList<dishOfFood> getLstDishOfFood(){
         dishOfFoodRes dishOfFoodOb = new dishOfFoodRes();
         dishOfFoodOb.addDataToLst();
         ArrayList dishDataForView = dishOfFoodOb.getLstDish();
         return dishDataForView;
    }
    public ArrayList<FoodConsumed> getLstFoodConsumed(){
        foodConsumedRes foodConsumedResOb =  new foodConsumedRes();
        foodConsumedResOb.addToList();
        ArrayList foodConsumedForView = foodConsumedResOb.getLstFood();
        return foodConsumedForView;
    }
    /////////Insert into sql/////////////

    public Customer addDataToDb(Customer cus, ArrayList<TypeRoom> lstRoomType, ArrayList<roomNo> lstRoomNoFull) {
    Customer cusOfService = CustomerRes.pushCusToSql(cus,lstRoomType,lstRoomNoFull);
    return cusOfService;
    }

    public ArrayList<Customer> getCusByDate(String day, String month, String year,ArrayList<TypeRoom> lstRoomTypeRam,ArrayList<roomNo> lstRoomNoRam) {
       return CustomerRes.getDataByDate(day,month,year,lstRoomTypeRam,lstRoomNoRam);
    }

    public void AlterSql(String idBill,String hourNowBill, String minuteNowBill, String moneyTook) {
        CustomerRes.alterDB(idBill,hourNowBill,minuteNowBill, moneyTook);
        
    }

    public void addFoodConsumedToSql(ArrayList<FoodConsumed> lstForReady) {
       foodConsumedRes.addFoodConsumedToSql(lstForReady);
    }


    public void updateCusToSql(String idBillPara,Customer customer, ArrayList<TypeRoom> lstRoomType, ArrayList<roomNo> lstRoomNoFull) {
       CustomerRes cusResAtService = new CustomerRes();
        cusResAtService.updateCusToSql(idBillPara,customer,lstRoomType,lstRoomNoFull);
    }

    
}
