package com.lowleveldesign.crms.Services.Booking;

import com.lowleveldesign.crms.Controllers.FloorController;
import com.lowleveldesign.crms.ErrorHandling.GenericClientException;
import com.lowleveldesign.crms.ErrorHandling.ResourceNotFoundException;
import com.lowleveldesign.crms.ErrorHandling.UserNotFoundException;
import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Repositories.Booking.BookingRepo;
import com.lowleveldesign.crms.Repositories.Booking.IBookingRepo;
import com.lowleveldesign.crms.Repositories.Building.BuildingRepo;
import com.lowleveldesign.crms.Repositories.Building.IBuildingRepo;
import com.lowleveldesign.crms.Services.Room.IRoomService;
import com.lowleveldesign.crms.Services.User.IUserService;
import com.lowleveldesign.crms.Utilities.BookingStatus;
import com.lowleveldesign.crms.Utilities.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class BookingService implements IBookingService{
    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    private IBookingRepo bookingRepo;
    @Autowired
    private IBuildingRepo buildingRepo;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IUserService userService;

    @Override
    public Booking addBooking(Booking booking) {

        try {
           //check whether the User exists or not
           // check whether the conference room exists and slot is available.
           // check whether the slot is available. and Slot is valid also (slot can't be null and time should not be in past)
           //booked slot update it inside building repo
           if((userService.getUserById(booking.getUserId()) != null) &&
                   (roomService.getRoomById(booking.getConfRoomId()) != null) &&
                   (isSlotValid(booking.getBookedSlot())) && (isSlotAvailable(booking.getConfRoomId(), booking.getBookedSlot()))){
               UUID id = UUID.randomUUID();
               booking.setBookingId(id);
               booking.setStatus(BookingStatus.ACTIVE);

               bookingRepo.addBooking(booking);
               return booking;
           }
        }catch (UserNotFoundException ex){
            logger.error("User with userId: {} cannot make a booking as this user is not a registered User!", booking.getUserId());
            throw new GenericClientException("You cannot make a booking as you are not a registered User!");
        }
        catch (ResourceNotFoundException ex){
            logger.error("User with userId: {} cannot make a booking as the conference room with confRoomId:{} doesn't exist!", booking.getUserId(), booking.getConfRoomId());
            throw new GenericClientException("You cannot make a booking to a Conference Room that doesn't exist!");
        }

        return null;
    }

    @Override
    public Booking cancelBooking(Booking booking) {
        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepo.cancelBooking(booking);
    }
    @Override
    public Booking getBookingById(UUID bookingId){
        return bookingRepo.getBookingById(bookingId);
    }

    private boolean isSlotValid(Slot slot){
        //To: Check both the slots shouldn't be null and should be valid date-time format, past-time should be invalid
        if(slot.getStartOfSlot() == null || slot.getEndOfSlot() == null){
            logger.error("Start or End slots can't be Null !");
            throw new GenericClientException("Start or End slots can't be Null !");
        }

        if(!(slot.getStartOfSlot().before(slot.getEndOfSlot()))){
            logger.error("Start slot can't be greater than End slot !");
            throw new GenericClientException("Start slot can't be greater than End slot !");
        }

        return true;
    }
    @Override
    public boolean isSlotAvailable(UUID confRoomId, Slot slot){
        Room confRoom = roomService.getRoomById(confRoomId);

        if(confRoom != null){
            List<Slot> occupiedSlots = confRoom.getOccupiedSlotsForRoom();
            //TO Do - If the Slot is present in occupied slots or not////
        }
        return true;
    }
    @Override
    public List<Booking> getAllBookings(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId) {
        List<Booking> bookings = null;

        //Get bookings for particular user
        if(userId != null)
            bookings = getBookingsByUser(userId, buildingId, floorId, confRoomId);

        //Get all the bookings for particular conference room
        if(userId == null && buildingId == null && floorId == null && confRoomId != null)
            bookings = getBookingsByConfRoom(confRoomId);
        return bookings;
    }

    private List<Booking> getBookingsById(UUID bookingId) {
        List<Booking> bookings = new ArrayList<Booking>();
        bookings.add(bookingRepo.getBookingById(bookingId));
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByUser(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId) {
        List<Booking> bookings = null;

        if(buildingId == null && floorId == null && confRoomId == null)
            return bookingRepo.getBookingsByUserId(userId);
        else if(buildingId != null && floorId == null && confRoomId == null)
            return getBookingsByUserNBuilding(userId, buildingId);
        else if(buildingId != null && floorId != null && confRoomId == null)
            return getBookingsByUserNFloor(userId, buildingId, floorId);
        else if(buildingId != null && floorId != null && confRoomId != null){

        }
        else if(buildingId == null && floorId == null && confRoomId != null){

        }

        return null;
    }

    private List<Booking> getBookingsByUserNBuilding(UUID userId, UUID buildingId){
        Building buildings = buildingRepo.getBuildingById(buildingId);
        List<Floor> floors = buildings.getFloorsInBuilding();

        List<UUID> confRoomIds = new ArrayList<UUID>();

        for (Floor floor: floors){
            floor.getRoomsInFloor().forEach(room -> {
                confRoomIds.add(room.getConfroomId());
            });
        }

        List<Booking> bookings = bookingRepo.getBookingsByUserId(userId);

        List<Booking> filteredBookings =  bookings.stream()
                .filter(booking -> confRoomIds.contains(booking.getConfRoomId()))
                .toList();
        return filteredBookings;
    }

    private List<Booking> getBookingsByUserNFloor(UUID userId, UUID buildingId, UUID floorId){
        Building buildings = buildingRepo.getBuildingById(buildingId);
        List<Floor> floors = buildings.getFloorsInBuilding();

        List<UUID> confRoomIds = new ArrayList<UUID>();

        for (Floor floor: floors){
            if(floor.getFloorId() == floorId){
                floor.getRoomsInFloor().forEach(room -> {
                    confRoomIds.add(room.getConfroomId());
                });
                break;
            }
        }

        List<Booking> bookings = bookingRepo.getBookingsByUserId(userId);

        List<Booking> filteredBookings =  bookings.stream()
                .filter(booking -> confRoomIds.contains(booking.getConfRoomId()))
                .toList();
        return filteredBookings;
    }
    private List<Booking> getBookingsByConfRoom(UUID confRoomId) {
        List<Booking> bookings = null;
        bookings.add(bookingRepo.getBookingByConfRoomId(confRoomId));
        return bookings;
    }
}
