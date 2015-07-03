package com.brunosimioni.springboot.asynctest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brunosimioni.springboot.asynctest.adapters.QueueAdapter;
import com.brunosimioni.springboot.asynctest.repositories.NIORepository;
import com.brunosimioni.springboot.asynctest.repositories.vo.NIOEntry;

@Component
public class NIOService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NIOService.class);
	
	@Autowired
	public QueueAdapter queueAdapter;
	
	@Autowired
	public NIORepository repo;

	public int veryExpensiveOperation(int minMs, int maxMs, long count) {

		// take some sleep
		if (maxMs < minMs) maxMs = minMs;
        int processingTimeMs = minMs + (int) (Math.random() * (maxMs - minMs));

		try {
			Thread.sleep(processingTimeMs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// publish in queue
		boolean msgPublished = queueAdapter.publishASimpleMessage("uma mensagem");
		
		if (msgPublished)
			LOGGER.info("Postando uma mensagem no rabbit");
		else
			LOGGER.info("Erro ao postar uma mensagem no rabbit");

		
		// store on mongo
		repo.save(new NIOEntry("count: " + String.valueOf(count)));
		
        return processingTimeMs;
	}
}
