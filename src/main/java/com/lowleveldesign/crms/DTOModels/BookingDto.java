package com.lowleveldesign.crms.DTOModels;

import com.lowleveldesign.crms.Utilities.BookingStatus;
import com.lowleveldesign.crms.Utilities.Slot;

import java.sql.Date;
import java.util.UUID;

public class BookingDto {
    private UUID bookingId;
    private UUID userId;
    private UUID confRoomId;
    private SlotDto bookedSlot;
    private BookingStatus status;

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
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

    public SlotDto getBookedSlot() {
        return bookedSlot;
    }

    public void setBookedSlot(SlotDto bookedSlot) {
        this.bookedSlot = bookedSlot;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
