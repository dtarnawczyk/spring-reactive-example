package org.springtest.spring5test.web.functional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import org.springtest.spring5test.service.UsersService;
import org.springtest.spring5test.users.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@AllArgsConstructor
class UserHandler {

    private final UsersService service;

    Mono<ServerResponse> getUser(ServerRequest request) {
        String userName = request.pathVariable("name");
        return service.findByName(userName)
                .flatMap(userMono -> ok().body(Flux.just(userMono), User.class))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> getUsers(ServerRequest request){
        Flux<User> users = service.findAll();
        return ok().body(users, User.class);
    }
}
