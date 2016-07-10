package com.dudream.app;

import com.dudream.app.bean.properties.RabbitMqDemoQueueProperties;
import com.dudream.app.mq.base.MessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        push(ctx);
    }

    private static void push(ApplicationContext ctx) {
        MessageProducer messageProducer = ctx.getBean(MessageProducer.class);
        RabbitMqDemoQueueProperties queueProperties = ctx.getBean(RabbitMqDemoQueueProperties.class);

        Map<String, String> bizBody = new HashMap<String, String>();
        bizBody.put("name", "abc");
        messageProducer.publishMessage(queueProperties.getQueue(), bizBody);
    }

}
