package com.ash.webapp.controller;

import java.util.Map;

import com.ash.webapp.model.User;
import com.ash.webapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    public LoginService loginService;

    @PostMapping("/login/user")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user){

        try {
            return loginService.loginUser(user.getUsername(),user.getPassword());
//             return ResponseEntity.ok("User login successful!"+user.getUsername());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error: " + e.getMessage()));
        }
    }
}



// Will handle the login for user as well as the admin
