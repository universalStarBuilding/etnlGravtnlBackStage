package com.etnlgravtnl.common.cache.redis;


import com.etnlgravtnl.common.utils.PropertiesLoader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Get the Redis Object from the Pool,
 * Redis using commons-pool to manage its own pool
 *
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 *
 */
public class RedisPoolManager {

    private static JedisPool pool;
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("properties/redis-jedis.properties");

    static {


        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.valueOf(loader.getProperty("redis.pool.maxTotal")));
        config.setMaxIdle(Integer.valueOf(loader.getProperty("redis.pool.maxIdle")));
        config.setMaxWaitMillis(Long.valueOf(loader.getProperty("redis.pool.maxWaitMillis")));
        config.setTestOnBorrow(Boolean.valueOf(loader.getProperty("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(loader.getProperty("redis.pool.testOnReturn")));

        pool = new JedisPool(config, loader.getProperty("redis.ip"), Integer.valueOf(loader.getProperty("redis.port")));
    }

    /**
     * Get Jedis resource from the pool
     * @return
     */
    public static Jedis createInstance() {
        Jedis jedis = pool.getResource();
        jedis.auth(loader.getProperty("redis.password"));
        return jedis;
    }

    /**
     * Return the resource to pool
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

}
