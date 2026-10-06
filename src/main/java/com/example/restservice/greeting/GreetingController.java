package com.example.restservice.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RequestMapping("/api")
@RestController
public class GreetingController {
	private static final Logger log =
			LoggerFactory.getLogger(GreetingController.class);
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();




	@PostMapping("/process")
	public String process() {
		log.info("POST /process called");
		return "process called!";
	}


	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		log.info("GET /greeting called");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
