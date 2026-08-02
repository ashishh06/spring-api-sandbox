package com.ash.webapp.service;

import com.ash.webapp.model.Item;
import com.ash.webapp.model.User;
import com.ash.webapp.repo.ItemRepository;
import com.ash.webapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ItemRepository itemRepository;

    public List<User> getBlockedUsers(){
        return userRepo.findByIsBlockedTrue();
    }

    public void unblockUser(int userId){
        Optional<User> userOptional=userRepo.findById(userId);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            user.setBlocked(false);
            userRepo.save(user);
        }
    }

    public ResponseEntity<String> addDataToItem(List<Item> items){
        itemRepository.saveAll(items);
        return ResponseEntity.ok("Added to Item successful!");
    }
}
