package com.lowleveldesign.crms.Services.Floor;

import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;

import java.util.UUID;

public interface IFloorService {
    public Floor addFloor(UUID buildingId, Floor floor);
}
