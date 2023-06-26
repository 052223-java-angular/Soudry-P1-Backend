package com.revature.DDWar.services;

import com.revature.DDWar.repositories.ItemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.revature.DDWar.controllers.MonsterController;
import com.revature.DDWar.entities.Item;
import java.util.Optional;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
@AllArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepo;


    private static final Logger logger = LoggerFactory.getLogger(MonsterController.class);
    public List<Item> getItem() {
        return itemRepo.findAll();
    }
    public Item getItemByName(String name) {
     Optional<Item> value = itemRepo.findByItemName(name);
     if (value.isPresent()) {
        Item v = value.get();
        return v;
     } else {
        // Item with the specified name was not found
        logger.info("no item found");
        throw new NoSuchElementException("Item not found: " + name);
    }
    }
}
