package com._520.myblog.service;

import com._520.myblog.entity.Blog;
import com._520.myblog.po.Condition;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Blog)表服务接口
 *
 * @author Werdio丶
 * @since 2020-02-15 12:23:34
 */
public interface BlogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Blog queryById(Long id);

    /**
     *  通过ID查询单条数据,并且将内容转换成前端页面格式
     */
    Blog queryById2HTML(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Blog> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param blog 实例对象
     * @return 对象列表
     */
    List<Blog> queryAll(Blog blog);

    /**
     * 新增数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    Blog insert(Blog blog, Long[] tagIds);

    /**
     * 修改数据
     *
     * @param blog 实例对象
     * @return 实例对象
     */
    Blog update(Blog blog, Long[] tagIds);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     *  分页查询
     */
    PageInfo<Blog> listBlogPage(Condition condition);

    /**
     *  查询最新推荐的博客
     */
    List<Blog> selectRecommend(int offset, int limit);

    /**
     *  查询指定分类的博客
     */
    PageInfo<Blog> selectByTypeId(Long typeId);

    /**
     *  查询指定标签的博客
     */
    PageInfo<Blog> selectByTagId(Long id);
}