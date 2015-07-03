package com.brunosimioni.springboot.asynctest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class DemoApplicationConfig extends ResourceConfig {

	public DemoApplicationConfig() {
		this.packages("demo.resources");
	}
}