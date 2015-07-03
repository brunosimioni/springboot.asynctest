package com.brunosimioni.springboot.asynctest.listeners;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class QueueListener {

	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(String message) throws InterruptedException {
		System.out.println("Received <" + message + ">");
		latch.countDown();
        latch.await(10000, TimeUnit.MILLISECONDS);
	}

	public CountDownLatch getLatch() {
		return latch;
	}
	
}
