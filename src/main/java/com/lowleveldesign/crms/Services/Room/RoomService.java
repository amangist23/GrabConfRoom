package com.lowleveldesign.crms.Services.Room;

import com.lowleveldesign.crms.Controllers.FloorController;
import com.lowleveldesign.crms.ErrorHandling.GenericClientException;
import com.lowleveldesign.crms.ErrorHandling.ResourceNotFoundException;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Repositories.Building.BuildingRepo;
import com.lowleveldesign.crms.Repositories.Building.IBuildingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class RoomService implements IRoomService{
    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    private IBuildingRepo buildingRepo; //Use interface here for loose coupling
    @Override
    public Room addConfRoom(UUID buildingId, UUID floorId, Room confRoom) {
        UUID id = UUID.randomUUID();
        confRoom.setConfroomId(id);

        return buildingRepo.addConfRoom(buildingId, floorId, confRoom);
    }
    @Override
    public Room getRoomById(UUID confRoomId){
        List<Building> buildings = buildingRepo.getAllBuildings();

        for(Building building: buildings){
            List<Floor> floors = building.getFloorsInBuilding();

            for (Floor floor: floors){
                List<Room> rooms = floor.getRoomsInFloor();
                for(Room room: rooms){
                    if(room.getConfroomId().compareTo(confRoomId) == 0){
                        return room;
                    }
                }
            }
        }
        logger.error("Conference Room with the ID : "+confRoomId+" doesn't exist!");
        throw new ResourceNotFoundException("Conference Room with the ID : "+confRoomId+" doesn't exist!");
    }
}
