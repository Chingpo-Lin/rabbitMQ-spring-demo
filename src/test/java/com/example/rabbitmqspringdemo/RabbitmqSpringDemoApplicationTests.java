package com.example.rabbitmqspringdemo;

import com.example.rabbitmqspringdemo.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqSpringDemoApplicationTests {
//
	@Autowired
	private RabbitTemplate template;
//
//	@Test
//	void testSend() {
//		template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "order.new", "677");
//	}
//
//	@Test
//	void testConfirmedCallback() {
//
//		template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//
//			/**
//			 *
//			 * @param correlationData setting
//			 * @param ack if exchange get msg, true success, false fail
//			 * @param cause fail cause
//			 */
//			@Override
//			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//
//				System.out.println("ConfirmCallback ===>");
//				System.out.println("CorrelationData:" + correlationData);
//				System.out.println("ack:" + ack);
//				System.out.println("cause:" + cause);
//
//				if (ack) {
//					System.out.println("success");
//				} else {
//					System.out.println("fail, note in log or db");
//				}
//
//			}
//		});
//
//		// add to db TODO
//
//		// send success
//		 template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "order.new", "new order");
//
//		// send fail
////		template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME + "hehe", "order.new", "new order confirmcallback");
//	}
//
//	@Test
//	void testReturnCallback() {
//
//		template.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
//
//			@Override
//			public void returnedMessage(ReturnedMessage returnedMessage) {
//				int code = returnedMessage.getReplyCode();
//				System.out.println("code=" + code);
//				System.out.println("returned" + returnedMessage.toString());
//			}
//		});
//
//		// success
//		// template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "order.new", "new order returncallback");
//
//		// fail
//		template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "order.new", "new order returncallback");
//	}
}
