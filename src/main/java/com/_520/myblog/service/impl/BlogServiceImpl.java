package com._520.myblog.service.impl;

import com._520.myblog.entity.Blog;
import com._520.myblog.mapper.BlogMapper;
import com._520.myblog.po.Condition;
import com._520.myblog.redis.BlogCache;
import com._520.myblog.service.BlogService;
import com._520.myblog.utils.MarkdownUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * (Blog)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-02-15 12:24:43
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Autowired
    private BlogCache blogCache;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Blog queryById(Long id) {
        return this.blogMapper.queryById(id);
    }

    @Transactional
    @ReadOnlyProperty
    @Override
    public Blog queryById2HTML(Long id) {
        Blog blog = blogMapper.queryById(id);
        // 将文本内容转换为前端页面显示
        String content = MarkdownUtils.markdownToHtmlExtensions(blog.getContent());
        blog.setContent(content);

        Integer viewsCount = blog.getViewsCount();
        AtomicInteger atomicInteger = new AtomicInteger(viewsCount);
        blog.setViewsCount(atomicInteger.incrementAndGet());
        // 将 redis 中的数量加一
        blogCache.zsetIncr(blog.getId());
        blogMapper.update(blog);

        return blog;
    }

    /**
     * 按条件查询数据
     *
     * @param blog 实例对象
     * @return 对象列表
     */
    @Override
    public List<Blog> queryAll(Blog blog) {
        return this.blogMapper.queryAll(blog);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Blog> queryAllByLimit(int offset, int limit) {
        return this.blogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public Blog insert(Blog blog, Long[] tagIds) {
        blog.setViewsCount(0);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:ss:mm");
        blog.setCreatTime(localDate);
        blog.setUpdateTime(localDate);
        this.blogMapper.insert(blog);
        Long blogId = blogMapper.getIdByTitle(blog.getTitle());
        // 记录推荐
        blogCache.zsetAddBlog(blogId);

        // 维护中间表
        for (Long tagId : tagIds){
            blogMapper.protectBlogAndTags(blogId, tagId);
        }

        return blog;
    }

    /**
     * 修改数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public Blog update(Blog blog, Long[] tagIds) {

        LocalDate localDate = LocalDate.now();
        blog.setUpdateTime(localDate);

        this.blogMapper.update(blog);

        // 维护中间表

        // 删除原来的标签
        blogMapper.deleteBlogAndTagsById(blog.getId());
        // 添加新的标签
        for (Long tagId : tagIds){
            blogMapper.protectBlogAndTags(blog.getId(), tagId);
        }

        return this.queryById(blog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.blogMapper.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Blog> listBlogPage(Condition condition) {

        List<Blog> blogs = null;
        if (condition == null){
            blogs = blogMapper.queryAll(null);
        }else {
            blogs = blogMapper.queryAllByCondition(condition);
        }

        return new PageInfo<>(blogs);
    }

    @Override
    public List<Blog> selectRecommend(int offset, int limit) {
        return blogMapper.queryAllSortByTime(offset, limit, true);
    }

    @Override
    public PageInfo<Blog> selectByTypeId(Long typeId) {
        List<Blog> blogs = blogMapper.selectByTypeId(typeId);
        return new PageInfo<>(blogs);
    }

    @Override
    public PageInfo<Blog> selectByTagId(Long id) {
        List<Blog> blogs = blogMapper.selectByTagId(id);
        return new PageInfo<>(blogs);
    }

    public Map<String, List<Blog>> queryByArchive(){
        List<String> yearList = blogMapper.queryYear();
        Map<String, List<Blog>> blogMap = new LinkedHashMap<>();
        log.info("yearList = {}", yearList);
        for (String year : yearList) {
            List<Blog> blogList = blogMapper.queryByYear(year);
            log.info("blogList = {}", blogList);
            blogMap.put(year, blogList);
        }
        return blogMap;
    }

    @Override
    public int queryCount() {
        return blogMapper.queryCount();
    }

    @Override
    public List<Blog> selectByIds(Set<Long> ids) {
        return blogMapper.selectByIds(ids);
    }
}