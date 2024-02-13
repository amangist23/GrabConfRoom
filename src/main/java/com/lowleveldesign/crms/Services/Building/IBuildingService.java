package com.lowleveldesign.crms.Services.Building;

import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;

import java.util.List;
import java.util.UUID;

public interface IBuildingService {
    public Building addBuilding(Building building);
    public Building getBuildingById(UUID buildingId);
    public List<Building> getAllBuildings();
}
