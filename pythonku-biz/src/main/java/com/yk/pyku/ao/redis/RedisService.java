package com.yk.pyku.ao.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Description redis
 * @Author yangkang
 * @Date 2019/7/15 17:48
 * @Version 1.0
 **/
@Component
public class RedisService {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
    /**
     * 注入redisTemplate bean
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置失效时间
     *
     * @param key  欲设置失效时间的键
     * @param time 失效时间，单位为秒
     */
    public void expire(String key, long time) {
        try {
            if (key == null) {
                logger.error("设置过期时间出错: Key值为null");
            } else if (time <= 0) {
                logger.error("设置过期时间出错: 过期时间必须大于0");
            } else {
                this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            logger.error("设置过期时间出错:" + key, e);
        }
    }

    /**
     * 查询某个键的失效时间
     *
     * @param key 欲查询失效时间的键
     * @return expire 返回指定键的失效时间
     */
    public long getExpireTime(String key) {
        long expire = 0;
        try {
            if (key == null) {
                logger.error("获取过期时间出差: Key值为null");
            } else {
                Object object = redisTemplate.getExpire(key);
                expire = object != null ? (long) object : 0;
            }
        } catch (Exception e) {
            logger.error("获取过期时间出错:" + key, e);
        }
        return expire;
    }

    /**
     * 查询键是否存在
     *
     * @param key 欲查询的键
     * @return 返回是否存在
     */
    public boolean hasKey(String key) {
        boolean has = false;
        try {
            if (key != null) {
                Object object = this.redisTemplate.hasKey(key);
                has = object != null && (boolean) object;
            } else {
                logger.error("查询键是否存在出错: Key值为null");
            }
        } catch (Exception e) {
            logger.error("查询键是否存在出错:" + key, e);
        }
        return has;
    }

    /**
     * 删除数据
     *
     * @param keys 欲删除键的数组
     */
    public void delete(String... keys) {
        try {
            if (keys != null && keys.length > 0) {
                if (keys.length == 1) {
                    this.redisTemplate.delete(keys[0]);
                }
                if (keys.length > 1) {
                    this.redisTemplate.delete(CollectionUtils.arrayToList(keys));
                }
            } else {
                logger.error("删除键出错: 键为空");
            }
        } catch (Exception e) {
            logger.error("删除键出错:" + Arrays.toString(keys), e);
        }
    }

    /**
     * 获取普通数据
     *
     * @param key 数据的键
     * @return Object
     */
    public Object get(String key) {
        Object object = null;
        try {
            if (key != null) {
                object = this.redisTemplate.opsForValue().get(key);
            } else {
                logger.error("获取值出错: 键为空");
            }
        } catch (Exception e) {
            logger.error("获取值出错:" + key, e);
        }
        return object;
    }

    /**
     * 插入普通数据
     *
     * @param key   数据的键
     * @param value 数据的值
     */
    public boolean set(String key, Object value) {
        boolean setResult = false;
        try {
            if (key == null) {
                logger.error("插入值出错: 键为空");
            } else if (value == null) {
                logger.error("插入值出错: 值为空");
            } else {
                this.redisTemplate.opsForValue().set(key, value);
                setResult = true;
            }
        } catch (Exception e) {
            logger.error("插入值出错:" + key, e);
        }
        return setResult;
    }

    /**
     * 插入普通数据的同时设置过期时间
     *
     * @param key   数据的键
     * @param value 数据的值
     * @param time  数据的过期时间
     */
    public boolean set(String key, Object value, long time) {
        boolean setResult = false;
        try {
            if (key == null) {
                logger.error("插入值出错: 键为空");
            } else if (value == null) {
                logger.error("插入值出错: 值为空");
            } else if (time <= 0) {
                logger.error("插入值出错: 有效期小于0");
            } else {
                this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                setResult = true;
            }
        } catch (Exception e) {
            logger.error("插入值出错:" + key, e);
        }
        return setResult;
    }

    /**
     * hget
     *
     * @param key  Redis的键
     * @param item 哈希的键
     */
    public Object hget(String key, String item) {
        Object object = null;
        try {
            if (key == null) {
                logger.error("获取哈希出错: 键为空");
            } else if (item == null) {
                logger.error("获取哈希出错: 项为空");
            } else {
                object = this.redisTemplate.opsForHash().get(key, item);
            }
        } catch (Exception e) {
            logger.error("获取哈希出错: Key:" + key + ", Item: " + item, e);
        }
        return object;
    }


    public Map<Object, Object> hmget(String key) {
        Map<Object, Object> map = null;
        try {
            if (key == null) {
                logger.error("获取哈希出错: 键为空");
            } else {
                map = this.redisTemplate.opsForHash().entries(key);
            }
        } catch (Exception e) {
            logger.error("获取哈希出错: Key:" + key, e);
        }
        return map;
    }

    public boolean hmset(String key, Map<Object, Object> map) {
        boolean setResult = false;
        try {
            if (key == null) {
                logger.error("存储哈希出错: 键为空");
            } else if (map == null) {
                logger.error("存储哈希出错: 值为空");
            } else {
                this.redisTemplate.opsForHash().putAll(key, map);
                setResult = true;
            }
        } catch (Exception e) {
            logger.error("存储哈希出错: Key:" + key, e);
        }
        return setResult;
    }

    public boolean hset(String key, String item, Object value) {
        boolean setResult = false;
        try {
            if (key == null) {
                logger.error("存储哈希出错: 键为空");
            } else if (item == null) {
                logger.error("存储哈希出错: 项为空");
            } else if (value == null) {
                logger.error("存储哈希出错: 值为空");
            } else {
                this.redisTemplate.opsForHash().put(key, item, value);
                setResult = true;
            }
        } catch (Exception e) {
            logger.error("设置哈希出错: Key:" + key, e);
        }
        return setResult;
    }

    public void hdel(String key, Object... item) {
        try {
            if (key == null) {
                logger.error("删除哈希出错: 键为空");
            } else if (item == null) {
                logger.error("删除哈希出错: 项为空");
            } else {
                this.redisTemplate.opsForHash().delete(key, item);
            }
        } catch (Exception e) {
            logger.error("删除哈希出错: Key:" + key, e);
        }
    }

    public boolean hHasKey(String key, String item) {
        boolean has = false;
        try {
            if (key == null) {
                logger.error("查找哈希出错: 键为空");
            } else if (item == null) {
                logger.error("查找哈希出错: 项为空");
            } else {
                has = this.redisTemplate.opsForHash().hasKey(key, item);
            }
        } catch (Exception e) {
            logger.error("查找哈希出错: Key:" + key, e);
        }
        return has;
    }

    public Set<Object> sGet(String key) {
        Set<Object> set = null;
        try {
            if (key == null) {
                logger.error("查询Set出错: 键为空");
            } else {
                set = this.redisTemplate.opsForSet().members(key);
            }
        } catch (Exception e) {
            logger.error("查询Set出错: Key:" + key);
        }
        return set;
    }

    public long sSet(String key, Object... values) {
        long successNum = 0;
        try {
            if (key == null) {
                logger.error("存储Set出错: 键为空");
            } else if (values == null) {
                logger.error("存储Set出错: 值为空");
            } else {
                Object object = this.redisTemplate.opsForSet().add(key, values);
                successNum = object != null ? (long) object : 0;
            }
        } catch (Exception e) {
            logger.error("存储Set出错: Key:" + key, e);
        }
        return successNum;
    }

    public long sGetSize(String key) {
        long size = 0;
        try {
            if (key == null) {
                logger.error("获取Set尺寸出错: 键为空");
            } else {
                Object object = this.redisTemplate.opsForSet().size(key);
                size = object != null ? (long) object : 0;
            }
        } catch (Exception e) {
            logger.error("获取Set尺寸出错: Key:" + key, e);
        }
        return size;
    }

    public long setRemove(String key, Object... values) {
        long successNum = 0;
        try {
            if (key == null) {
                logger.error("删除Set出错: 键为空");
            } else if (values == null) {
                logger.error("删除Set出错: 值为空");
            } else {
                Object object = this.redisTemplate.opsForSet().remove(key, values);
                successNum = object != null ? (long) object : 0;
            }
        } catch (Exception e) {
            logger.error("删除Set出错: Key:" + key, e);
        }
        return successNum;
    }

    /**
     * 查询List的值，范围为(0, -1)代表所有
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @return 返回查询到的List
     */
    public List<Object> lGet(String key, long start, long end) {
        List<Object> list = null;
        try {
            if (key == null) {
                logger.error("查询List出错: 键为空");
            } else {
                list = this.redisTemplate.opsForList().range(key, start, end);
            }
        } catch (Exception e) {
            logger.error("查询List出错: Key:" + key, e);
        }
        return list;
    }

    public long lGetListSize(String key) {
        long size = 0;
        try {
            if (key == null) {
                logger.error("获取List尺寸出错: 键为空");
            } else {
                Object object = this.redisTemplate.opsForList().size(key);
                size = object != null ? (long) object : 0;
            }
        } catch (Exception e) {
            logger.error("获取List尺寸出错: Key:" + key, e);
        }
        return size;
    }

    /**
     * 通过索引查询List的中数据
     *
     * @param key   键
     * @param index 索引，当索引大于等于0时，0代表表头，1代表第二个元素；当索引小于0时，-1代表表尾，-2代表倒数第二个元素
     * @return 值
     */
    public Object lGetByIndex(String key, long index) {
        Object object = null;
        try {
            if (key == null) {
                logger.error("通过Index获取List中数据出错: 键为空");
            } else {
                object = this.redisTemplate.opsForList().index(key, index);
            }
        } catch (Exception e) {
            logger.error("通过Index获取List中数据出错: Key:" + key, e);
        }
        return object;
    }

    public boolean lSet(String key, Object value) {
        boolean setResult = false;
        try {
            if (key == null) {
                logger.error("写入List出错: 键为空");
            } else if (value == null) {
                logger.error("写入List出错: 值为空");
            } else {
                this.redisTemplate.opsForList().rightPush(key, value);
                setResult = true;
            }
        } catch (Exception e) {
            logger.error("写入List出错: Key:" + key, e);
        }
        return setResult;
    }

    public boolean lSet(String key, List<Object> values) {
        boolean setResult = false;
        try {
            if (key == null) {
                logger.error("写入List出错: 键为空");
            } else if (values == null) {
                logger.error("写入List出错: 值为空");
            } else {
                this.redisTemplate.opsForList().rightPushAll(key, values);
                setResult = true;
            }
        } catch (Exception e) {
            logger.error("写入List出错: Key:" + key, e);
        }
        return setResult;
    }

    public boolean lUpdateByIndex(String key, long index, Object value) {
        boolean setResult = false;
        try {
            if (key == null) {
                logger.error("更新List出错: 键为空");
            } else {
                this.redisTemplate.opsForList().set(key, index, value);
                setResult = true;
            }
        } catch (Exception e) {
            logger.error("更新List出错: Key:" + key, e);
        }
        return setResult;
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        long successNum = 0;
        try {
            if (key == null) {
                logger.error("移除List出错: 键为空");
            } else if (value == null) {
                logger.error("移除List出错: 值为空");
            } else {
                Object object = this.redisTemplate.opsForList().remove(key, count, value);
                successNum = object != null ? (long) object : 0;
            }
        } catch (Exception e) {
            logger.error("移除List出错: Key:" + key, e);
        }
        return successNum;
    }
}
