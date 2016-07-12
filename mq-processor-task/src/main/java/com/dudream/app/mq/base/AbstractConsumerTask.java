package com.dudream.app.mq.base;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 消费者任务抽象父类
 */
public abstract class AbstractConsumerTask implements ChannelAwareMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractConsumerTask.class);

    @Autowired
    private JsonMessageConverter jsonMessageConverter;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            MqMessage mqMessage = (MqMessage) jsonMessageConverter.fromMessage(message);
            mqMessage.setConsumedTime(new Date());

            boolean doBizRet = this.doBiz(mqMessage.getBizBody());

            if (doBizRet) {
                mqMessage.setFinishedTime(new Date());
                this.doAck(message, channel);
                LOG.info("run task success for message:{}", mqMessage);
            } else {
                this.doNAck(message, channel);
                LOG.info("run task fail for message:{}", mqMessage);
            }
        } catch (Exception e) {
            this.doNAck(message, channel);
            LOG.error("run task exception for queue message:{}", message);
            throw new RuntimeException("run task error", e);
        }
    }

    /**
     * 根据消息内容处理业务
     *
     * @param bizBody 消息内容
     * @return true:处理成功；false:处理失败，消息重入mq
     * @throws Exception 处理失败，消息重入mq
     */
    protected abstract boolean doBiz(Map<String, String> bizBody) throws Exception;

    /**
     * 处理成功，并发送ack消息给mq
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    private void doAck(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 处理失败，发送nack消息给mq，并把message消息重入队列
     *
     * @param message
     * @param channel
     * @throws IOException
     */
    private void doNAck(Message message, Channel channel) throws IOException {
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }

}
