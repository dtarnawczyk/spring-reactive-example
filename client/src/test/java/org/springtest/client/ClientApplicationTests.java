package org.springtest.client;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springtest.client.users.User;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@SpringJUnitWebConfig
@SpringBootTest
@DisplayName("Client Test")
public class ClientApplicationTests {

	private String serviceUri = "/usrs/Jerry";
	private User testUser = new User("Jerry");
	private WebTestClient client;

	@Test
	public void contextLoads() {
	}

	@BeforeEach
	public void prepareService() {
		RouterFunction function = RouterFunctions.route(
				RequestPredicates.GET(serviceUri).and(accept(APPLICATION_JSON_UTF8)),
				request -> ServerResponse.ok().body(Mono.just(testUser), User.class));
		this.client = WebTestClient.bindToRouterFunction(function).build();
	}

	@Test
	public void givenUserService_whenAskForJerry_thenReturnUser () {
		this.client.get().uri(serviceUri)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(APPLICATION_JSON_UTF8)
				.expectBody(User.class).isEqualTo(testUser);
	}

}
