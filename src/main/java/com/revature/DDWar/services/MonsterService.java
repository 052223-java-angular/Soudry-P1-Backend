package com.revature.DDWar.services;

import com.revature.DDWar.dtos.responses.MonsterList;
import com.revature.DDWar.models.MonsterResult;
import com.revature.DDWar.dtos.responses.Monster;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;


@Service
public class MonsterService {
WebClient webClient = WebClient.builder().build();

public Mono<MonsterList> fetchMonsterList() {
    // flat map un wraps the Mono
    return fetchMonsterData().flatMap(monsterList -> {
        List<MonsterResult> request = monsterList.getResults();


        // Create a list of Mono<Monster> by mapping each MonsterResult to a Mono<Monster> using fetchSingleMonster()
        List<Mono<Monster>> monsterMonos = request.stream()
                .map(monsterResult -> fetchSingleMonster(monsterResult.getIndex())
                        .flatMap(monster -> {
                            String type = monster.getType();
                            if (type.contains("swarm")) {
                                  monsterResult.setMonsterType("swarm");
                            } else {
                            monsterResult.setMonsterType(monster.getType());
                            }
                            return Mono.just(monster);
                        })
                )
                .collect(Collectors.toList());

        // Merge the Mono objects into a single Flux, ensuring concurrency
        return Flux.merge(monsterMonos)
                .collectList()  // Collect the merged Flux into a list
                .thenReturn(new MonsterList(request));
    });
}




    public Mono<MonsterList> fetchMonsterData() {
        return webClient.get()
        .uri("https://www.dnd5eapi.co/api/monsters")
        .retrieve()
        .bodyToMono(MonsterList.class);  
    }

    public Mono<MonsterList> fetchAndScrambleMonsterData() {
    return fetchMonsterList()
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