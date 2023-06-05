package com.revature.DDWar.services;

import com.revature.DDWar.dtos.responses.MonsterList;
import com.revature.DDWar.dtos.responses.Monster;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class MonsterService {
    WebClient webClient = WebClient.builder().build();

    public Mono<List<MonsterList>> fetchMonsterData() {
        return webClient.get()
        .uri("/api/monsters")
        .retrieve()
        .bodyToFlux(MonsterList.class)
        .collectList();    
    }

    // public Mono<Monster> fetchSingleMonster() {
    //     return webClient.get()
    //     .uri("/api/monsters/{monsterName}")
    //     .retrieve()
    //     .bodyToFlux(MonsterList.class)
    //     .collectList();    
    // }
}