package com.dudream.app.mq.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.Map;

/**
 * MQ消息体
 */
public class MqMessage {

    /**
     * 消息id，全局唯一
     */
    private String id;
    /**
     * 队列名称
     */
    private String queueName;
    /**
     * 消息发布时间
     */
    private Date publishedTime;
    /**
     * 消息消费时间
     */
    private Date consumedTime;
    /**
     * 消息处理完成时间
     */
    private Date finishedTime;
    /**
     * 消息内容
     */
    private Map<String, String> bizBody;

    public MqMessage() {
    }

    public MqMessage(Map<String, String> bizBody) {
        this.bizBody = bizBody;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Date getConsumedTime() {
        return consumedTime;
    }

    public void setConsumedTime(Date consumedTime) {
        this.consumedTime = consumedTime;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Map<String, String> getBizBody() {
        return bizBody;
    }

    public void setBizBody(Map<String, String> bizBody) {
        this.bizBody = bizBody;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }

}
