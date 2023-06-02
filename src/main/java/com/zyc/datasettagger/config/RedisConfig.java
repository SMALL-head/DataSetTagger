package com.zyc.datasettagger.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author zyc
 * @version 1.0
 */
@Configuration
@EnableCaching
public class RedisConfig {
    //redis连接工厂
//    @Resource
//    private LettuceConnectionFactory lettuceConnectionFactory;
//
//    /**
//     * 配置自定义redisTemplate
//     * @return redisTemplate
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(ObjectMapper objectMapper) {
//
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(lettuceConnectionFactory);
//        template.afterPropertiesSet();
//        return template;
//    }
}
