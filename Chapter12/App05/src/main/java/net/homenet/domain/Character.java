package net.homenet.domain;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Character {
    private Long id = -1L;
    private String name;
    private Planet location;
    private Character apprentice;
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
}
