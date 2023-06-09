package com.revature.DDWar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.DDWar.dtos.responses.ItemPrinciple;
import com.revature.DDWar.services.ItemService;

import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;
import com.revature.DDWar.entities.Item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<ItemPrinciple> grabItem() {
        List<Item> listofItems = itemService.getItem();
        // ItemPrinciple mm = monsterMono.block();
        ItemPrinciple im = new ItemPrinciple(listofItems);
        return ResponseEntity.status(HttpStatus.OK).body(im);
    }

    @GetMapping("/singleItem")
    public ResponseEntity<Item> grabSingleItem(@RequestParam(value = "name", required = true) String name) {
        Item item = itemService.getItemByName(name);
        // ItemPrinciple mm = monsterMono.block();
        // ItemPrinciple im = new ItemPrinciple(listofItems);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }
    
}
