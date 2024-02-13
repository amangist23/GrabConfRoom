package com.lowleveldesign.crms.Services.Floor;

import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Repositories.Building.BuildingRepo;
import com.lowleveldesign.crms.Repositories.Building.IBuildingRepo;
import com.lowleveldesign.crms.Services.Building.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class FloorService implements IFloorService{
    @Autowired
    private IBuildingRepo buildingRepo; //Use interface here for loose coupling

    @Override
    public Floor addFloor(UUID buildingId, Floor floor) {
        UUID id = UUID.randomUUID();
        floor.setFloorId(id);

        return buildingRepo.addFloor(buildingId, floor);
    }
}
