package com.eci.arcn.producer_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publisher")
public class PubllisherController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Autowired
	private Publisher publisher;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        log.info("Enviando mensaje: '{}' a la cola '{}'", message, queueName);
        publisher.send(message);
        return "Mensaje '" + message + "' enviado!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
