package com.lowleveldesign.crms.Models;

import com.lowleveldesign.crms.Utilities.BookingStatus;
import com.lowleveldesign.crms.Utilities.Slot;

import java.util.UUID;

public class Booking {
    private UUID bookingId;
    private UUID userId;
    private UUID confRoomId;
    private Slot bookedSlot;
    private BookingStatus status;
    //booking Status enum//

    public UUID getBookingId(){
        return bookingId;
    }
    public void setBookingId(UUID id){
        bookingId = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public UUID getConfRoomId() {
        return confRoomId;
    }

    public void setConfRoomId(UUID confRoomId) {
        this.confRoomId = confRoomId;
    }

    public Slot getBookedSlot() {
        return bookedSlot;
    }

    public void setBookedSlot(Slot bookedSlot) {
        this.bookedSlot = bookedSlot;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
