package com.lowleveldesign.crms.Utilities.DTOMappers;

import com.lowleveldesign.crms.DTOModels.BookingDto;
import com.lowleveldesign.crms.DTOModels.SlotDto;
import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Models.Room;
import com.lowleveldesign.crms.Models.User;
import com.lowleveldesign.crms.Services.Room.IRoomService;
import com.lowleveldesign.crms.Services.User.IUserService;
import com.lowleveldesign.crms.Utilities.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingDtoMapper {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoomService roomService;
    public static Booking getBookingInstance(BookingDto bookingDto, User user, Room room){
        Booking booking = new Booking();

        booking.setUser(user);
        booking.setRoom(room);
        booking.setStartOfSlot(bookingDto.getBookedSlot().getStartOfSlot());
        booking.setEndOfSlot(bookingDto.getBookedSlot().getStartOfSlot());
        booking.setBookingStatus(bookingDto.getStatus().toString());
        return booking;
    }

    public static BookingDto getBookingDtoInstance(Booking booking){
        BookingDto bookingDto = new BookingDto();
        if(booking == null)
            return bookingDto;

        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setUserId(booking.getUser().getUserId());
        bookingDto.setConfRoomId(booking.getRoom().getConfroomId());
        bookingDto.setStatus(booking.getBookingStatus()=="ACTIVE"? BookingStatus.ACTIVE:BookingStatus.CANCELLED);

        SlotDto bookedSlot = new SlotDto();

        bookedSlot.setStartOfSlot(booking.getStartOfSlot());
        bookedSlot.setEndOfSlot(booking.getEndOfSlot());
        bookingDto.setBookedSlot(bookedSlot);
        return bookingDto;
    }
}
