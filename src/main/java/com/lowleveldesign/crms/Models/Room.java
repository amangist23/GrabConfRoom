package com.lowleveldesign.crms.Models;
import com.lowleveldesign.crms.Utilities.Slot;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "confroom_id")
    private UUID confroomId;
    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
    private int totalCapacity;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Slot> occupiedSlotsForRoom;
//    private Map<Date, List<Slot>> slotMp;
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
    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
