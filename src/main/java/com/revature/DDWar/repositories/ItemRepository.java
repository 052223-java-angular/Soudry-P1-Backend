package com.revature.DDWar.repositories;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.DDWar.entities.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> getItem();
    
}
