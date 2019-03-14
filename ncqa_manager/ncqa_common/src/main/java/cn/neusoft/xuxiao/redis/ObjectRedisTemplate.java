package cn.neusoft.xuxiao.redis;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @作者 ShowSoft
 * @创建时间 2016/6/3 14:09
 * @描述
 */
public class ObjectRedisTemplate extends RedisTemplate<String,Object> {

    public ObjectRedisTemplate(RedisConnectionFactory connectionFactory) {

        setKeySerializer(new StringRedisSerializer());
        setValueSerializer(new GenericJackson2JsonRedisSerializer());

        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }


    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection,
                                                   boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

}
