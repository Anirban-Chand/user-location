package com.example.user_location_ambula.controllers;


import com.example.user_location_ambula.entities.UserLocation;
import com.example.user_location_ambula.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("user_data")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    /*
    * UserController Class that consists of all 3 API endpoints
    * a) create_data
    * b) update_data/{id}
    * c) get_users/N
    *
    * I have divided the project in 3 major layers:
    *                                               - Controller Layer,
    *                                               - Service Layer,
    *                                               - Data Access Layer
    *
    * */

    @Autowired
    UserService userService;


    @PostMapping(path = "create_data")
    public ResponseEntity<UserLocation> createUser(@Valid @RequestBody UserLocation userLocation){
        UserLocation savedUserLocation = userService.createUser(userLocation);
        return ResponseEntity.created(URI.create("/users/" + savedUserLocation.getId())).body(savedUserLocation);
    }

    @PatchMapping(path = "update_user/{id}")
    public ResponseEntity<UserLocation> updateUser(@PathVariable("id") Long id, @RequestBody UserLocation userLocation){
        UserLocation updatedUserLocation = userService.updateUser(id, userLocation);
        return ResponseEntity.ok(updatedUserLocation);
    }

    @GetMapping(path="get_users/{count}")
    public ResponseEntity<List<UserLocation>> getNearestUsers(@PathVariable("count") Integer count){
        List<UserLocation> nearestUsersList = userService.getNearestUsers(count);
        return ResponseEntity.ok(nearestUsersList);
    }
}
