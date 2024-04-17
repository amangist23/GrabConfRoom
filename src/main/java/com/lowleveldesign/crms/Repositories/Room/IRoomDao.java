package com.lowleveldesign.crms.Repositories.Room;

import com.lowleveldesign.crms.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRoomDao extends JpaRepository<Room, UUID> {
}
