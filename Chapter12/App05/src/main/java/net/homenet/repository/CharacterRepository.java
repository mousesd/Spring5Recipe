package net.homenet.repository;

import net.homenet.domain.Character;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, Long> { }
