package com.revature.DDWar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.DDWar.dtos.responses.ItemPrinciple;
import com.revature.DDWar.services.ItemService;
import com.revature.DDWar.services.TokenService;
import com.revature.DDWar.utils.custom_exceptions.InvalidTokenException;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import com.revature.DDWar.entities.Item;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {
    private final ItemService itemService;
    public final TokenService tokenService;
    // public final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<ItemPrinciple> grabItem(HttpServletRequest sreq) {

          String token = sreq.getHeader("auth-token");

        Optional<String> idReference = tokenService.extractUserIdOptional(token);
        if(idReference.isEmpty()) {
            throw new InvalidTokenException("Your token is invalid or expired");
        }

        List<Item> listofItems = itemService.getItem();
        // ItemPrinciple mm = monsterMono.block();
        ItemPrinciple im = new ItemPrinciple(listofItems);
        return ResponseEntity.status(HttpStatus.OK).body(im);
    }

    @GetMapping("/singleItem")
    public ResponseEntity<Item> grabSingleItem(@RequestParam(value = "name", required = true) String name, HttpServletRequest sreq) {
          // String token = sreq.getHeader("auth-token");
        Item item = itemService.getItemByName(name);
        // ItemPrinciple mm = monsterMono.block();
        // ItemPrinciple im = new ItemPrinciple(listofItems);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }
    
}
