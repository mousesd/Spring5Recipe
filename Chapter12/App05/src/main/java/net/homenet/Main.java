package net.homenet;

import net.homenet.domain.Character;
import net.homenet.domain.Planet;
import net.homenet.repository.Neo4jStarwarsRepository;
import net.homenet.repository.StarwarsRepository;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final String DB_PATH = System.getProperty("user.home") + "\\friends";
        final GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(Paths.get(DB_PATH).toFile());

        StarwarsRepository repository = new Neo4jStarwarsRepository(db);
        try (Transaction tx = db.beginTx()) {
            //# Planets
            Planet dagobah = new Planet("Dagobah");
            Planet tatooine = new Planet("Tatooine");
            Planet alderaan = new Planet("Alderaan");
            Stream.of(dagobah, tatooine, alderaan).forEach(repository::save);

            //# Characters
            Character han = new Character("Han Solo");
            Character leia = new Character("Leia Organa", alderaan, han);
            Character luke = new Character("Luke Skywalker");
            luke.setLocation(tatooine);
            leia.addFriend(han);
            leia.addFriend(leia);
            Character yoda = new Character("Yoda", dagobah, luke);
            Stream.of(han, leia, luke, yoda).forEach(repository::save);

            tx.success();
        }

        Result result = db.execute("MATCH (n) RETURN n.name as name");
        result.stream()
            .flatMap(m -> m.entrySet().stream())
            .map(row -> row.getKey() + ":" + row.getValue() + ";")
            .forEach(System.out::println);

        db.shutdown();
    }
}
