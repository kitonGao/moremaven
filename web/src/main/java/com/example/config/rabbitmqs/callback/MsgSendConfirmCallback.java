package com.example.config.rabbitmqs.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/10/0010 11:27
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
public class MsgSendConfirmCallback implements RabbitTemplate.ConfirmCallback {

    /**
     *  如果消息没有到达exchange（交换器），则confirm回调， ack =false
     *  如果消息到达exchange， 则confirm回调， ack=true
     *  exchange 到达queue 成功 ，则不回调return
     *  exchange 到达queue失败， 则回调return（需要设置mandatory= true， 否则不会回调，消息会丢失）
     *
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("correlationData = " + correlationData);
        if (ack) {
            System.out.println("消息消费成功。。。。");
        }else{
            System.out.println("消息消费失败.....原因为:【{}】" +cause);
        }
    }
}
