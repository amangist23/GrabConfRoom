package com.lowleveldesign.crms.Controllers;

import com.lowleveldesign.crms.Models.User;
import com.lowleveldesign.crms.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/crms/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')") //Not working
    public ResponseEntity<User> addUser(@RequestBody User user){
        //To Do: HTTP Response for every response e.g. 200, 4XX
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED); //CREATED = 201 //it's not 200=OK
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")//Method level authorization not working
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> Users = userService.getAllUsers(); // variable name in camelCase;
        return new ResponseEntity<>(Users, HttpStatus.OK);
    }
    //Should I use Query parameter in above endpoint instead of the URL parameter
    // as below endpoint to get particular user with the given userId????
    @GetMapping("/{userId}") //userId is unique attribute so URL parameter will be used not the query parametert(Query parameter is used non unique rows are needed)
    public ResponseEntity<User> getUserById(@PathVariable("userId") UUID userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
