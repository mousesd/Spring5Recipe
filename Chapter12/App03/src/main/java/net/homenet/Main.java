package net.homenet;

import net.homenet.configuration.MongoDbConfiguration;
import net.homenet.domain.Vehicle;
import net.homenet.repository.VehicleRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MongoDbConfiguration.class);
        VehicleRepository repository = context.getBean(VehicleRepository.class);

        CountDownLatch countDownLatch = new CountDownLatch(1);

        repository.count().doOnSuccess(count -> System.out.println("Number of vehicles: " + count))
            .thenMany(repository.saveAll(
                Flux.just(new Vehicle("TEM0001", "RED", 4, 4)
                    , new Vehicle("TEM0002", "RED", 4, 4)))
            .last())
            .then(repository.count())
                .doOnSuccess(count -> System.out.println("Number of vehicles: " + count))
            .then(repository.findByVehicleNo("TEM0001"))
                .doOnSuccess(System.out::println)
            .then(repository.deleteAll())
                .doOnSuccess(x -> countDownLatch.countDown())
                .doOnError(x -> countDownLatch.countDown())
            .then(repository.count())
                .subscribe(count -> System.out.println("Number of vehicles: " + count));

        countDownLatch.await();
        context.close();
    }
}
