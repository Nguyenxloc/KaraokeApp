/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.model;

/**
 *
 * @author 84374
 */
public class FoodConsumed {
    private String idBill;
    private double amount;
    private String idFood;
    private String nameFood;
    private String unit;
    private double priceFood;

    public FoodConsumed() {
    }

    public FoodConsumed(String idBill, String idFood, String nameFood, String unit, double amount, double priceFood) {
        this.idBill = idBill;
        this.idFood = idFood;
        this.nameFood = nameFood;
        this.unit = unit;
        this.amount = amount;
        this.priceFood = priceFood;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(double priceFood) {
        this.priceFood = priceFood;
    }

    @Override
    public String toString() {
        return "FoodNuintP{" + "idBill=" + idBill + ", idFood=" + idFood + ", nameFood=" + nameFood + ", unit=" + unit + ", amount=" + amount + ", priceFood=" + priceFood + '}';
    }
}
