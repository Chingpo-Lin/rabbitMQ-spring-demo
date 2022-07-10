package com.example.rabbitmqspringdemo.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
// @RabbitListener(queues = "order_queue")
public class OderMQListener {

    // @RabbitHandler
    public void messageHandler(String body, Message message, Channel channel) throws IOException {

        long msgTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("msgTag=" + msgTag);
        System.out.println("message=" + message.toString());
        System.out.println("body=" + body);

        // tell broker, msg has been confirmed
        channel.basicAck(msgTag, false);

//        // tell broker, msg rejected, set if multiple or not, set if requeue
//        channel.basicNack(msgTag, false, true);
//
//        // only can reject one msg, set if requeue
//        channel.basicReject(msgTag, true);
    }
}
