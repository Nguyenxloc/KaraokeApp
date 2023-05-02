/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.model;

/**
 *
 * @author 84374
 */
public class dishOfFood {
    private String idFood;
    private String nameFood;
    private String unit;
    private double priceFood;

    public dishOfFood() {
    }

    public dishOfFood(String idFood, String nameFood, String unit, double priceFood) {
        this.idFood = idFood;
        this.nameFood = nameFood;
        this.unit = unit;
        this.priceFood = priceFood;
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

    public double getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(double priceFood) {
        this.priceFood = priceFood;
    }

    @Override
    public String toString() {
        return "dishOfFood{" + "idFood=" + idFood + ", nameFood=" + nameFood + ", unit=" + unit + ", priceFood=" + priceFood + '}';
    }
    
    
}
