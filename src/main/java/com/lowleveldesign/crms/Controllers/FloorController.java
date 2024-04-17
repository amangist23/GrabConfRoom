package com.lowleveldesign.crms.Controllers;

import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Services.Building.BuildingService;
import com.lowleveldesign.crms.Services.Floor.FloorService;
import com.lowleveldesign.crms.Services.Floor.IFloorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/crms/buildings/{buildingId}/floors")
public class FloorController {
    @Autowired
    private IFloorService floorService; //Always keep these member as private
    @PostMapping("") //Post API should be 201
    public ResponseEntity<Floor> addFloor(
            @PathVariable("buildingId") UUID buildingId,
            @RequestBody Floor floor){

        //To Do: HTTP Response for every response e.g. 200, 4XX
        return new ResponseEntity<>(floorService.addFloor(buildingId, floor), HttpStatus.CREATED);
    }
}
