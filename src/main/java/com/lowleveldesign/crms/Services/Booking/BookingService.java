package com.lowleveldesign.crms.Services.Booking;

import com.lowleveldesign.crms.Controllers.FloorController;
import com.lowleveldesign.crms.Utilities.DTOMappers.BookingDtoMapper;
import com.lowleveldesign.crms.DTOModels.BookingDto;
import com.lowleveldesign.crms.DTOModels.SlotDto;
import com.lowleveldesign.crms.ErrorHandling.GenericClientException;
import com.lowleveldesign.crms.ErrorHandling.ResourceNotFoundException;
import com.lowleveldesign.crms.ErrorHandling.UserNotFoundException;
import com.lowleveldesign.crms.Models.*;
import com.lowleveldesign.crms.Repositories.Booking.IBookingDao;
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
import java.util.Optional;
import java.util.UUID;
@Component
public class BookingService implements IBookingService{
    private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

    @Autowired
    private IRoomService roomService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookingDao bookingDao;

    @Override
    public BookingDto addBooking(BookingDto bookingDto) {
        try{
           if(isBookingValidated(bookingDto)){
               User user = userService.getUserById(bookingDto.getUserId());
               Room room = roomService.getRoomById(bookingDto.getConfRoomId());

               Booking booking = BookingDtoMapper.getBookingInstance(bookingDto, user, room);
               bookingDao.save(booking);
               roomService.reserveSlot(bookingDto.getConfRoomId(), bookingDto.getBookedSlot());
           }
        }
        catch (UserNotFoundException ex){
            logger.error("User with userId: {} cannot make a booking as this user is not a registered User!", bookingDto.getUserId());
            throw new GenericClientException("You cannot make a booking as you are not a registered User!");
        }
        catch (ResourceNotFoundException ex){
            logger.error("User with userId: {} cannot make a booking as the conference room with confRoomId:{} doesn't exist!", bookingDto.getUserId(), bookingDto.getConfRoomId());
            throw new GenericClientException("You cannot make a booking to a Conference Room that doesn't exist!");
        }

        return bookingDto;
    }

    private Boolean isBookingValidated(BookingDto bookingDto){
        User user = userService.getUserById(bookingDto.getUserId());
        if(user == null){
            //To do:- Throw and exception that user doesn't exist. and also add logs.

        }

        Room room = roomService.getRoomById(bookingDto.getConfRoomId());
        if(room == null){

        }
        if(!isSlotAvailable(bookingDto.getConfRoomId(), bookingDto.getBookedSlot())){

        }

        return true;
    }

    @Override
    public BookingDto cancelBooking(BookingDto bookingDto) {

//        booking.setStatus(BookingStatus.CANCELLED);
//        return bookingRepo.cancelBooking(booking);
        Optional<Booking> booking = bookingDao.findById(bookingDto.getBookingId());

        if(booking.isPresent()){
            Booking booking1 = booking.orElse(null);
            if(booking1!=null) {
                booking1.setBookingStatus(BookingStatus.CANCELLED.toString());
                bookingDao.save(booking1);
                roomService.unreserveSlot(bookingDto.getConfRoomId(), bookingDto.getBookedSlot());
            }
        }
        bookingDto.setStatus(BookingStatus.CANCELLED);
        return bookingDto;
    }
    @Override
    public BookingDto getBookingById(UUID bookingId){
        Optional<Booking> booking = bookingDao.findById(bookingId);

        BookingDto bookingDto = new BookingDto();
        if(booking.isPresent()){
            bookingDto = BookingDtoMapper.getBookingDtoInstance(booking.orElse(null));
        }

        return bookingDto;
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
    public boolean isSlotAvailable(UUID confRoomId, SlotDto bookedSlot){
        Room confRoom = roomService.getRoomById(confRoomId);

        if(confRoom != null){
            List<Slot> occupiedSlots = confRoom.getOccupiedSlotsForRoom();
            //TO Do - If the Slot is present in occupied slots or not////
        }
        return true;
    }
    @Override
    public List<BookingDto> getAllBookings(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId) {
        List<BookingDto> bookingsDto = null;

        if(confRoomId == null && floorId == null && buildingId == null){
            List<Booking> bookings = bookingDao.findAll();
            bookings.forEach((booking)->{
                bookingsDto.add(BookingDtoMapper.getBookingDtoInstance(booking));
            });
        }

//        //Get bookings for particular user
//        if(userId != null)
//            bookings = getBookingsByUser(userId, buildingId, floorId, confRoomId);
//
//        //Get all the bookings for particular conference room
//        if(userId == null && buildingId == null && floorId == null && confRoomId != null)
//            bookings = getBookingsByConfRoom(confRoomId);
        return bookingsDto;
    }

    @Override
    public List<Booking> getBookingsByUser(UUID userId, UUID buildingId, UUID floorId, UUID confRoomId) {
        List<Booking> bookings = null;

        if(buildingId == null && floorId == null && confRoomId == null)
            return null;
//            return bookingRepo.getBookingsByUserId(userId);
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
//        Building buildings = buildingRepo.getBuildingById(buildingId);
//        List<Floor> floors = buildings.getFloorsInBuilding();

        List<UUID> confRoomIds = new ArrayList<UUID>();

//        for (Floor floor: floors){
//            floor.getRoomsInFloor().forEach(room -> {
//                confRoomIds.add(room.getConfroomId());
//            });
//        }

//        List<Booking> bookings = bookingRepo.getBookingsByUserId(userId);

//        List<Booking> filteredBookings =  bookings.stream()
//                .filter(booking -> confRoomIds.contains(booking.getConfRoomId()))
//                .toList();
        return null;
    }

    private List<Booking> getBookingsByUserNFloor(UUID userId, UUID buildingId, UUID floorId){
//        Building buildings = buildingRepo.getBuildingById(buildingId);
//        List<Floor> floors = buildings.getFloorsInBuilding();

        List<UUID> confRoomIds = new ArrayList<UUID>();

//        for (Floor floor: floors){
//            if(floor.getFloorId() == floorId){
//                floor.getRoomsInFloor().forEach(room -> {
//                    confRoomIds.add(room.getConfroomId());
//                });
//                break;
//            }
//        }

//        List<Booking> bookings = bookingRepo.getBookingsByUserId(userId);
//
//        List<Booking> filteredBookings =  bookings.stream()
//                .filter(booking -> confRoomIds.contains(booking.getConfRoomId()))
//                .toList();
        return null;
    }
    private List<Booking> getBookingsByConfRoom(UUID confRoomId) {
        List<Booking> bookings = null;
//        bookings.add(bookingRepo.getBookingByConfRoomId(confRoomId));
        return bookings;
    }
}
