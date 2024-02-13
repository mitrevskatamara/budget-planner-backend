package com.controller;

import com.messages.ExchangeRateMessage;
import com.service.RabbitMqService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbitMq")
@CrossOrigin("*")
@AllArgsConstructor
public class RabbitMqController {

    private final RabbitMqService rabbitMqService;

    @PostMapping("/getRate")
    public void getExchangeRates(@RequestParam String rate, @RequestParam Long userId) {
        ExchangeRateMessage exchangeRateMessage = new ExchangeRateMessage(rate, userId);
        rabbitMqService.sendMessageToGetRate(exchangeRateMessage);
    }

}
