package com.example.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈Redis 配置〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/8/0008 10:34
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Component //当做组件扫描
@Configuration //读取配置文件  (里面已经包含了Component)
@EnableCaching(mode = AdviceMode.PROXY)
// model属性默认proxy
// mode属性，可以选择值proxy和aspectj。默认使用proxy。当mode为proxy时，
// 只有缓存方法在外部被调用的时候才会生效。这也就意味着如果一个缓存方法在一个对
// 象的内部被调用SpringCache是不会发生作用的。而mode为aspectj时，就不会有
// 这种问题了。另外使用proxy的时候，只有public方法上的@Cacheable才会发生作用。
// 如果想非public上的方法也可以使用那么就把mode改成aspectj。
public class RedisConfig {


    /**
     * 主机
     */
    @Value("${spring.redis.host}")
    private String host;

    /**
     * 端口
     */
    @Value("${spring.redis.port}")
    private int port;

    /**
     * 超时时间
     */
    @Value("${spring.redis.timeout}")
    private int timeout;

    /**
     * 连接池最大线程数
     */
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    /**
     * 等待时间
     */
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWait;

    /**
     * 最大空闲链接
     */
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;




    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }
}
