package com.lowleveldesign.crms.Services.Floor;

import com.lowleveldesign.crms.ErrorHandling.GenericClientException;
import com.lowleveldesign.crms.ErrorHandling.ResourceNotFoundException;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Repositories.Building.IBuildingDao;
import com.lowleveldesign.crms.Repositories.Floor.IFloorDao;
import com.lowleveldesign.crms.Services.Building.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class FloorService implements IFloorService{
    @Autowired
    private IBuildingDao buildingDao;

    @Autowired
    private IFloorDao floorDao;
    @Autowired IBuildingService buildingService;

    @Override
    public Floor addFloor(UUID buildingId, Floor floor) {
//        UUID id = UUID.randomUUID();
//        floor.setFloorId(id);

//        return buildingRepo.addFloor(buildingId, floor);
        Building building = buildingService.getBuildingById(buildingId);

        if(building != null){
            floor.setBuilding(building);
            floorDao.save(floor);

//            List<Floor> floors = building.getFloorsInBuilding();
//            floors.add(floor);
//            buildingDao.save(building);
        }
        else{
            //To: Throw an exception
            throw new GenericClientException("The Building doesn't exist where you're trying to add a Floor!");
        }

        return floor;
    }

    @Override
    public Floor getFloor(UUID floorId) {
        Optional<Floor> floor = floorDao.findById(floorId);
        return floor.orElseThrow(()-> new ResourceNotFoundException("The Floor you're trying to fetch doesn't exist!"));
    }


}
