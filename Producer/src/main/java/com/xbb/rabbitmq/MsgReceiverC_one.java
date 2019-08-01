package com.xbb.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiaobaobao
 * @date 2019/7/30 22:23
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiverC_one {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("处理器one接收处理队列A当中的消息： " + content);
    }
}