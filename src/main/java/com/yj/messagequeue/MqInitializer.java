package com.yj.messagequeue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by zhiyuan.huang on 2017/6/20.
 */
@Component
public class MqInitializer {
    RabbitAdmin rabbitAdmin;

    @Autowired
    public MqInitializer(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
        registerWechatUser();
    }




    public void registerWechatUser() {
        TopicExchange topicExchange = new TopicExchange(UserTopics.getExchangeName());
        rabbitAdmin.declareExchange(topicExchange);

        String[] topics = UserTopics.ALL;
        for (String topic :
                topics) {
            topic = UserTopics.getTopicName(topic);
            Queue queue = new Queue(topic, true);
            rabbitAdmin.declareQueue(queue);

            Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(topic);
            rabbitAdmin.declareBinding(binding);
        }
    }

}
