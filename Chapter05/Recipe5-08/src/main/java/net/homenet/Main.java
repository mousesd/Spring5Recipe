package net.homenet;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class Main {
    public static void main(String[] args) {
        final String url = "http://localhost:8080";
        WebClient.create(url)
            .get()
            .uri("reservations")
            .accept(MediaType.APPLICATION_STREAM_JSON)
            .exchange()
            .flatMapMany(response -> response.bodyToFlux(String.class))
            .subscribe(System.out::println);
    }
}
