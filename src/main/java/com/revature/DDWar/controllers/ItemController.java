package com.revature.DDWar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.DDWar.dtos.responses.ItemPrinciple;
import com.revature.DDWar.services.ItemService;

import reactor.core.publisher.Mono;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/grabItem")
    public ResponseEntity<ItemPrinciple> grabItem() {
        // Mono<ItemPrinciple> monsterMono = 
        itemService.getItem();
        // ItemPrinciple mm = monsterMono.block();
        return ResponseEntity.status(HttpStatus.OK).body(mm);
    }
    
}
