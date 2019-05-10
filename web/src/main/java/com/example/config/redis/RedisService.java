package com.example.config.redis;

import com.alibaba.fastjson.JSON;
import com.example.enums.CacheObjectEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * 〈一句话功能简述〉<br>
 * 〈Redis连接池 配置类 〉
 * <p>
 * TODO
 *
 * @author Administrator
 * @create 2019/5/6/0006 18:18
 * @since 1.0.0
 * 备注:写这段代码的时候，只有上帝和我知道它是干嘛的。现在，只有上帝知道。
 */
@Service
public class RedisService{

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private JedisPool jedisPool;

    public <T> T get(CacheObjectEnum objectEnum , String key, Class<T> tClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real key
            String realKey = objectEnum.getObjType()+":"+(String) key;
            String str = jedis.get(realKey);
            T t = String2Bean(str, tClass);
            return t;
        }catch (Exception e) {
            logger.error("redis连接池异常【{}】",e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }


    public <T> T get(String key, Class<T> tClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real key
            String realKey = (String) key;
            String str = jedis.get(realKey);
            T t = String2Bean(str, tClass);
            return t;
        }catch (Exception e) {
            logger.error("redis连接池异常【{}】",e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public <T> boolean set(CacheObjectEnum objectEnum, String key, T value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value_new = Bean2String(value);
            if (value_new == null ||value_new.length() <= 0) {
                return false;
            }
            //生成Real Key
            String realKey = objectEnum.getObjType()+":"+key;
            //过期时间
            if (!StringUtils.isEmpty(timeout)) {
                if (timeout <= 0) {
                    jedis.set(realKey, value_new);
                } else {
                    jedis.setex(realKey, timeout, value_new);
                }
            }
            return true;

        }catch (Exception e) {
            logger.error("redis连接池异常【{}】",e.getMessage());
            return false;
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> boolean set(String key, T value, int time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value_new = Bean2String(value);
            if (value_new == null ||value_new.length() <= 0) {
                return false;
            }
            //生成Real Key
            String realKey = key;
            //过期时间
            if (!StringUtils.isEmpty(time)) {
                if (time <= 0) {
                    jedis.set(realKey, value_new);
                } else {
                    jedis.setex(realKey, time, value_new);
                }
            }

            return true;

        }catch (Exception e) {
            logger.error("redis连接池异常【{}】",e.getMessage());
            return false;
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 检查key 是否存在
     * @param objectEnum
     * @param key
     * @param <T>
     * @return
     */
    public <T> Boolean exists(CacheObjectEnum objectEnum, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real key
            String realKey = objectEnum.getObjType()+":"+key;
            return jedis.exists(realKey);

        }catch (Exception e) {
            logger.error("连接池异常【{}】", e.getMessage());
            return null;
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 增加key 对应的值
     * @param objectEnum
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(CacheObjectEnum objectEnum, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real key
            String realKey = objectEnum.getObjType()+":"+key;
            return jedis.incr(key);

        }catch (Exception e) {
            logger.error("redis连接池异常【{}】", e.getMessage());
            return null;
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 减少key 对应的对象的值
     * @param objectEnum
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(CacheObjectEnum objectEnum, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成real key 的值
            String realKey = objectEnum.getObjType()+":"+key;
            return jedis.decr(realKey);
        }catch (Exception e) {
            logger.error("redis 连接池异常【{}】", e.getMessage());
            return null;
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }



    /**
     *
     * @param value
     * @param <T>
     * @return
     */
    private <T> String Bean2String(T value) {
        if (value == null) {
            return null;
        }
        Class<?> claszz = value.getClass();
        if (claszz == int.class || claszz == Integer.class) {
            return ""+value;
        } else if (claszz == String.class) {
            return (String) value;
        } else if (claszz == long.class || claszz == Long.class) {
            return ""+value;
        } else {
            String s = JSON.toJSONString(value);
            return s;
        }

    }


    private <T> T String2Bean(String str, Class<T> clazz) {
        if (str == null || str.length() < 0 ||clazz == null) {
            return null;
        }
        if (clazz == int.class ||clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class ||clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }

    }




}
