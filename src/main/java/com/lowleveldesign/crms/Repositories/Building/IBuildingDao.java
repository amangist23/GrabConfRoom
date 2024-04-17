package com.lowleveldesign.crms.Repositories.Building;

import com.lowleveldesign.crms.Models.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IBuildingDao extends JpaRepository<Building, UUID> {
}
