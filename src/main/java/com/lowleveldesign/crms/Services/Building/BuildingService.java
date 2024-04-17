package com.lowleveldesign.crms.Services.Building;

import com.lowleveldesign.crms.ErrorHandling.ResourceNotFoundException;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Repositories.Building.IBuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class BuildingService implements IBuildingService{
    @Autowired
    private IBuildingRepo buildingRepo; //Use interface here for loose coupling

    @Autowired
    private IBuildingDao buildingDao;

    @Override
    public Building addBuilding(Building building) {
//        UUID id = UUID.randomUUID();
//        building.setBuildingId(id);
//
//        return buildingRepo.addBuilding(building);
        return buildingDao.save(building);
    }
    @Override
    public Building getBuildingById(UUID buildingId) {
//        return buildingRepo.getBuildingById(buildingId);
        Optional<Building> building = buildingDao.findById(buildingId);
        return building.orElseThrow(() -> new ResourceNotFoundException("The Building you're trying to fetch doesn't exist!"));
    }

    @Override
    public List<Building> getAllBuildings() {
//        return buildingRepo.getAllBuildings();
        return buildingDao.findAll();
    }
}
