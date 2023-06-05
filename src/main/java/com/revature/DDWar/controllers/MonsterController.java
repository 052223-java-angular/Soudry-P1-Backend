package com.revature.DDWar.controllers;

import com.revature.DDWar.dtos.responses.Monster;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.DDWar.dtos.responses.MonsterList;
import com.revature.DDWar.services.MonsterService;
import reactor.core.publisher.Mono;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/monster")
public class MonsterController {
    private final MonsterService monsterService;

    @GetMapping("/all")
    public ResponseEntity<List<MonsterList>> getAllMonsters() {
        Mono<List<MonsterList>> monsterMono = monsterService.fetchMonsterData();
        List<MonsterList> mm = monsterMono.block();

        if (mm != null) {
            return ResponseEntity.status(HttpStatus.OK).body(mm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // @GetMapping("/singleMonster")
    // public ResponseEntity<Monster> getOneMonsters(@RequestParam(value = "name", required = true) String name) {
    //     Mono<Monster> monsterMono = monsterService.fetchSingleMonster();
    //     Monster mm = monsterMono.block();
    //     if (mm != null) {
    //         return ResponseEntity.status(HttpStatus.OK).body(mm);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
}
