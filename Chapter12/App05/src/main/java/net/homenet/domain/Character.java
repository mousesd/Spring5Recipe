package net.homenet.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@NodeEntity
public class Character {
    @Id
    @GeneratedValue
    private Long id = -1L;
    private String name;

    @Relationship(type = "LOCATION")
    private Planet location;

    @Relationship(type = "MASTER_OF")
    private Character apprentice;

    @Relationship(type = "FRIENDS_WITH")
    private final List<Character> friends = new ArrayList<>();

    public Character() { }

    public Character(String name) {
        this.name = name;
    }

    public Character(String name, Planet location, Character apprentice) {
        this.name = name;
        this.location = location;
        this.apprentice = apprentice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Planet getLocation() {
        return location;
    }

    public void setLocation(Planet location) {
        this.location = location;
    }

    public Character getApprentice() {
        return apprentice;
    }

    public void setApprentice(Character apprentice) {
        this.apprentice = apprentice;
    }

    public List<Character> getFriends() {
        return friends;
    }

    public void addFriend(Character friend) {
        friends.add(friend);
    }

    @Override
    public String toString() {
        return "Character{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", location=" + location +
            ", apprentice=" + apprentice +
            ", friends=" + friends +
            '}';
    }
}
