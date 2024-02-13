package com.lowleveldesign.crms.Services.User;

import com.lowleveldesign.crms.Models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    public User addUser(String userName);
    public User getUserById(UUID userId);
    public List<User> getAllUsers();
}
