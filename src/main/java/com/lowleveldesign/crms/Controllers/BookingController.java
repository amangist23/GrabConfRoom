package com.lowleveldesign.crms.Controllers;

import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Models.Building;
import com.lowleveldesign.crms.Services.Booking.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/crms/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService; //To Do-

    @PostMapping("")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking){
        //Here I am taking the entire Booking object from the request body although i am ignoring the booking id as
        // id will be generated in the service layer.
        //To Do: HTTP Response for every response e.g. 200, 4XX
        return new ResponseEntity<>(bookingService.addBooking(booking), HttpStatus.CREATED);
    }

    //Not as per standard implementation see documentation.
    @PutMapping("")
    public ResponseEntity<Booking> cancelBooking(@RequestBody Booking booking){
        //To Do: HTTP Response for every response e.g. 200, 4XX
        return new ResponseEntity<>(bookingService.cancelBooking(booking), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Booking>> getAllBookings(
            @RequestParam() UUID userId,
            @RequestParam() UUID buildingId,
            @RequestParam() UUID floorId,
            @RequestParam() UUID confRoomId ){
        return new ResponseEntity<>(bookingService.getAllBookings(userId, buildingId, floorId, confRoomId), HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") UUID bookingId){
        return new ResponseEntity<>(bookingService.getBookingById(bookingId),HttpStatus.OK);
    }
}
