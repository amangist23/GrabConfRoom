package com.lowleveldesign.crms.Models;

import com.lowleveldesign.crms.DTOModels.BookingDto;
import com.lowleveldesign.crms.Utilities.BookingStatus;
import com.lowleveldesign.crms.Utilities.Slot;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "confroom_id")
    private Room room;
//    private Slot bookedSlot;// To Do: Mapping is not feasible and necessay , direclty store two columns for start and end time.
    @Column(name = "start_of_slot")
    private Timestamp startOfSlot;
    @Column(name = "end_of_slot")
    private Timestamp endOfSlot;

    @Column(name = "booking_status")
    private String bookingStatus;

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Timestamp getStartOfSlot() {
        return startOfSlot;
    }

    public void setStartOfSlot(Timestamp startOfSlot) {
        this.startOfSlot = startOfSlot;
    }

    public Timestamp getEndOfSlot() {
        return endOfSlot;
    }

    public void setEndOfSlot(Timestamp endOfSlot) {
        this.endOfSlot = endOfSlot;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public static Booking getBookingFromBookingDto(BookingDto bookingDto){

        return null;
    }
}
