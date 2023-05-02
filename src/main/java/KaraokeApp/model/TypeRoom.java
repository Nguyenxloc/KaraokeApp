/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.model;

/**
 *
 * @author 84374
 */
public class TypeRoom {
    private String nameRoomType;
    private double priceOnHour;

    public TypeRoom() {
    }

    public TypeRoom(String nameRoomType, double priceOnHour) {
        this.nameRoomType = nameRoomType;
        this.priceOnHour = priceOnHour;
    }
@Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TypeRoom roomType = (TypeRoom) o;
            return  nameRoomType.equalsIgnoreCase(roomType.getNameRoomType()) &&
                    priceOnHour==(roomType.getPriceOnHour()); 
            
        }
    public String getNameRoomType() {
        return nameRoomType;
    }

    public void setNameRoomType(String nameRoomType) {
        this.nameRoomType = nameRoomType;
    }

    public double getPriceOnHour() {
        return priceOnHour;
    }

    public void setPriceOnHour(double priceOnHour) {
        this.priceOnHour = priceOnHour;
    }

    @Override
    public String toString() {
        return "TypeRoom{" + "nameRoomType=" + nameRoomType + ", priceOnHour=" + priceOnHour + '}';
    }
    
}
