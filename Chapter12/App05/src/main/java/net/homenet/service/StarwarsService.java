package net.homenet.service;

import net.homenet.domain.Character;
import net.homenet.domain.Planet;

public interface StarwarsService {
    Planet save(Planet planet);
    Character save(Character character);
    void printAll();
}
