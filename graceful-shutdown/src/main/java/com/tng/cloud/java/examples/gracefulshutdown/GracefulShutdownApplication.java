package com.tng.cloud.java.examples.gracefulshutdown;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@Slf4j
@SpringBootApplication
public class GracefulShutdownApplication {

	public static void main(String[] args) {
		SpringApplication.run(GracefulShutdownApplication.class, args);

		Runtime.getRuntime().addShutdownHook(buildShutdownMethod());

	}

	private static Thread buildShutdownMethod() {
		return new Thread(() -> log.warn("Custom shutdown hook invoked! TS={}", Instant.now()));
	}
}
