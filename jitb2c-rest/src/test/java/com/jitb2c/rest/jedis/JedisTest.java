package com.jitb2c.rest.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

/**
 * @Author wuqiong6
 * @Date 2018/3/27 15:54
 */
public class JedisTest {

    @Test
    public void testJedisSingle(){
        //创建一个jedis对象
        Jedis jedis  = new Jedis("127.0.0.1",6379);
        //调用jedis对象的方法,方法名称和redis的命令一致
        jedis.set("key1","jedis test");
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis
        jedis.close();
    }

    /**
     * 使用连接池
     */
    @Test
    public void testJedisPool(){
        //创建Jedis连接池
        JedisPool pool = new JedisPool("127.0.0.1",6379);

        //从jedis连接池获取jedis对象
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("key1"));
        //使用完需要关闭jedis对象
        jedis.close();
    }

    /**
     * 集群连接
     */
    @Test
    public void testJedisCluster(){
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1",7001));
        nodes.add(new HostAndPort("127.0.0.1",7002));
        nodes.add(new HostAndPort("127.0.0.1",7003));
        nodes.add(new HostAndPort("127.0.0.1",7004));
        nodes.add(new HostAndPort("127.0.0.1",7005));
        nodes.add(new HostAndPort("127.0.0.1",7006));
        JedisCluster cluster = new JedisCluster(nodes);

        cluster.set("key1","1000");
        System.out.println(cluster.get("key1"));
    }

    /**
     * 测试spring创建的单机版jedis
     */
    @Test
    public void testSpringJedisSingle(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("key1"));
        jedis.close();
        pool.close();
    }

    /**
     * 测试spring创建的集群版jedis
     */
    @Test
    public void testSpringJedisCluster(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster cluster = (JedisCluster) applicationContext.getBean("redisClient");
        System.out.println(cluster.get("key1"));
        cluster.close();
    }

}
