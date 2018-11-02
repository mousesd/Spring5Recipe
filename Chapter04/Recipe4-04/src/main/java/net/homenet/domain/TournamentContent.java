package net.homenet.domain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TournamentContent {
    private final static AtomicInteger counter = new AtomicInteger();

    private final String author;
    private final Date publicationDate;
    private final String name;
    private final String link;
    private final int id;

    private TournamentContent(String author, Date publicationDate, String name, String link, int id) {
        this.author = author;
        this.publicationDate = publicationDate;
        this.name = name;
        this.link = link;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public int getId() {
        return id;
    }

    public static TournamentContent of(String author, Date publicationDate, String name, String link) {
        return new TournamentContent(author, publicationDate, name, link, counter.incrementAndGet());
    }
}
