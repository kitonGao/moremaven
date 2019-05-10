package com.example.config.rabbitmqs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈队列配置〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/10/0010 10:48
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Configuration
public class QueueConfig {


    /**
     * durable: 持久化， rabbitmq 重启的时候不需要创建新的队列
     * auto-delete: 表示消息队列在使用的时候将被自动删除，默认是false
     * exclusive: 表示该消息队列是否只在当前connection生效， 默认是false
     * @return
     */
    @Bean
    public Queue firstQueue(){
        return new Queue(RabbitMqConfig.QUEUE_NAME1, true
        , false, true);
    }


    @Bean
    public Queue secondQueue(){
        return new Queue(RabbitMqConfig.QUEUE_NAME2, true,
                false, true);
    }

}
