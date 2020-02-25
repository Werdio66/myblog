package com._520.myblog.service;

import com._520.myblog.entity.Tag;
import com._520.myblog.entity.Type;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Tag)表服务接口
 *
 * @author Werdio丶
 * @since 2020-02-15 12:23:35
 */
public interface TagService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tag queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tag> queryAllByLimit(int offset, int limit);

    /**
     *  按照博客的数量排序
     */
    List<Tag> queryAllSortByBlogSize(int offset, int limit);

    List<Tag> queryAllSortByBlogSize();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param tag 实例对象
     * @return 对象列表
     */
    List<Tag> queryAll(Tag tag);

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag insert(Tag tag);

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    Tag update(Tag tag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    PageInfo<Tag> listTagPage();
}