package com.ash.webapp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.ash.webapp.model.User;
import com.ash.webapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    public UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private int maxLoginAttempt=3;


    public ResponseEntity<Map<String, Object>> loginUser(String username, String password) {
        Optional<User> userOptional = repo.findByUsername(username);


        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "User not found!"));
        }


        User user = userOptional.get();

        if (user.isBlocked()) {
            return ResponseEntity.status(403).body(Map.of("error", "Your account is locked due to multiple failed login attempts."));
        }

        if (user.isAdmin()) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid admin credentials!"));
            }
        }
        else if (!passwordEncoder.matches(password, user.getPassword())) {
            user.setCountOfLogin(user.getCountOfLogin()+1);
            repo.save(user);


            if(user.getCountOfLogin()>=maxLoginAttempt){
                user.setBlocked(true);
                repo.save(user);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials!"));

        }

        user.setCountOfLogin(0);
        repo.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful!");
        response.put("userId", user.getUserId());
        response.put("isAdmin", user.isAdmin());
        System.out.println(user);
        return ResponseEntity.ok(response);
    }
}
