/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.service;

import KaraokeApp.model.TypeRoom;
import KaraokeApp.model.roomNo;
import KaraokeApp.repository.CustomerRes;
import KaraokeApp.repository.foodConsumedRes;
import java.util.ArrayList;

/**
 *
 * @author 84374
 */
public class OwnerService {

    public OwnerService() {
    }

    public double getTotalRevenue(String dayStart, String monthStart, String yearStart, String dayEnd, String monthEnd, String yearEnd,ArrayList<roomNo>lstRoomRam,ArrayList<TypeRoom>lstTypeRoomRam) {
        double totalRevenueRoom = CustomerRes.getTotalRevenueRoomBaseDateRange(dayStart,monthStart,yearStart,dayEnd,monthEnd,yearEnd,lstRoomRam,lstTypeRoomRam);
        double totalRevenueFood = foodConsumedRes.getTotalRevenueFoodConsumedBaseDateRage(dayStart,monthStart,yearStart,dayEnd,monthEnd,yearEnd);
        double totalRevenue = totalRevenueFood+totalRevenueRoom;
        return  totalRevenue;
    }
    
}
