package com.lowleveldesign.crms.Services.Booking;

import com.lowleveldesign.crms.DTOModels.BookingDto;
import com.lowleveldesign.crms.DTOModels.SlotDto;
import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Utilities.Slot;

import java.awt.print.Book;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface IBookingService {
    public BookingDto addBooking(BookingDto bookingDto);
    public BookingDto cancelBooking(BookingDto booking);
    public List<Booking> getBookingsByUser(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId);
    public List<BookingDto> getAllBookings(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId);
    public BookingDto getBookingById(UUID bookingId);
    public boolean isSlotAvailable(UUID confRoomId, SlotDto bookedSlot);
}
