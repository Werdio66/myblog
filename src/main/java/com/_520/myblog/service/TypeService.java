package com._520.myblog.service;

import com._520.myblog.entity.Type;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Type)表服务接口
 *
 * @author Werdio丶
 * @since 2020-02-15 12:23:35
 */
public interface TypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Type queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Type> queryAllByLimit(int offset, int limit);

    /**
     *  按照博客数量排序
     */
    List<Type> queryAllSortByBlogSize(int offset, int limit);

    List<Type> queryAllSortByBlogSize();
    /**
     * 通过实体作为筛选条件查询
     *
     * @param type 实例对象
     * @return 对象列表
     */
    List<Type> queryAll(Type type);

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type insert(Type type);

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    Type update(Type type);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    PageInfo<Type> listTypePage();

    /**
     *  批量删除
     * @param str2Long
     */
    void deleteBanch(List<Long> str2Long);
}