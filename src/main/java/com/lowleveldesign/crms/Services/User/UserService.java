package com.lowleveldesign.crms.Services.User;

import com.lowleveldesign.crms.ErrorHandling.UserNotFoundException;
import com.lowleveldesign.crms.Models.Booking;
import com.lowleveldesign.crms.Models.User;
import com.lowleveldesign.crms.Repositories.User.IUserRepo;
import com.lowleveldesign.crms.Repositories.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepo userRepo;

    //Model class should not be singleton, it will create references to a single object only inside map
    private User user;
    @Override
    public User addUser(String name) {
        user = new User();
        UUID id = UUID.randomUUID();

        user.setUserId(id);
        user.setUserName(name);

        return userRepo.addUser(user);
    }

    @Override
    public User getUserById(UUID userId) {
        User user = userRepo.getUserById(userId);

        if (user==null)
            throw new UserNotFoundException("The User you're trying to fetch doesn't exist!");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }
}
