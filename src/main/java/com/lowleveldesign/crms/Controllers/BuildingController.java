package com.lowleveldesign.crms.Controllers;

import com.lowleveldesign.crms.ErrorHandling.ResourceNotFoundException;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Models.User;
import com.lowleveldesign.crms.Services.Building.BuildingService;
import com.lowleveldesign.crms.Services.Building.IBuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/crms/buildings")
public class BuildingController {
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @Autowired
    private IBuildingService buildingService; //Always keep these member as private
    @PostMapping("")
    public ResponseEntity<Building> addBuilding(@RequestBody Building building){
        logger.info("Incoming API POST request to addBuilding with building name parameter: {}", building.getBuildingName());
        //To Do: HTTP Response for every response e.g. 200, 4XX
        return new ResponseEntity<>(buildingService.addBuilding(building), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Building>> getAllBuildings() {
        logger.info("Incoming API GET request to getAllBuildings()");

        List<Building> buildings = buildingService.getAllBuildings();
        if(buildings.isEmpty())
            return new ResponseEntity<>(buildings, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }

    @GetMapping("/{buildingId}")
    public ResponseEntity<Building> getBuilding(@PathVariable("buildingId") UUID buildingId) {
        logger.info("Incoming API GET request to getBuilding with buildingId parameter: {}", buildingId);

        Building building = buildingService.getBuildingById(buildingId);

        if(building == null)
            throw  new ResourceNotFoundException("Building with Building Id : "+buildingId+" doesn't exist!");
        return new ResponseEntity<>(building, HttpStatus.OK);
    }
}
