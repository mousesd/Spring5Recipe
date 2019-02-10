package net.homenet;

import net.homenet.configuration.StarwarsConfiguration;
import net.homenet.domain.Character;
import net.homenet.domain.Planet;
import net.homenet.service.StarwarsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StarwarsConfiguration.class);
        StarwarsService service = context.getBean(StarwarsService.class);

        //# Planets
        Planet dagobah = new Planet("Dagobah");
        Planet tatooine = new Planet("Tatooine");
        Planet alderaan = new Planet("Alderaan");
        Stream.of(dagobah, tatooine, alderaan).forEach(service::save);

        Character han = new Character("Han Solo");
        Character leia = new Character("Leia Organa", alderaan, han);
        Character luke = new Character("Luke Skywalker");
        luke.setLocation(tatooine);
        leia.addFriend(han);
        leia.addFriend(leia);
        Character yoda = new Character("Yoda", dagobah, luke);
        Stream.of(han, leia, luke, yoda).forEach(service::save);

        service.printAll();
        context.close();
    }
}
