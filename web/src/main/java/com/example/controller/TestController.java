package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.config.rabbitmqs.RabbitMqSendService;
import com.example.config.redis.RedisService;
import com.example.enums.CacheObjectEnum;
import com.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试Controller〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/5/0005 10:03
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    //    TODO spring 2.0 之后 spring容器自动生成StringRedisTemplate 和redisTemplate
//    使用RedisTemplate 操作缓存
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RabbitMqSendService rabbitMqSendService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<String> Test() {
        List<String> list = new ArrayList<>();
        for (int i=0; i<5; i++) {
            list.add(String.valueOf(i));
        }

        return list;
    }



    //Redis 的五种数据类型操作
    @RequestMapping(value = "/value")
    @ResponseBody
    public Object redis() throws Exception {

//        Object name =  redisService.set("name", "token", 10000);
//        String name1 =  redisService.get("name", String.class);

//        User u = new User();
//        u.setUsername("ces");
//        redisService.set(CacheObjectEnum.BaseEnumValue,"user", u, 1000);
//
        //输出value
        System.out.println("获取的值："+ JSON.toJSONString(redisService.get(CacheObjectEnum.BaseEnumValue,"user", User.class)));
        return redisService.get(CacheObjectEnum.BaseEnumValue,"user", User.class);


//        System.out.println("獲取的值：" + name1);
//        return name1;
    }


    /**
     * 测试服务端发送消息到RabbitMQ
     * @param message
     * @return
     */
    @RequestMapping(value = "/rabbitmqvalue")
    @ResponseBody
    public String setRabbitMqMessage(String message){
        if (!StringUtils.hasText(message)) {
            message = "测试消息";
        }
        return rabbitMqSendService.sendMsg(message);
    }





}
