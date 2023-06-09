package com.revature.DDWar.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.DDWar.entities.Item;
import java.util.Optional;
@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findAll();   

   Optional<Item> findByItemName(String itemName);
}
