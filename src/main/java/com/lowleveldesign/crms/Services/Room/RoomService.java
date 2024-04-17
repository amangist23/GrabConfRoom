package com.lowleveldesign.crms.Services.Room;

import com.lowleveldesign.crms.Controllers.FloorController;
import com.lowleveldesign.crms.DTOModels.SlotDto;
import com.lowleveldesign.crms.ErrorHandling.GenericClientException;
import com.lowleveldesign.crms.ErrorHandling.ResourceNotFoundException;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Repositories.Room.IRoomDao;
import com.lowleveldesign.crms.Services.Building.IBuildingService;
import com.lowleveldesign.crms.Services.Floor.IFloorService;
import com.lowleveldesign.crms.Utilities.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RoomService implements IRoomService{
    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    private IBuildingRepo buildingRepo; //Use interface here for loose coupling
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IFloorService floorService;
    @Autowired
    private IRoomDao roomDao;
    @Override
    public Room addConfRoom(UUID buildingId, UUID floorId, Room confRoom) {
//        UUID id = UUID.randomUUID();
//        confRoom.setConfroomId(id);

        Floor floor = floorService.getFloor(floorId);

        if(floor != null){
            confRoom.setFloor(floor);
            roomDao.save(confRoom);
        }
        else{
            throw new GenericClientException("The Floor doesn't exist where you're trying to add a Conference Room!");
        }
//        return buildingRepo.addConfRoom(buildingId, floorId, confRoom);
        return confRoom;
    }
    @Override
    public Room getRoomById(UUID confRoomId){
//        List<Building> buildings = buildingRepo.getAllBuildings();
//
//        for(Building building: buildings){
//            List<Floor> floors = building.getFloorsInBuilding();
//
//            for (Floor floor: floors){
//                List<Room> rooms = floor.getRoomsInFloor();
//                for(Room room: rooms){
//                    if(room.getConfroomId().compareTo(confRoomId) == 0){
//                        return room;
//                    }
//                }
//            }
//        }
        Optional<Room> room = roomDao.findById(confRoomId);

        return room.orElseThrow(()->new ResourceNotFoundException("Conference Room with the ID : "+confRoomId+" doesn't exist!"));

//        logger.error("Conference Room with the ID : "+confRoomId+" doesn't exist!");
//        throw new ResourceNotFoundException("Conference Room with the ID : "+confRoomId+" doesn't exist!");
    }

    @Override
    public List<Slot> unreserveSlot(UUID confRoomId, SlotDto bookedSlot) {
        return null;
    }

    @Override
    public List<Slot> reserveSlot(UUID confRoomId, SlotDto bookedSlot) {
        return null;
    }
}
