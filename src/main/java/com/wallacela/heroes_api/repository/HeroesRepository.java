package com.wallacela.heroes_api.repository;

import com.wallacela.heroes_api.model.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes,String> {

}
