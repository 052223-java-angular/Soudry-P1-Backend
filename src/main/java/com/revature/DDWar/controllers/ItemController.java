package com.revature.DDWar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


@AllArgsConstructor

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {
    private final ItemService itemService;
    public final TokenService tokenService;

     
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
  

    @GetMapping("/all")
    public ResponseEntity<ItemPrinciple> grabItem(HttpServletRequest sreq) {

        String token = sreq.getHeader("auth-token");

        Boolean isExpired = tokenService.isJwtExpired(token);
        if(isExpired) {
            throw new InvalidTokenException("Your token is expired");
        }

        List<Item> listofItems = itemService.getItem();
        ItemPrinciple im = new ItemPrinciple(listofItems);
           logger.info("All items retreived");
        return ResponseEntity.status(HttpStatus.OK).body(im);
    }

    @GetMapping("/singleItem")
    public ResponseEntity<Item> grabSingleItem(@RequestParam(value = "name", required = true) String name
    , HttpServletRequest sreq
    ) {
        String token = sreq.getHeader("auth-token");
       
        Boolean isExpired = tokenService.isJwtExpired(token);
        if(isExpired) {
            throw new InvalidTokenException("Your token is expired");
        }
        Item item = itemService.getItemByName(name);

           logger.info("single item retreived");
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }
    
}
