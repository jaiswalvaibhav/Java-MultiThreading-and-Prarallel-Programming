package com.multithreading.springboot_threads.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncWorkloadService {

    @Async
    public CompletableFuture<Boolean> backgroundWork() throws InterruptedException {
        // CompletableFuture grabs the result from an async operation (similar to future but a bit more involved)
        Thread.sleep(4000);
        return CompletableFuture.completedFuture(true);
    }
}
