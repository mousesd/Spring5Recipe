package net.homenet;

import net.homenet.configuration.CouchbaseConfiguration;
import net.homenet.domain.Vehicle;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(CouchbaseConfiguration.class);
        VehicleRepository repository = context.getBean(VehicleRepository.class);

        CountDownLatch latch = new CountDownLatch(1);

        repository.saveAll(Flux.just(new Vehicle("TEM0001", "GREEN", 3, 1), new Vehicle("TEM0004", "RED", 4, 1)))
            .last().log()
            .then(repository.findById("TEM0001")).doOnSuccess(System.out::println)
            .then(repository.findById("TEM0004")).doOnSuccess(System.out::println)
            .then(repository.deleteById(Flux.just("TEM0001", "TEM0004")))
                .doOnSuccess(x -> latch.countDown())
                .doOnError(x -> latch.countDown())
            .subscribe();

        latch.await();
    }
}
