package com.tng.cloud.java.examples.gracefulshutdown.application;

import com.tng.cloud.java.examples.gracefulshutdown.application.dto.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class WebController {

    @GetMapping("/hi")
    public TextMessage hi() throws InterruptedException {
        log.info("Invoked /hi endpoint...");
        var response = new TextMessage(String.format("Hello there, current time is %s", Instant.now()));
        log.info("Responding with result: {}", response);
        return response;
    }

    @GetMapping("/greetings")
    public TextMessage delayedQuery(@RequestParam(name = "delay", defaultValue = "0")  long delayInMils) throws InterruptedException {
        log.info("Received call for delayed query...");
        log.info("Attempt to sleep {} milliseconds before response. Now: {}", delayInMils, Instant.now());
        Thread.sleep(delayInMils);
        var response = new TextMessage(String.format("Hello there, I just slept %d milliseconds. Current time is %s", delayInMils, Instant.now()));
        log.info("Awake! Responding with result: {}", response);
        return response;
    }

}
