package com.dudream.app.mq.base;

import java.util.Map;

/**
 * 消息生产者
 */
public interface MessageProducer {

    /**
     * 发布消息
     *
     * @param queue 队列名
     * @param bizBody 消息内容，key value对
     */
    void publishMessage(String queue, Map<String, String> bizBody);

}
