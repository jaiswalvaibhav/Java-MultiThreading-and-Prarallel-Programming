package com.multithreading.springboot_threads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringbootThreadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootThreadsApplication.class, args);
	}

}
