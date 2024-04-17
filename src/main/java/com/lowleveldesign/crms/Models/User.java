package com.lowleveldesign.crms.Models;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.UUID;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
