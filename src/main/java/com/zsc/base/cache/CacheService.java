package com.zsc.base.cache;
import com.zsc.base.utils.JsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 文件对象
 * 功能说明：<br>
 * 模块名称：<br>
 * 功能描述：<br>
 * 文件名称: <br>
 * 系统名称：<br>
 * 软件著作权：icelove 版权所有<br>
 * 开发人员：icelove <br>
 * 开发时间：2019/2/18 17:24<br>
 * 系统版本：1.0.0<br>
 */
@Service
public class CacheService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public <T> void put(String key, T obj) {
        redisTemplate.opsForValue().set(key, JsonUtils.toString(obj));
    }

    public <T> void put(String key, T obj, int timeout) {
        put(key, obj, timeout, TimeUnit.MINUTES);
    }

    public <T> void put(String key, T obj, int timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, JsonUtils.toString(obj), timeout, unit);
    }

    public <T> T get(String key, Class<T> cls) {
        return JsonUtils.toJavaObject(JsonUtils.asJSONObject(redisTemplate.opsForValue().get(key)), cls);
    }

    public <T> List<T> getList(String key, Class<T> cls) {
        return JsonUtils.toJavaList(JsonUtils.asJSONArray(redisTemplate.opsForValue().get(key)), cls);
    }

    public <T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier) {
        T t = get(key, cls);
        if (null == t) {
            t = supplier.get();
            if (null != t) put(key, t);
        }
        return t;
    }

    public <T> T putIfAbsent(String key, Class<T> cls, Supplier<T> supplier, int timeout) {
        T t = get(key, cls);
        if (null == t) {
            t = supplier.get();
            if (null != t) put(key, t, timeout);
        }
        return t;
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public boolean expire(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.MINUTES);
    }

    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void put(String key, String value, int timeout) {
        put(key, value, timeout, TimeUnit.MINUTES);
    }

    public void put(String key, String value, int timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void putHash(String key, Map<Object, Object> m) {
        redisTemplate.opsForHash().putAll(key, m);
    }

    public Map<Object, Object> getHash(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            return null;
        }

    }
}
