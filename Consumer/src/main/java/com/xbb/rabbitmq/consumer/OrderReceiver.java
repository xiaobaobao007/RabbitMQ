package com.xbb.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.xbb.rabbitmq.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiaobaobao
 * @date 2019/8/1 10:49
 */
@Component
public class OrderReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
            key = "order.*"
        )
    )
    @RabbitHandler
    public void orderMessage(@Payload Order order,
                             @Headers Map<String, Object> heagers,
                             Channel channel) throws Exception {
        System.err.println("===========开始消费了=============");
        System.err.println("消费了：" + order.getId());

        Long deliveryTag = (Long) heagers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
