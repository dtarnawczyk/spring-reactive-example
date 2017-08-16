package org.springtest.service.web.functional;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springtest.service.service.UsersService;
import org.springtest.service.users.User;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@AllArgsConstructor
class UserHandler {

    private final UsersService service;

    Mono<ServerResponse> getUser(ServerRequest request) {
        String userName = request.pathVariable("name");
        return ok().body(service.findByName(userName), User.class)
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> getAllUsers(ServerRequest request){
        return ok().body(service.findAll(), User.class);
    }

    Mono<ServerResponse> createUser(ServerRequest request) {
        return status(HttpStatus.CREATED)
                .body(service.addUser(request.bodyToMono(User.class)), String.class);
    }
}
