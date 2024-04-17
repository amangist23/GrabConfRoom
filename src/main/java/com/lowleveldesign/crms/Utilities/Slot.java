package com.lowleveldesign.crms.Utilities;

import com.lowleveldesign.crms.Models.Floor;
import com.lowleveldesign.crms.Models.Room;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Entity
public class Slot {
    //Date --> int for simplicity
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID slotId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confroom_id")
    private Room room;
    private Date startOfSlot;
    private Date endOfSlot;

    public Date getStartOfSlot() {
        return startOfSlot;
    }

    public void setStartOfSlot(Date startOfSlot) {
        this.startOfSlot = startOfSlot;
    }

    public Date getEndOfSlot() {
        return endOfSlot;
    }

    public void setEndOfSlot(Date endOfSlot) {
        this.endOfSlot = endOfSlot;
    }
}
