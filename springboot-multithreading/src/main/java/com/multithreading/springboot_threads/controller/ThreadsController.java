package com.multithreading.springboot_threads.controller;

import com.multithreading.springboot_threads.service.AsyncWorkloadService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class ThreadsController {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsController.class);

    private final AsyncWorkloadService asyncWorkloadService;

    @GetMapping("/asyncThread")
    public String async() throws InterruptedException, ExecutionException {
        CompletableFuture<Boolean> future = asyncWorkloadService.backgroundWork();
        logger.info("Async thread returned: {}", future.get());
        return (String.format("Hello from Aysnc Thread call %s ", future.get()));
    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(1000000);
        return "Hello Threads";
    }
}
