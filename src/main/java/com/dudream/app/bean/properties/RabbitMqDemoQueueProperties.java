package com.dudream.app.bean.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dudream on 2016/7/10.
 */
@Configuration
public class RabbitMqDemoQueueProperties {

    @Value("${rabbitmq.demo.queue}")
    private String queue;
    @Value("${rabbitmq.demo.concurrency}")
    private Integer concurrency;
    @Value("${rabbitmq.demo.prefetch}")
    private Integer prefetch;

    public String getQueue() {
        return queue;
    }

    public Integer getConcurrency() {
        return concurrency;
    }

    public Integer getPrefetch() {
        return prefetch;
    }
}
