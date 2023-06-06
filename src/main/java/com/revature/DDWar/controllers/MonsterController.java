package com.revature.DDWar.controllers;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.revature.DDWar.dtos.responses.MonsterList;
import com.revature.DDWar.services.MonsterService;
import com.revature.DDWar.dtos.responses.Monster;
import reactor.core.publisher.Mono;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/monsters")
public class MonsterController {
    private final MonsterService monsterService;

    // All works!
    @GetMapping("/all")
    public ResponseEntity<MonsterList> getAllMonsters() {
        Mono<MonsterList> monsterMono = monsterService.fetchMonsterData();
        MonsterList mm = monsterMono.block();
        return ResponseEntity.status(HttpStatus.OK).body(mm);
    }

    @GetMapping("/singleMonster")
    public ResponseEntity<Monster> getOneMonsters(@RequestParam(value = "name", required = true) String name) {
        Mono<Monster> monsterMono = monsterService.fetchSingleMonster(name);
        Monster mm = monsterMono.block();

        return ResponseEntity.status(HttpStatus.OK).body(mm);
        // if (mm != null) {
        //     return ResponseEntity.status(HttpStatus.OK).body(mm);
        // } else {
        //     return ResponseEntity.notFound().build();
        // }
    }
}

    // }
// }
