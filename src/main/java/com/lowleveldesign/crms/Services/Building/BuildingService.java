package com.lowleveldesign.crms.Services.Building;

import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Repositories.Building.BuildingRepo;
import com.lowleveldesign.crms.Repositories.Building.IBuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class BuildingService implements IBuildingService{
    @Autowired
    private IBuildingRepo buildingRepo; //Use interface here for loose coupling
    @Override
    public Building addBuilding(Building building) {
        UUID id = UUID.randomUUID();
        building.setBuildingId(id);

        return buildingRepo.addBuilding(building);
    }
    @Override
    public Building getBuildingById(UUID buildingId) {
        return buildingRepo.getBuildingById(buildingId);
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepo.getAllBuildings();
    }
}
