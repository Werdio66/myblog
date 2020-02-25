package com._520.myblog.service.impl;

import com._520.myblog.entity.Tag;
import com._520.myblog.entity.Type;
import com._520.myblog.mapper.TagMapper;
import com._520.myblog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Tag)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-02-15 12:24:44
 */
@Service("tagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Tag queryById(Long id) {
        return this.tagMapper.queryById(id);
    }

    /**
     * 按条件查询数据
     *
     * @param tag 实例对象
     * @return 对象列表
     */
    @Override
    public List<Tag> queryAll(Tag tag) {
        return this.tagMapper.queryAll(tag);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Tag> queryAllByLimit(int offset, int limit) {
        return this.tagMapper.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Tag> queryAllSortByBlogSize(int offset, int limit) {
        List<Tag> tags = queryAllByLimit(offset, limit);
        tags.sort(((o1, o2) -> {
            return o2.getBlogs().size() - o1.getBlogs().size();
        }));
        return tags;
    }

    @Override
    public List<Tag> queryAllSortByBlogSize() {
        List<Tag> tags = queryAll(null);
        tags.sort(((o1, o2) -> {
            return o2.getBlogs().size() - o1.getBlogs().size();
        }));
        return tags;
    }

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag insert(Tag tag) {
        this.tagMapper.insert(tag);
        return tag;
    }

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag update(Tag tag) {
        this.tagMapper.update(tag);
        return this.queryById(tag.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tagMapper.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Tag> listTagPage() {
        List<Tag> tags = tagMapper.queryAll(null);
        return new PageInfo<>(tags);
    }
}