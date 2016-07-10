package com.dudream.app.mq.base;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 消息生产者实现类
 */
@Service
public class MessageProducerImpl implements MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publishMessage(String queue, Map<String, String> bizBody) {
        MqMessage mqMessage = new MqMessage(bizBody);
        mqMessage.setId(UUID.randomUUID().toString());
        mqMessage.setQueueName(queue);
        mqMessage.setPublishedTime(new Date());
        rabbitTemplate.convertAndSend(queue, mqMessage);
    }

}
