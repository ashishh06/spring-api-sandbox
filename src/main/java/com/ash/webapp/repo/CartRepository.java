package com.ash.webapp.repo;

import java.util.List;
import java.util.Optional;

import com.ash.webapp.model.Item;
import com.ash.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ash.webapp.model.Cart;


public interface CartRepository extends JpaRepository<Cart,Integer>{

    List<Cart> findByUser(User user);
    void deleteByUserUserIdAndItemItemId(int userId,int itemId);

    Optional<Cart> findByUserAndItem(User user, Item item);
}
