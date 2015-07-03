package com.brunosimioni.springboot.asynctest.adapters;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.brunosimioni.springboot.asynctest.listeners.QueueListener;


@Component
public class QueueAdapter {
	
	final static String queueName = "spring-boot";

	public boolean publishASimpleMessage(String message) {
		rabbitTemplate.convertAndSend(queueName, message);
		return true;
		
	}
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

    @Bean
    QueueListener receiver() {
        return new QueueListener();
    }

	@Bean
	MessageListenerAdapter listenerAdapter(QueueListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
}
