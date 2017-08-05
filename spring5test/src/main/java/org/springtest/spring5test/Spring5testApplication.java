package org.springtest.spring5test;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springtest.spring5test.repo.UsersRepository;
import org.springtest.spring5test.users.User;
import reactor.core.publisher.Flux;

@SpringBootApplication
@AllArgsConstructor
public class Spring5testApplication implements CommandLineRunner {

	private final UsersRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(Spring5testApplication.class, args);
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

