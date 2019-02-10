package net.homenet.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@SuppressWarnings("unused")
@NodeEntity
public class Planet {
    @Id
    @GeneratedValue
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

    @Override
    public String toString() {
        return "Planet{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
