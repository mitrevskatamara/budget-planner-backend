package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketController {

    @MessageMapping("/update")
    @SendTo("/topic/converter")
    public String handleExchangeRateRequest(@Payload String message) {
        log.info("You received a message " + message);
        return "You received a message " + message;
    }
}
