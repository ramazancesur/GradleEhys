package tr.com.prolms.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>This configuration file for caching.
 * Redis database is used to cache.
 * See {@linktourl http://redis.io/}
 * You can see this example
 * {@linktourl http://www.javacodegeeks.com/2013/02/caching-with-spring-data-redis.html}</p>
 * <p>This use redis.properties file.</p>
 */
@Configuration
@EnableCaching
@PropertySources(value = {@PropertySource("classpath:redis.properties")})
public class CachingConfig {

  /**
   * Environment obj.
   */
  @Autowired
  private Environment env;

  /**
   * JedisConnectionFactory config from redis property file.
   * @return JedisConnectionFactory
   */
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(env.getProperty("redis.host-name"));
    factory.setPort(env.getProperty("redis.port", Integer.class));
    factory.setUsePool(true);
    return factory;
  }

  /**
   * RedisTemplate connection is set jedisConnection.
   * @return RedisTemplate
   */
  @Bean
  RedisTemplate<Object, Object> redisTemplate() {
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }

  /**
   * RedisCacheManager bean.
   * @return CacheManager
   */
  @Bean
  CacheManager cacheManager() {
    return new RedisCacheManager(redisTemplate());
  }


}
