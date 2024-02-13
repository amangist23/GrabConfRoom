package com.lowleveldesign.crms.Models;

import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;

    public UUID getUserId(){
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserId(UUID id){
        userId = id;
    }
    public void setUserName(String name){
        userName = name;
    }
}
