package org.springtest.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import org.springtest.client.users.User;

@SpringBootApplication
public class  ClientApplication {

	@Bean
	WebClient webClient() {
		return WebClient.builder()
				.baseUrl("http://localhost:8080/usrs")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.filter(ExchangeFilterFunctions.basicAuthentication("user", "password"))
				.build();
	}

	@Bean
	CommandLineRunner clientRun(WebClient webClient) {
		return args -> {
			webClient.get().uri("")
					.retrieve()
					.bodyToFlux(User.class)
					.filter(user -> user.getName().equalsIgnoreCase("Tom"))
					.flatMap(user -> webClient.get().uri("/{name}", user.getName())
								.retrieve()
								.bodyToFlux(User.class))
					.subscribe(System.out::println);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
