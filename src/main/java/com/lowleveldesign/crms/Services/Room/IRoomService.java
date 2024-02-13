package com.lowleveldesign.crms.Services.Room;

import com.lowleveldesign.crms.Models.Room;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface IRoomService {
    public Room addConfRoom(UUID buildingId, UUID floorId, Room confRoom);
    public Room getRoomById(UUID confRoomId);
}
