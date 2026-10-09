package com.example.restservice.greeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Plain unit tests for {@link GreetingController} without a Spring context.
 */
class GreetingControllerUnitTests {

	private GreetingController controller;

	@BeforeEach
	void setUp() {
		controller = new GreetingController();
	}

	@Test
	void greetingShouldFormatGivenName() {
		Greeting greeting = controller.greeting("Spring");
		assertThat(greeting.content()).isEqualTo("Hello, Spring!");
	}

	@Test
	void greetingShouldHandleEmptyName() {
		Greeting greeting = controller.greeting("");

		assertThat(greeting.content()).isEqualTo("Hello, !");
	}

	@Test
	void firstGreetingShouldHaveIdOne() {
		Greeting greeting = controller.greeting("World");

		assertThat(greeting.id()).isEqualTo(1L);
	}

	@Test
	void greetingIdShouldIncrementOnEachCall() {
		Greeting first = controller.greeting("WA");
		Greeting second = controller.greeting("B");
		Greeting third = controller.greeting("C");

		assertThat(first.id()).isEqualTo(1L);
		assertThat(second.id()).isEqualTo(2L);
		assertThat(third.id()).isEqualTo(3L);
	}

	@Test
	void countersShouldBeIndependentPerControllerInstance() {
		controller.greeting("A");
		controller.greeting("B");

		Greeting fromNewInstance = new GreetingController().greeting("C");

		assertThat(fromNewInstance.id()).isEqualTo(1L);
	}

	@Test
	void processShouldReturnOkWithConfirmation() {
		ResponseEntity<String> response = controller.process();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("process called!");
	}
}
