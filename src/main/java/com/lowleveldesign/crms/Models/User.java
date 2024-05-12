package com.lowleveldesign.crms.Models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true,updatable = false, nullable = false, columnDefinition = "CHAR(36)") // @Column annotation is used to define the column in database that maps annotated field.
    private UUID userId;
    private String password;
    private String email;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private UserRole roles;

    public UUID getUserId(){
        return userId;
    }

    public void setUserId(UUID id){
        userId = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserRole getRoles() {
        return roles;
    }
    public void setRoles(UserRole roles) {
        this.roles = roles;
    }
}
