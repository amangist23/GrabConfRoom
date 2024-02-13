package com.lowleveldesign.crms.Repositories.Booking;

import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Utilities.Slot;

import java.util.List;
import java.util.UUID;

public interface IBookingRepo {
    public Booking addBooking(Booking booking);
    public Booking cancelBooking(Booking booking);
    public List<Booking> getBookingsByUserId(UUID userId);
    public Booking getBookingById(UUID bookingId);

    public Booking getBookingByConfRoomId(UUID confRoomId);
}
