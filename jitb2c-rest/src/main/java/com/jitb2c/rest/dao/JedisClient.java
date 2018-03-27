package com.jitb2c.rest.dao;

/**
 * 将对redis的操作抽取出来封装实现
 * @Author wuqiong6
 * @Date 2018/3/27 17:31
 */
public interface JedisClient {

    String get(String key);

    String set(String key,String value);

    String hget(String hkey,String key);

    long hset(String hkey,String key,String value);

    /**
     * 自增
     * @param key
     * @return
     */
    long incr(String key);

    /**
     * 自减
     * @param key
     * @return
     */
    long decr(String key);

    /**
     * 设置有效时间
     * @param key
     * @param second 时间
     * @return
     */
    long expire(String key,int second);

    /**
     * 获取有效时间
     * @param key
     * @return
     */
    long ttl(String key);

    long del(String key);
    long hdel(String hkey, String key);
}
