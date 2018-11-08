package net.homenet;

import net.homenet.domain.Reservation;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Main {
    public static void main(String[] args) throws IOException {
        final String url = "http://localhost:8080";

        //# 1.
        //WebClient.create(url)
        //    .get()
        //    .uri("reservations")
        //    .accept(MediaType.APPLICATION_STREAM_JSON)
        //    .exchange()
        //    .flatMapMany(response -> response.bodyToFlux(String.class))
        //    .subscribe(System.out::println);

        //# 2.Reservation 객체로 변환시 아래 예외가 발생
        //# ERROR reactor.core.publisher.Operators - Operator called default onErrorDropped
        //# reactor.core.Exceptions$BubblingException: java.io.IOException: Connection closed prematurely
        WebClient.create(url)
            .get()
            .uri("reservations")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .flatMapMany(response -> response.bodyToFlux(Reservation.class))
            .subscribe(System.out::println);

        System.in.read();
    }
}
