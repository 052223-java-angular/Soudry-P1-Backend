package com.revature.DDWar.services;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;


import com.revature.DDWar.dtos.responses.Monster;

import com.revature.DDWar.dtos.responses.MonsterList;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.DDWar.models.*;



import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertFalse;




class MonsterServiceTests {

    private MockWebServer mockWebServer;
    private WebClient webClient;

    @Mock
    private MonsterService monsterService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        // Create a new instance of MockWebServer
        mockWebServer = new MockWebServer();

        // Start the MockWebServer
        mockWebServer.start();

        // Get the URL of the MockWebServer
        String mockUrl = mockWebServer.url("/").toString();

        // Create a new instance of WebClient pointing to the MockWebServer
        webClient = WebClient.builder().baseUrl(mockUrl).build();

   
        
    }

    @AfterEach
    void tearDown() throws Exception {
        // Shutdown the MockWebServer
        mockWebServer.shutdown();
    }
    
@Test
void testGetMonsters() throws Exception {
    // Enqueue a mock response
    mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"results\": [{\"index\": \"1\", \"name\": \"Monster 1\", \"monsterType\": \"Type A\"}, " +
                    "{\"index\": \"2\", \"name\": \"Monster 2\", \"monsterType\": \"Type B\"}]}")
            .addHeader("Content-Type", "application/json"));

    // Make the request and retrieve the response
    Mono<MonsterList> result = webClient.get()
            .uri("/api/monsters")
            .retrieve()
            .bodyToMono(MonsterList.class);

    // Verify the response
    StepVerifier.create(result)
            .expectNextMatches(monsterList -> {
                assertThat(monsterList.getResults().size()).isEqualTo(2);

                MonsterResult monster1 = monsterList.getResults().get(0);
                assertThat(monster1.getIndex()).isEqualTo("1");
                assertThat(monster1.getName()).isEqualTo("Monster 1");
                assertThat(monster1.getMonsterType()).isEqualTo("Type A");

                MonsterResult monster2 = monsterList.getResults().get(1);
                assertThat(monster2.getIndex()).isEqualTo("2");
                assertThat(monster2.getName()).isEqualTo("Monster 2");
                assertThat(monster2.getMonsterType()).isEqualTo("Type B");

                return true;
            })
            .verifyComplete();

    // Verify the request sent to the MockWebServer
    RecordedRequest recordedRequest = mockWebServer.takeRequest();
    assertThat(recordedRequest.getMethod()).isEqualTo("GET");
    assertThat(recordedRequest.getPath()).isEqualTo("/api/monsters");
}

@Test public void testGetSingleMonster() {
     // Enqueue a mock response
    mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"name\": \"Adult Black Dragon\",\"size\": \"Huge\",\"type\": \"dragon\",\"alignment\": \"chaotic evil\"}")
            .addHeader("Content-Type", "application/json"));
    

    Mono<Monster> result = webClient.get()
            .uri("/api/monsters/adult-black-dragon")
            .retrieve()
            .bodyToMono(Monster.class);

  Monster expectedMonster = new Monster();
expectedMonster.setName("Adult Black Dragon");
expectedMonster.setSize("Huge");
expectedMonster.setType("dragon");
expectedMonster.setAlignment("chaotic evil");

    StepVerifier.create(result)
        .assertNext(actualMonster -> {
              assertThat(actualMonster.getName()).isEqualTo(expectedMonster.getName());
                assertThat(actualMonster.getType()).isEqualTo(expectedMonster.getType());
                  assertThat(actualMonster.getSize()).isEqualTo(expectedMonster.getSize());
            assertThat(actualMonster.getAlignment()).isEqualTo(expectedMonster.getAlignment());
        })
        .verifyComplete();
}

 @Test
    public void testFetchMonsterList() {
        // Configure the mock server to respond with the desired JSON
        String responseBody = "{\"results\": [{\"index\": \"1\", \"name\": \"Monster 1\", \"type\": \"Type A\"}, " +
                "{\"index\": \"2\", \"name\": \"Monster 2\", \"type\": \"Type B\"}]}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(responseBody)
                .addHeader("Content-Type", "application/json"));

        // Make the request and retrieve the response
        Mono<MonsterList> result = webClient.get()
                .uri("/api/monsters")
                .retrieve()
                .bodyToMono(MonsterList.class)
                .flatMap(monsterList -> {
                    List<MonsterResult> request = monsterList.getResults();

                    List<Mono<Monster>> monsterMonos = request.stream()
                            .map(monsterResult -> fetchSingleMonster(monsterResult.getIndex())
                                    .flatMap(monster -> {
                                        String type = monster.getType();
                                        if (type.contains("swarm")) {
                                            monsterResult.setMonsterType("swarm");
                                        } else {
                                            monsterResult.setMonsterType(type);
                                        }
                                        return Mono.just(monster);
                                    })
                            )
                            .collect(Collectors.toList());

                    return Flux.merge(monsterMonos)
                            .collectList()
                            .map(monsters -> {
                                monsterList.setResults(request);
                                return monsterList;
                            });
                });

        // Verify type changed
        StepVerifier.create(result)
                .expectNextMatches(monsterList -> {
                    List<MonsterResult> results = monsterList.getResults();
                    return results.size() == 2
                            && results.get(0).getMonsterType().equals("Beast")
                            && results.get(1).getMonsterType().equals("Beast");
                })
                .expectComplete()
                .verify();
    }
    
@Test
public void testFetchAndScrambleMonsterData() {
          // Enqueue a mock response
    mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody("{\"results\": [{\"index\": \"1\", \"name\": \"Monster 1\", \"monsterType\": \"Type A\"}, " +
                    "{\"index\": \"2\", \"name\": \"Monster 2\", \"monsterType\": \"Type B\"}]}")
            .addHeader("Content-Type", "application/json"));

    // Make the request and retrieve the response
    Mono<MonsterList> result = webClient.get()
            .uri("/api/monsters")
            .retrieve()
            .bodyToMono(MonsterList.class)
            .map((responseBody -> {
        Collections.shuffle(responseBody.getResults());
        return responseBody ;
    }
    ));

    StepVerifier.create(result)
        .assertNext(responseBody -> {
          List<MonsterResult> shuffledResults = responseBody.getResults();
           List<MonsterResult> originaResults = responseBody.getResults();
            Collections.shuffle(shuffledResults);

             boolean isShuffled = false;
            for (int i = 0; i < originaResults.size(); i++) {
                if (!originaResults.get(i).equals(shuffledResults.get(i))) {
                    isShuffled = true;
                    break;
                }
            }
             assertFalse(isShuffled);
        })
        .verifyComplete();
}



private Mono<Monster> fetchSingleMonster(String index) {
        Monster m = getMockMonster();
        return Mono.just(m);
    }

private Monster getMockMonster() {
    Monster monster = new Monster();
    monster.setName("Mock Monster");
    monster.setType("Beast");
    return monster;
}}