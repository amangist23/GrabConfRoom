package com.lowleveldesign.crms.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "floor_id")
    private UUID floorId;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public UUID getFloorId(){
        return floorId;
    }
    public void setFloorId(UUID id){
        floorId = id;
    }
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
