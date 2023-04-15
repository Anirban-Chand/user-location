package com.example.user_location_ambula.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /*
    * TestController is for testing if the application is running or not
    * Hitting this below endpoint will display the message
    * */

    @GetMapping("/")
    public String testingEndpoint(){
        return "User Location Application is Up and Running";
    }
}
