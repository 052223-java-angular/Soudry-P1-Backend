package com.revature.DDWar.services;

import com.revature.DDWar.dtos.responses.MonsterList;
import com.revature.DDWar.dtos.responses.Monster;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Collections;


@Service
public class MonsterService {
    WebClient webClient = WebClient.builder().build();

    public Mono<MonsterList> fetchMonsterData() {
        return webClient.get()
        .uri("https://www.dnd5eapi.co/api/monsters")
        .retrieve()
        .bodyToMono(MonsterList.class);  
    }

    public Mono<MonsterList> fetchAndScrambleMonsterData() {
    return fetchMonsterData()
        .map(monsterList -> {
        Collections.shuffle(monsterList.getResults());
        return monsterList;
    });
}

    public Mono<Monster> fetchSingleMonster(String monsterName) {
        return webClient.get()
        .uri("https://www.dnd5eapi.co/api/monsters/" + monsterName)
        .retrieve()
        .bodyToMono(Monster.class);
    }
}