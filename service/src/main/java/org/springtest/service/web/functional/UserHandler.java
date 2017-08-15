package org.springtest.service.web.functional;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import org.springtest.service.service.UsersService;
import org.springtest.service.users.User;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@AllArgsConstructor
class UserHandler {

    private final UsersService service;

    Mono<ServerResponse> getUser(ServerRequest request) {
        String userName = request.pathVariable("name");
        return service.findByName(userName)
                .flatMap(userMono -> ok().body(Mono.just(userMono), User.class))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> getAllUsers(ServerRequest request){
        return ok().body(fromPublisher(service.findAll(), User.class));
    }

    Mono<ServerResponse> createUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> service.addUser(Mono.just(user)))
                .flatMap(createdUserName ->
                    ServerResponse.status(HttpStatus.CREATED).body(Mono.just(createdUserName), String.class)
                );
    }
}
