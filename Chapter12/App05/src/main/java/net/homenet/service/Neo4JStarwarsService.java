package net.homenet.service;

import net.homenet.domain.Character;
import net.homenet.domain.Planet;
import net.homenet.repository.CharacterRepository;
import net.homenet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;

@Service
@Transactional
public class Neo4JStarwarsService implements StarwarsService {
    private final PlanetRepository planetRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public Neo4JStarwarsService(PlanetRepository planetRepository, CharacterRepository characterRepository) {
        this.planetRepository = planetRepository;
        this.characterRepository = characterRepository;
    }

    @Override
    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    @Override
    public Character save(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public void printAll() {
        planetRepository.findAll().forEach(System.out::println);
        characterRepository.findAll().forEach(System.out::println);
    }

    @PreDestroy
    public void cleanup() {
        planetRepository.deleteAll();
        characterRepository.deleteAll();
    }
}
