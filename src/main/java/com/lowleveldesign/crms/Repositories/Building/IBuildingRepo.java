package com.lowleveldesign.crms.Repositories.Building;

import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Utilities.Slot;

import java.util.List;
import java.util.UUID;

public interface IBuildingRepo {
    public Building addBuilding(Building building);
    public Floor addFloor(UUID buildingId, Floor floor); //????????
    public Room addConfRoom(UUID buildingId, UUID floorId, Room confRoom); //????????
    public Building getBuildingById(UUID buildingId);
    public List<Building> getAllBuildings();
    public Room markBookingSlotAsOccupied(UUID confRoomId, Slot occupiedSlots);
    public Room markBookingSlotAsAvailable(UUID confRoomId, Slot occupiedSlots);
}
