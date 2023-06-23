package com.revature.DDWar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.DDWar.dtos.responses.MonsterList;
import com.revature.DDWar.services.MonsterService;
import com.revature.DDWar.services.TokenService;
import com.revature.DDWar.utils.custom_exceptions.InvalidTokenException;
import com.revature.DDWar.dtos.responses.Monster;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;

import jakarta.servlet.http.HttpServletRequest;


@AllArgsConstructor
@RestController
@RequestMapping("/monsters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MonsterController {



    private MonsterService monsterService;
    public final TokenService tokenService;


    // All works!
    @GetMapping("/all")
    public ResponseEntity<MonsterList> getAllMonsters(HttpServletRequest sreq) {

        String token = sreq.getHeader("auth-token");
        Boolean isExpired = tokenService.isJwtExpired(token);
        if(isExpired) {
            throw new InvalidTokenException("Your token is expired");
        }

        Mono<MonsterList> monsterMono = monsterService.fetchAndScrambleMonsterData();
      
        MonsterList mm = monsterMono.block();
        return ResponseEntity.status(HttpStatus.OK).body(mm);
    }

    @GetMapping("/singleMonster")
    public ResponseEntity<Monster> getOneMonsters(@RequestParam(value = "name", required = true) String name, HttpServletRequest sreq ) {

        String token = sreq.getHeader("auth-token");
        Boolean isExpired = tokenService.isJwtExpired(token);
        if(isExpired) {
            throw new InvalidTokenException("Your token is expired");
        }

        Mono<Monster> monsterMono = monsterService.fetchSingleMonster(name);
        Monster mm = monsterMono.block();
        return ResponseEntity.status(HttpStatus.OK).body(mm);
    }
}