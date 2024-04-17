package com.lowleveldesign.crms.Services.User;

import com.lowleveldesign.crms.Controllers.BuildingController;
import com.lowleveldesign.crms.ErrorHandling.UserNotFoundException;
import com.lowleveldesign.crms.Models.User;
import com.lowleveldesign.crms.Repositories.User.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService implements IUserService{
    private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private IUserDao userDao;

    //Model class should not be singleton, it will create references to a single object only inside map
    private User user;
    @Override
    public User addUser(String name) {
        user = new User();
        user.setUserName(name);

        return userDao.save(user);
    }

    @Override
    public User getUserById(UUID userId) {
        Optional<User> user = userDao.findById(userId);

        return user.orElseThrow(() -> new UserNotFoundException("The User you're trying to fetch doesn't exist!"));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
