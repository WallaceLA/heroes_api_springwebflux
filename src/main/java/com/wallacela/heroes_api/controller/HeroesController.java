package com.wallacela.heroes_api.controller;

import com.wallacela.heroes_api.model.Heroes;
import com.wallacela.heroes_api.repository.HeroesRepository;
import com.wallacela.heroes_api.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.wallacela.heroes_api.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

 @RestController
 @Slf4j
public class HeroesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;

    private static final org.slf4j.Logger logIt = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
        this.heroesService = heroesService;
        this.heroesRepository = heroesRepository;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
     public Flux<Heroes> getAllItems(){
        logIt.info("Requesting the list of all heroes");
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
     public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable(value = "id") String id) {
        logIt.info("Requesting the hero with ID {}", id);
        return heroesService.findById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
     public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        logIt.info("A new hero has been created.");
        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
     public Mono<HttpStatus> deleteById(@PathVariable String id) {
        heroesService.deleteById(id);
        log.info("Deleting hero with ID {}", id);
        return Mono.just(HttpStatus.OK);
    }

}
