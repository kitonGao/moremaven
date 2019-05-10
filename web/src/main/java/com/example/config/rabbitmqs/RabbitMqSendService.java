package com.example.config.rabbitmqs;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/10/0010 11:41
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */

@Service
@Component
public class RabbitMqSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到RabbitMQ 消息队列
     * @param message
     * @return
     */
    public String sendMsg(String message){
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY1,
                message, correlationData);
        return uuid;
    }

}
