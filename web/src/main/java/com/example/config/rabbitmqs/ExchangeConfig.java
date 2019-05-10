package com.example.config.rabbitmqs;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈交换机的配置， 可以配置多个〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/10/0010 10:52
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Configuration
public class ExchangeConfig {


    /**
     * direct: 交换器相对来说比较简单， 匹配规则为：如果路由键匹配，消息就被投送到相关的队列
     *
     *durable: rabbitmq 重启的时候不需要创建新的交换机
     *
     * @return
     */
   @Bean
    public DirectExchange directExchange(){
       DirectExchange directExchange = new DirectExchange(
            RabbitMqConfig.EXCHANGE,true, false
       );
       return directExchange;
   }


    /**
     * topicExchange: 交换器你采用模糊匹配路由键的原则进行转发消息到队列中
     * @return
     */
//   @Bean
//    public TopicExchange topicExchange(){
//        TopicExchange topicExchange = new TopicExchange(
//                RabbitMqConfig.EXCHANGE, true, false
//        );
//        return topicExchange;
//   }
//
//
//   @Bean
//    public FanoutExchange fanoutExchange (){
//        FanoutExchange fanoutExchange = new FanoutExchange(
//                RabbitMqConfig.EXCHANGE, true, false
//        );
//        return fanoutExchange;
//   }



}
