package com.lowleveldesign.crms.Repositories.Building;

import com.lowleveldesign.crms.ErrorHandling.GenericClientException;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Utilities.Slot;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class BuildingRepo implements IBuildingRepo{
    private Map<UUID, Building> buildingMap;

    private BuildingRepo( ) {
        this.buildingMap = new HashMap<>();
    }

    @Override
    public Building addBuilding(Building building) {
        buildingMap.put(building.getBuildingId(), building);
        return building;
    }

    @Override
    public Floor addFloor(UUID buildingId, Floor floor) {
        Building building = buildingMap.get(buildingId);

        if(building == null)
            throw new GenericClientException("You can't add any floor to the Building that doesn't exist.");

        List<Floor> floors = building.getFloorsInBuilding();
        floors.add(floor);
        return floor;
    }

    @Override
    public Room addConfRoom(UUID buildingId, UUID floorId, Room confRoom) {
        Building building = buildingMap.get(buildingId);

        if(building == null)
            throw new GenericClientException("You can't add any conference to the Building that doesn't exist!");

        List<Floor> floors = building.getFloorsInBuilding();

        if(floors.size() == 0)
            throw new GenericClientException("You can't add any conference to the Building that doesn't have any Floor!");

        //Iterate through floors and find the floor with the given id;
        for(Floor floor: floors){
            //compareTo method compares the UUID with the given UUID and returns -1,0,1, if it is
            //less than, equal to or greater than the given UUID.
            if(floor.getFloorId().compareTo(floorId) == 0){
                //TO DO:convert UUIDS to string and then compare
                List<Room> rooms = floor.getRoomsInFloor();
                rooms.add(confRoom);
            }
            else if(floors.indexOf(floor) == floors.size()-1){
                throw new GenericClientException("You can't add any conference to the Floor that doesn't exist!");
            }
        }
        return confRoom;
    }

    @Override
    public Building getBuildingById(UUID buildingId) {
        Building clonedBuilding = new Building();
        Building building = buildingMap.get(buildingId);

        if(building == null)
            return null;

        clonedBuilding.setBuildingId(building.getBuildingId());
        clonedBuilding.setBuildingName(building.getBuildingName());
        //To Do - proper deep cloning way for floors???
        clonedBuilding.setFloorsInBuilding(new ArrayList<>(building.getFloorsInBuilding()));

        return clonedBuilding;
    }

    @Override
    public List<Building> getAllBuildings() {
        List<Building> clonedBuildings = new ArrayList<Building>();

        for(UUID buildingId: buildingMap.keySet()){
            Building clonedBuilding = new Building();
            Building building = buildingMap.get(buildingId);

            clonedBuilding.setBuildingId(building.getBuildingId());
            clonedBuilding.setBuildingName(building.getBuildingName());
            clonedBuilding.setFloorsInBuilding(building.getFloorsInBuilding());

            clonedBuildings.add(clonedBuilding);
        }
        return clonedBuildings;
    }
    @Override
    public Room markBookingSlotAsOccupied(UUID confRoomId, Slot occupiedSlots){
        //Deep Cloning Room
        Room clonedRoom = new Room();

        //Searching for the desired conference room
        for(UUID buildingId: buildingMap.keySet()){
            Building building = buildingMap.get(buildingId);

            List<Floor> floors = building.getFloorsInBuilding();
            for(Floor floor: floors){
                List<Room> confRooms = floor.getRoomsInFloor();

                for(Room room: confRooms){
                    if(room.getConfroomId().equals(confRoomId)){
                        List<Slot> slots = room.getOccupiedSlotsForRoom();
                        slots.add(occupiedSlots);
                        room.setOccupiedSlotsForRoom(slots);

                        //Deep Cloning Room
                        clonedRoom.setConfroomId(room.getConfroomId());
                        clonedRoom.setTotalCapacity(room.getTotalCapacity());
                        clonedRoom.setOccupiedSlotsForRoom(room.getOccupiedSlotsForRoom());
                        return clonedRoom;
                    }
                }
            }
        }
        return clonedRoom;
    }
    @Override
    public Room markBookingSlotAsAvailable(UUID confRoomId, Slot occupiedSlots){
        //Deep Cloning Room
        Room clonedRoom = new Room();

        //Searching for the desired conference room
        for(UUID buildingId: buildingMap.keySet()){
            Building building = buildingMap.get(buildingId);

            List<Floor> floors = building.getFloorsInBuilding();
            for(Floor floor: floors){
                List<Room> confRooms = floor.getRoomsInFloor();

                for(Room room: confRooms){
                    if(room.getConfroomId().equals(confRoomId)){
                        List<Slot> slots = room.getOccupiedSlotsForRoom();
                        slots.remove(occupiedSlots);
                        room.setOccupiedSlotsForRoom(slots);

                        //Deep Cloning Room
                        clonedRoom.setConfroomId(room.getConfroomId());
                        clonedRoom.setTotalCapacity(room.getTotalCapacity());
                        clonedRoom.setOccupiedSlotsForRoom(room.getOccupiedSlotsForRoom());
                        return clonedRoom;
                    }
                }
            }
        }
        return clonedRoom;
    }
}
