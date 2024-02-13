package com.lowleveldesign.crms.Models;

import java.util.List;
import java.util.UUID;

public class Floor {
    private UUID floorId;
    private List<Room> roomsInFloor;
    private int totalConfrooms;    //Additional fields or properties

    public UUID getFloorId(){
        return floorId;
    }
    public void setFloorId(UUID id){
        floorId = id;
    }
    public List<Room> getRoomsInFloor(){
        return roomsInFloor;
    }
    public void setRoomsInFloor(List<Room> rooms){
        roomsInFloor = rooms;
    }
}
