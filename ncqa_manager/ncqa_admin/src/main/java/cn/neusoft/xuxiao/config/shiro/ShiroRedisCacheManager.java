package cn.neusoft.xuxiao.config.shiro;


import cn.neusoft.xuxiao.redis.JdkRedisTemplate;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRedisCacheManager implements CacheManager {

    @Autowired
    private JdkRedisTemplate redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroCache<K, V>(name, redisTemplate);
    }

    public JdkRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(JdkRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}