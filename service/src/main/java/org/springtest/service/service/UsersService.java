package org.springtest.service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springtest.service.repo.UsersRepository;
import org.springtest.service.users.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository repository;

    public Mono<User> findByName(final String name) {
        return repository.findById(name);
    }

    public Flux<User> findAll() {
        return repository.findAll();
    }

    public Mono<String> addUser(final Mono<User> userMono) {
        return userMono
                .flatMap(repository::save)
                .map(User::getName);
    }
}
