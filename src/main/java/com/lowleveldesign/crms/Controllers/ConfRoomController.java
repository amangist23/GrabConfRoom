package com.lowleveldesign.crms.Controllers;

import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Services.Room.IRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/crms/buildings/{buildingId}/floors/{floorId}")
public class ConfRoomController {
    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    IRoomService roomService;
    @PostMapping("/rooms")
    public ResponseEntity<Room> addConfRoom(
            @PathVariable("buildingId") UUID buildingId,
            @PathVariable("floorId") UUID floorId,
            @RequestBody Room room){
        logger.info("Incoming API POST request to addConfRoom with buildingId parameter: {} and floorId parameter: {}", buildingId, floorId);

        //To Do: HTTP Response for every response e.g. 200, 4XX
        return new ResponseEntity<>(roomService.addConfRoom(buildingId, floorId, room), HttpStatus.CREATED);
    }
}
