package com.lowleveldesign.crms.Repositories.Booking;

import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Repositories.Building.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Component
public class BookingRepo implements IBookingRepo{
    private Map<UUID, Booking> bookingsMap;
    @Autowired
    private BuildingRepo buildingRepo;

    private BookingRepo(){
        this.bookingsMap = new HashMap<>();
    }
    @Override
    public Booking addBooking(Booking booking) {
        if(!bookingsMap.isEmpty() && bookingsMap.containsKey(booking.getBookingId()))
            return null;

        //To Do: @Transactional to handle atomicity
        bookingsMap.put(booking.getBookingId(), booking);

        //Add these bookings to the conference room repo in the list of bookings
        buildingRepo.markBookingSlotAsOccupied(booking.getConfRoomId(), booking.getBookedSlot());

        return booking;
    }

    @Override
    public Booking cancelBooking(Booking booking) {
        bookingsMap.put(booking.getBookingId(), booking);

        //This should be done inside Repo or service class????????
        //TO Do: Remove these bookings from the conference room repo in the list of bookings
        buildingRepo.markBookingSlotAsAvailable(booking.getConfRoomId(), booking.getBookedSlot());
        return booking;
    }
    @Override
    public List<Booking> getBookingsByUserId(UUID userId) {
        return null;
    }

    @Override
    public Booking getBookingById(UUID bookingId) {
        return bookingsMap.get(bookingId);
    }

    @Override
    public Booking getBookingByConfRoomId(UUID confRoomId) {
        return null;
    }

}
