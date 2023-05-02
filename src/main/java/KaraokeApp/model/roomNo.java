/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KaraokeApp.model;

/**
 *
 * @author 84374
 */
public class roomNo {
    private  String nameRoom;
    TypeRoom typeRoom;

    public roomNo() {
    }

    public roomNo(String nameRoom, TypeRoom typeRoom) {
        this.nameRoom = nameRoom;
        this.typeRoom = typeRoom;
    }
@Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            roomNo roomNo = (roomNo) o;
            return  nameRoom.equalsIgnoreCase(roomNo.getNameRoom()) &&
                    typeRoom.equals(roomNo.getTypeRoom()); 
        }
    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }

    @Override
    public String toString() {
        return "roomNo{" + "nameRoom=" + nameRoom + ", typeRoom=" + typeRoom + '}';
    }
    
}
