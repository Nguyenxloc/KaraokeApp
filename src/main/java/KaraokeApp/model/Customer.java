/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.model;

/**
 *
 * @author 84374
 */
public class Customer {
    private  String name;
    private String idBill;
    private String DOB;
    private String gender;
    private TypeRoom roomType;
    private roomNo roomNo;
    private String hourIn;
    private String minuteIn;
    private String hourOut;
    private String minuteOut;
    private String stt;
    private String date;
    private String moneyTook;

    public Customer() {
    }

    public Customer(String name, String idBill, String DOB, String gender, TypeRoom roomType, roomNo roomNo, String hourIn, String minuteIn, String hourOut, String minuteOut, String stt, String date, String moneyTook) {
        this.name = name;
        this.idBill = idBill;
        this.DOB = DOB;
        this.gender = gender;
        this.roomType = roomType;
        this.roomNo = roomNo;
        this.hourIn = hourIn;
        this.minuteIn = minuteIn;
        this.hourOut = hourOut;
        this.minuteOut = minuteOut;
        this.stt = stt;
        this.date = date;
        this.moneyTook = moneyTook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public TypeRoom getRoomType() {
        return roomType;
    }

    public void setRoomType(TypeRoom roomType) {
        this.roomType = roomType;
    }

    public roomNo getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(roomNo roomNo) {
        this.roomNo = roomNo;
    }

    public String getHourIn() {
        return hourIn;
    }

    public void setHourIn(String hourIn) {
        this.hourIn = hourIn;
    }

    public String getMinuteIn() {
        return minuteIn;
    }

    public void setMinuteIn(String minuteIn) {
        this.minuteIn = minuteIn;
    }

    public String getHourOut() {
        return hourOut;
    }

    public void setHourOut(String hourOut) {
        this.hourOut = hourOut;
    }

    public String getMinuteOut() {
        return minuteOut;
    }

    public void setMinuteOut(String minuteOut) {
        this.minuteOut = minuteOut;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoneyTook() {
        return moneyTook;
    }

    public void setMoneyTook(String moneyTook) {
        this.moneyTook = moneyTook;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", idBill=" + idBill + ", DOB=" + DOB + ", gender=" + gender + ", roomType=" + roomType + ", roomNo=" + roomNo + ", hourIn=" + hourIn + ", minuteIn=" + minuteIn + ", hourOut=" + hourOut + ", minuteOut=" + minuteOut + ", stt=" + stt + ", date=" + date + ", moneyTook=" + moneyTook + '}';
    }

}
