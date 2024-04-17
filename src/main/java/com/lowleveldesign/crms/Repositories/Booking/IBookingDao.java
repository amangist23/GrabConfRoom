package com.lowleveldesign.crms.Repositories.Booking;

import com.lowleveldesign.crms.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBookingDao extends JpaRepository<Booking, UUID> {
}
