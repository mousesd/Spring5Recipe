package net.homenet.domain;

@SuppressWarnings("unused")
public class Planet {
    private Long id = -1L;
    private String name;

    public Planet() { }

    public Planet(String name) {
        this.name = name;
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
}
