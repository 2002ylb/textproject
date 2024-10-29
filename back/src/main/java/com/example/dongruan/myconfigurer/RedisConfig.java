package com.example.dongruan.myconfigurer;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.data.redis.connection.RedisConnectionFactory;
        import org.springframework.data.redis.core.RedisTemplate;
        import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
        import org.springframework.data.redis.serializer.StringRedisSerializer;
@SuppressWarnings("all")
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        // 设置连接工厂
        template.setConnectionFactory(connectionFactory);

        // 使用String序列化方式对key进行处理
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        // 使用Jackson2Json序列化方式进行value的处理
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        return template;
    }
}
