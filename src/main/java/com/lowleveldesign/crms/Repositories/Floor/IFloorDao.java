package com.lowleveldesign.crms.Repositories.Floor;

import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IFloorDao extends JpaRepository<Floor, UUID> {
}
