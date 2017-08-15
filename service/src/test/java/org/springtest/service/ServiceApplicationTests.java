package org.springtest.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springtest.service.service.UsersService;
import org.springtest.service.users.User;
import org.springtest.service.web.annotation.WebController;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@SpringJUnitWebConfig
@SpringBootTest(classes = ServiceApplication.class)
@DisplayName("Web Controller Test")
public class ServiceApplicationTests {

	private WebTestClient client;
	private RouterFunction<ServerResponse> routerFunction;
	private WebController controller;

	@BeforeEach
	void setupClient(@Autowired UsersService usersService, @Autowired RouterFunction<ServerResponse> routerFunction) {
		this.controller = new WebController(usersService);
		this.routerFunction = routerFunction;
	}

	@ParameterizedTest(name = "test no.{index} with [{arguments}] type web")
	@EnumSource(value = DefType.class)
	@DisplayName("When asking for all users then should return the list of users")
	public void whenAksForAllUsers_thenReturnList(DefType definitionType) throws Exception {
		if(definitionType == DefType.ANNOTATION)
			client = WebTestClient.bindToController(controller).build();
		else
			client = WebTestClient.bindToRouterFunction(routerFunction).build();
        this.client.get()
				.uri(definitionType.getPath())
				.accept(APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_UTF8)
                .expectBodyList(User.class).hasSize(3)
				.consumeWith(result ->
					assertThat(result.getResponseBody().stream()
							.map(User::getName)
							.collect(toList())
							.containsAll(Arrays.asList("Tom", "Bob", "Jerry")))
				);
	}

	@ParameterizedTest(name = "test no.{index} with [{arguments}] type web")
	@EnumSource(value = DefType.class)
	@DisplayName("When asking for Tom then should return one user")
	public void whenAskForTom_thenReturnOne(DefType definitionType) throws Exception {
		if(definitionType == DefType.ANNOTATION)
			client = WebTestClient.bindToController(controller).build();
		else
			client = WebTestClient.bindToRouterFunction(routerFunction).build();
        this.client.get()
				.uri(definitionType.getPath()+"/Tom")
				.accept(APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_UTF8)
                .expectBody(User.class).isEqualTo(new User("Tom"));
	}

	@ParameterizedTest(name = "test no.{index} with [{arguments}] type web")
	@EnumSource(value = DefType.class)
	@DisplayName("Should create Bill")
	public void whenCreateBill_thenUserCreated(DefType definitionType) throws Exception {
		if(definitionType == DefType.ANNOTATION)
			client = WebTestClient.bindToController(controller).build();
		else
			client = WebTestClient.bindToRouterFunction(routerFunction).build();
		this.client.post()
				.uri(definitionType.getPath())
				.body(BodyInserters.fromObject(new User("Bill")))
				.accept(APPLICATION_JSON_UTF8)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(String.class).isEqualTo("Bill");
	}
}
