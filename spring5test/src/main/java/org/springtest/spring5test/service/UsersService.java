package org.springtest.spring5test.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springtest.spring5test.repo.UsersRepository;
import org.springtest.spring5test.users.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository repository;

    public Mono<User> findByName(String name) {
        return repository.findById(name);
    }

    public Flux<User> findAll() {
        return repository.findAll();
    }
}
