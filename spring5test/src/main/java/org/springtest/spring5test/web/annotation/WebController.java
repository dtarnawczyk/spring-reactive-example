package org.springtest.spring5test.web.annotation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springtest.spring5test.service.UsersService;
import org.springtest.spring5test.users.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
class WebController {

    private final UsersService service;

    @GetMapping("/users/{name}")
    Mono<User> getUser(@PathVariable String name) {
        return this.service.findByName(name);
    }

    @GetMapping("/users")
    Flux<User> getAllUsers() {
        return this.service.findAll();
    }

}
