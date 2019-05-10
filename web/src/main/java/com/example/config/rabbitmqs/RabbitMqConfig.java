package com.example.config.rabbitmqs;

import com.example.config.rabbitmqs.callback.MsgSendConfirmCallback;
import com.example.config.rabbitmqs.callback.MsgSendReturnCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈RabbitMq的配置〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/10/0010 10:41
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Configuration
public class RabbitMqConfig {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

    /*交换机的名称*/
    public static final String EXCHANGE = "rabbitmq-exchange-text";


    /*队列名称*/
    public static final String QUEUE_NAME1 = "first-queue";
    public static final String QUEUE_NAME2 = "second-queue";

    /*
    *key ： queue 在该direct-exchange中的key值，当消息发送给direct-exhange中指定的key为设置值的时候
    *   消息将转发给queue参数指定的消息队列
    *
    * */
    /*队列key*/
    public static final String ROUTINGKEY1 = "queue-one-key1";
    public static final String ROUTINGKEY2 = "queue-two-key2";


    @Autowired
    private RabbitTemplate rabbitTemplate;


    /*链接工厂*/
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private QueueConfig queueConfig;

    @Autowired
    private ExchangeConfig exchangeConfig;



    /*交换机和消息队列绑定，且指定队列key值*/
    @Bean
    public Binding binding_one(){
        return BindingBuilder.bind(queueConfig.firstQueue()
        ).to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTINGKEY1);

    }


    /**
     *
     * @return
     */
    @Bean
    public Binding binding_two(){
        return BindingBuilder.bind(queueConfig.secondQueue())
                .to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTINGKEY2);
    }


    /**
     * 使用RabbitTemplate 进行收发消息使其变得更加简单（用于接受和发送消息）
     * 可以设置消息确认机制和回调
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
//        logger.info("caching factory :{}", connectionFactory.getChannelCacheSize());

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);


        /*confirmcallback 接口用于实现消息发送到RabbitMQ 交换器后接受ack回调*/
        rabbitTemplate.setConfirmCallback(rabbitConfirmCallback());


        /*
        * 当mandatory 标志位设置为true时
        * 如果exchange 根据自身类型和消息routingKey 无法找到一个合适queue存储消息的时候
        *   则broker 会调用basic.return 方法将消息返还给生产者
        *   当mandatory设置为false的时候，出现上述的情况broker会直接将消息丢弃
        *
        *
        * 如果exhcange 到达queue失败，则回调return ，这里需要设置mandatory 为true， 否则不会回调，消息会丢失
        * */
        rabbitTemplate.setMandatory(true);

        /*returncallback 接口用于实现消息发送到RabbitMQ交换器，但没有相对应的队列与交换器绑定时的回调*/
        rabbitTemplate.setReturnCallback(rabbitReturnCallback());

        /*使用单独的发送链接， 避免生产者由于各种原因阻塞而导致消费者同样阻塞*/
        rabbitTemplate.setUsePublisherConnection(true);
        return rabbitTemplate;
    }




    public MsgSendConfirmCallback rabbitConfirmCallback(){
        return new MsgSendConfirmCallback();
    }

    public MsgSendReturnCallback rabbitReturnCallback(){
        return new MsgSendReturnCallback();
    }



}
