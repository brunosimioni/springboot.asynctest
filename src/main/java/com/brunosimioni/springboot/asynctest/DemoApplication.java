package com.brunosimioni.springboot.asynctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
    }
}