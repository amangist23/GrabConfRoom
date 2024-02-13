package com.lowleveldesign.crms.Models;
import com.lowleveldesign.crms.Utilities.Slot;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Room {
    private UUID confroomId;
    private int totalCapacity;
    private List<Slot> occupiedSlotsForRoom;
    private Map<Date, List<Slot>> slotMp;
    public UUID getConfroomId(){
        return confroomId;
    }
    public void setConfroomId(UUID id){
        confroomId = id;
    }
    public int getTotalCapacity(){
        return totalCapacity;
    }
    public void setTotalCapacity(int count){
        totalCapacity = count;
    }
    public List<Slot> getOccupiedSlotsForRoom(){
        return occupiedSlotsForRoom;
    }
    public void setOccupiedSlotsForRoom(List<Slot> slots){
        occupiedSlotsForRoom = slots;
    }
}
