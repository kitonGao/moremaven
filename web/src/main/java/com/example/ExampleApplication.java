package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/4/29/0029 14:55
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@SpringBootApplication
//@EnableCaching
@MapperScan(basePackages ="com.example.mapper" )
public class ExampleApplication {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ExampleApplication.class);

    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(ExampleApplication.class, args);
        logger.info("程序启动成功。。。");
    }
}
