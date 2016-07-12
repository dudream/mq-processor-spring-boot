package com.dudream.app.mq.task;

import com.dudream.app.bean.properties.RabbitMqDemoQueueProperties;
import com.dudream.app.domain.Demo;
import com.dudream.app.mq.base.AbstractConsumerTask;
import com.dudream.app.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息消费者任务DemoTask
 */
@Component
public class DemoTask extends AbstractConsumerTask {

    private static final Logger LOG = LoggerFactory.getLogger(DemoTask.class);

    @Autowired
    private RabbitMqDemoQueueProperties rabbitMqDemoQueueProperties;

    @Autowired
    private DemoService demoService;

    /**
     * 处理业务：根据消息中的name字段插入DB的Demo表
     */
    @Override
    protected boolean doBiz(Map<String, String> bizBody) throws Exception {
        if (bizBody.containsKey("name")) {
            Demo demo = new Demo();
            demo.setName(bizBody.get("name"));
            demoService.addDemo(demo);
        }
        return true;
    }

    @Bean
    public Queue queue() {
        return new Queue(rabbitMqDemoQueueProperties.getQueue(), true);
    }

    @Bean
    @Autowired
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(rabbitMqDemoQueueProperties.getQueue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(rabbitMqDemoQueueProperties.getConcurrency());
        container.setConcurrentConsumers(rabbitMqDemoQueueProperties.getConcurrency());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setPrefetchCount(rabbitMqDemoQueueProperties.getPrefetch());
        container.setMessageListener(this);
        return container;
    }

}
