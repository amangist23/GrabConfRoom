package com.lowleveldesign.crms.Utilities;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Slot {
    //Date --> int for simplicity
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
