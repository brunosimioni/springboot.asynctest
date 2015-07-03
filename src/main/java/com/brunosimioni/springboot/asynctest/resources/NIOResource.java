package com.brunosimioni.springboot.asynctest.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;
import org.springframework.beans.factory.annotation.Autowired;

import com.brunosimioni.springboot.asynctest.resources.vo.NIOResponse;
import com.brunosimioni.springboot.asynctest.services.NIOService;
import com.google.common.base.Optional;

@Path("/nio")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class NIOResource {

	private Integer defaultMin = 1;
	private Integer defaultMax = 2;
	private final AtomicLong counter = new AtomicLong(0);

	@Autowired
	NIOService service;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hello";
	}
	
	@GET
	@Path("/async")
	@ManagedAsync
	public void asyncNIO(@Suspended final AsyncResponse asyncResponse, @QueryParam("minMs") Integer _minMs, @QueryParam("maxMs") Integer _maxMs) {
		long count = counter.incrementAndGet();
		Optional<Integer> minMs = Optional.fromNullable(_minMs); Optional<Integer> maxMs = Optional.fromNullable(_maxMs);
		int expensiveTime = service.veryExpensiveOperation(minMs.or(defaultMin), maxMs.or(defaultMax), count);
		NIOResponse response = new NIOResponse(count, expensiveTime);
		asyncResponse.resume(response);
	}

	@GET
	@Path("/sync")
	public NIOResponse syncNIO(@QueryParam("minMs") Integer _minMs, @QueryParam("maxMs") Integer _maxMs
	) {
		long count = counter.incrementAndGet();
		Optional<Integer> minMs = Optional.fromNullable(_minMs); Optional<Integer> maxMs = Optional.fromNullable(_maxMs);
		int expensiveTime = service.veryExpensiveOperation( minMs.or(defaultMin), maxMs.or(defaultMax), count);
		NIOResponse response = new NIOResponse(count, expensiveTime);
		return response;
	}
}