package net.homenet.repository;

import net.homenet.domain.Character;
import net.homenet.domain.Planet;

public interface StarwarsRepository {
    Planet save(Planet planet);
    Character save(Character character);
}
