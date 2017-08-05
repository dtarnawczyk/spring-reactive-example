package org.springtest.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springtest.service.repo.UsersRepository;
import org.springtest.service.users.User;
import reactor.core.publisher.Flux;

@SpringBootApplication
@AllArgsConstructor
public class ServiceApplication implements CommandLineRunner {

	private final UsersRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		usersRepository.deleteAll()
				.thenMany(
						Flux.just("Tom", "Bob", "Jerry")
								.map(User::new))
				.flatMap(usersRepository::save)
				.subscribe(null, null, () ->
						usersRepository.findAll().subscribe(System.out::println));


	}
}

