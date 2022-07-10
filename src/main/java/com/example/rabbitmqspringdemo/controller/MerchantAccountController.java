package com.example.rabbitmqspringdemo.controller;

import com.example.rabbitmqspringdemo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/merchant")
public class MerchantAccountController {

    @Autowired
    private RabbitTemplate template;

    @GetMapping("check")
    public Object check() {

        // TODO  revise db

        template.convertAndSend(RabbitMQConfig.NEW_MERCHANT_EXCHANGE,
                RabbitMQConfig.NEW_MERCHANT_ROUTING_KEY, "SUCCESS");

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "plz upload in 10 s");

        return map;
    }
}
