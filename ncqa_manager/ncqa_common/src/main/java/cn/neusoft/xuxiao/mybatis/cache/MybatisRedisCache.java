package cn.neusoft.xuxiao.mybatis.cache;

import cn.neusoft.xuxiao.SpringContextHolder;
import cn.neusoft.xuxiao.redis.ObjectRedisTemplate;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class MybatisRedisCache implements Cache {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private ObjectRedisTemplate redisTemplate;

    private String id;

    public MybatisRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        logger.info("Redis Cache id " + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (value != null && getRedisTemplate() != null) {
            if(key.toString().indexOf("page")!=-1||key.toString().indexOf("Page")!=-1){
                return;
            }
            getRedisTemplate().opsForValue().set(key.toString(), value, 1, TimeUnit.HOURS);
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            if (key != null && getRedisTemplate() != null) {
                Object obj = getRedisTemplate().opsForValue().get(key.toString());
                return obj;
            }
        } catch (Exception e) {
            logger.error("redis ");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try {
            if (key != null && getRedisTemplate() != null) {
                getRedisTemplate().delete(key.toString());
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void clear() {
        logger.debug("清空缓存");
        if (getRedisTemplate() != null) {
            try {
                Set<String> keys = getRedisTemplate().keys("*:" + this.id + "*");
                if (!CollectionUtils.isEmpty(keys)) {
                    getRedisTemplate().delete(keys);
                }
            } catch (Exception e) {
            }
        }

    }

    @Override
    public int getSize() {
        if (getRedisTemplate() != null) {
            Long size = (Long) getRedisTemplate().execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.dbSize();
                }
            });
            return size.intValue();
        }

        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }


    public ObjectRedisTemplate getRedisTemplate() {
        if (null == redisTemplate) {
            redisTemplate = SpringContextHolder.getBean(ObjectRedisTemplate.class);
        }
        return redisTemplate;
    }
}
