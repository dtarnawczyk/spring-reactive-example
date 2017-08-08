package org.springtest.service.web.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routing {

    @Bean
    RouterFunction<ServerResponse> routingFunction(UserHandler userHandler) {
        return route(
                    GET("/usrs/{name}").and(accept(APPLICATION_JSON)), userHandler::getUser)
                .andRoute(
                     GET("/usrs").and(accept(APPLICATION_JSON)), userHandler::getAllUsers)
                .andRoute(
                     POST("/usrs").and(accept(APPLICATION_JSON)), userHandler::createUser);

    }
}
