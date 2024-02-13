package com.lowleveldesign.crms.Services.Booking;

import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Utilities.Slot;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

public interface IBookingService {
    public Booking addBooking(Booking booking);
    public Booking cancelBooking(Booking booking);
    public List<Booking> getBookingsByUser(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId);
    public List<Booking> getAllBookings(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId);
    public Booking getBookingById(UUID bookingId);
    public boolean isSlotAvailable(UUID confRoomId, Slot slot);
}
