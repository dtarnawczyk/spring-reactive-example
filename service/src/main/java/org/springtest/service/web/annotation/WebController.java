package org.springtest.service.web.annotation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springtest.service.service.UsersService;
import org.springtest.service.users.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class WebController {

    private final UsersService service;

    @GetMapping("/users/{name}")
    Mono<User> getUser(@PathVariable String name) {
        return this.service.findByName(name);
    }

    @GetMapping("/users")
    Flux<User> getAllUsers() {
        return this.service.findAll();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    Mono<String> createUser(@RequestBody Mono<User> newUser) {
        return this.service.addUser(newUser);
    }
}
