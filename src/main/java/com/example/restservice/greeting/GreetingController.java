package com.example.restservice.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequestMapping("/api")
@RestController
public class GreetingController {
	private static final Logger log =
			LoggerFactory.getLogger(GreetingController.class);
	private static final String TEMPLATE = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@PostMapping("/process")
	public ResponseEntity<String> process() {
		log.debug("\n\n POST /process called");
		return ResponseEntity.ok("process called!");
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		log.debug("GET /greeting called");
		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
	}
}
