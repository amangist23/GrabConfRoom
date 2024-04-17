package com.lowleveldesign.crms.Controllers;

import com.lowleveldesign.crms.Models.User;
import com.lowleveldesign.crms.Services.User.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/crms/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody String name){
        //To Do: HTTP Response for every response e.g. 200, 4XX
        return new ResponseEntity<>(userService.addUser(name), HttpStatus.CREATED); //CREATED = 201 //it's not 200=OK
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> Users = userService.getAllUsers(); // variable name in camelCase;
        // here we can simply return empty [] and code=OK
        return new ResponseEntity<>(Users, HttpStatus.OK);
    }
    //Should I use Query parameter in above endpoint instead of the URL parameter
    // as below endpoint to get particular user with the given userId????
    @GetMapping("/{userId}") //userId is unique attribute so URL parameter will be used not the query parametert(Query parameter is used non unique rows are needed)
    public ResponseEntity<User> getUserById(@PathVariable("userId") UUID userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
