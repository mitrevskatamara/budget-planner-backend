package com.controller;

import com.messages.ExchangeRateMessage;
import com.service.RabbitMqService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbitMq")
@CrossOrigin("https://budget-planner-f424865111f8.herokuapp.com")
@AllArgsConstructor
public class RabbitMqController {

    private final RabbitMqService rabbitMqService;

    @PostMapping("/getRate")
    public void getExchangeRates(@RequestParam String rate, @RequestParam Long userId) {
        ExchangeRateMessage exchangeRateMessage = new ExchangeRateMessage(rate, userId);
        rabbitMqService.sendMessageToGetRate(exchangeRateMessage);
    }

}
