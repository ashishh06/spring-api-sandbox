package com.ash.webapp.controller;

import java.util.List;

import com.ash.webapp.model.Item;
import com.ash.webapp.model.User;
import com.ash.webapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    public AdminService adminService;

    @GetMapping("/blocked")
    public ResponseEntity<List<User>> getBlockedUsers() {
        List<User> blockedUsers = adminService.getBlockedUsers();
        return ResponseEntity.ok(blockedUsers);
    }

    @PutMapping("/unblock/{userId}")
    public ResponseEntity<String> unblockUser(@PathVariable int userId){
        adminService.unblockUser(userId);
        return ResponseEntity.ok("User unblocked successfully!");
    }

    @PostMapping("/add/items")
    public ResponseEntity<String> addDataToItem(@RequestBody List<Item> items){
        return adminService.addDataToItem(items);
    }

}
