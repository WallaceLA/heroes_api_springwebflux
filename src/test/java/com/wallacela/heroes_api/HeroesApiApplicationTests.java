package com.wallacela.heroes_api;

import com.wallacela.heroes_api.model.Heroes;
import com.wallacela.heroes_api.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static com.wallacela.heroes_api.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    HeroesRepository heroesRepository;

    @Test
    public void getAllHeroes(){
        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void getOneHeroById(){
        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void getOneHeroNotFound(){
        webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void insertHero(){
        Heroes hero = new Heroes("99", "Thanos", "marvel", 5);
        System.out.println("I AM INEVITABLE!");
        webTestClient.post().uri(HEROES_ENDPOINT_LOCAL)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(hero))
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void deleteHero(){
        System.out.println("SNAPPED!");
        webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"99")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Void.class);
    }


}
