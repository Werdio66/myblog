package com._520.myblog.redis;

import com._520.myblog.entity.Blog;
import com._520.myblog.service.impl.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Werdio丶
 * @since 2021-02-07 11:20:52
 */
@Component
public class BlogCache {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BlogServiceImpl blogService;

    private final String zsetKey = "commentableBlog";

    public Integer incrViewsCount(String viewCount){
        int i = Math.toIntExact(redisTemplate.opsForValue().increment(viewCount));
        System.out.println(i);
        return i;
    }

    public void addBlog1(Blog blog){
        blog = blogService.queryById(1L);
        redisTemplate.opsForValue().set(blog.getId().toString(), blog);
    }
    public void getBlog1(String key){
        Blog blog = (Blog) redisTemplate.opsForList().leftPop(key);

        System.out.println(blog);
    }
    public void addBlog(Blog blog){
        redisTemplate.opsForHash().put(blog.getId().toString(), blog.getTitle(), blog);
    }

    public void getBlog(String key){
        Blog blog = (Blog) redisTemplate.opsForHash().get(key, "blog");
        System.out.println(blog);
    }

    public void zsetAddBlog(Long id){
        redisTemplate.opsForZSet().add(zsetKey, id.toString(), 0);
    }

    public void zsetIncr(Long id){
        redisTemplate.opsForZSet().incrementScore(zsetKey, id.toString(), 1);
        System.out.println(redisTemplate.opsForZSet().rangeByScore(zsetKey, 0, -1));
    }
    public Set<Long> zsetGetBlog(int size){
        return redisTemplate.opsForZSet().reverseRange(zsetKey, 0, size);
    }
}
