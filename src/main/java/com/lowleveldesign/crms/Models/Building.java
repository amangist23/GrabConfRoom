package com.lowleveldesign.crms.Models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;


@Entity
@Table
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "building_id")
    private UUID buildingId;
//    @OneToMany(mappedBy = "building")
//    private List<Floor> floorsInBuilding; //One to Many
    @Column(name = "building_name")
    private String buildingName;


    public UUID getBuildingId(){
        return buildingId;
    }
    public String getBuildingName(){
        return buildingName;
    }
//    public List<Floor> getFloorsInBuilding(){
//        return floorsInBuilding;
//    }
    public void setBuildingId(UUID id){
        buildingId = id;
    }
    public void setBuildingName(String name){
        buildingName = name;
    }
//    public void setFloorsInBuilding(List<Floor> floorList){
//        floorsInBuilding = floorList;
//    }
}
