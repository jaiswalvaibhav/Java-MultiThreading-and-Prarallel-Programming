package com.multithreading.springboot_threads.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public TaskExecutor taskExecutor() {// Spring framework's Executor
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);        // Minimum threads always alive
        executor.setMaxPoolSize(4);        // Maximum threads allowed
        executor.setQueueCapacity(500);     // How many tasks can wait if all threads are busy
        executor.setThreadNamePrefix("async-worker-thread");
        executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());//What to do when pool and queue are full
        executor.initialize();
        return executor;
    }
}