package com.lowleveldesign.crms.DTOModels;


import java.sql.Timestamp;

public class SlotDto {
    private Timestamp startOfSlot;
    private Timestamp endOfSlot;

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
}
