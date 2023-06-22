package com.revature.DDWar.services;

import com.revature.DDWar.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import com.revature.DDWar.entities.Item;
import java.util.Optional;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
@AllArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepo;
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
        throw new NoSuchElementException("Item not found: " + name);
    }
    }
}
