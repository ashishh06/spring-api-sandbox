package com.ash.webapp.controller;

import com.ash.webapp.model.User;
import com.ash.webapp.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/registerUser")
    public void registerUser(@RequestBody User user){
        registerService.registerUser(user);
    }
}
