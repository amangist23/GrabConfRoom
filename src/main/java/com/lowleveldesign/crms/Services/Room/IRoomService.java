package com.lowleveldesign.crms.Services.Room;

import com.lowleveldesign.crms.DTOModels.SlotDto;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Utilities.Slot;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface IRoomService {
    public Room addConfRoom(UUID buildingId, UUID floorId, Room confRoom);
    public Room getRoomById(UUID confRoomId);
    public List<Slot> unreserveSlot(UUID confRoomId, SlotDto bookedSlot);
    public List<Slot> reserveSlot(UUID confRoomId, SlotDto bookedSlot);
}
