package com._520.myblog.mapper;

import com._520.myblog.entity.Blog;
import com._520.myblog.po.Condition;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Set;

/**
 * (Blog)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-14 19:26:25
 */
public interface BlogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Blog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Blog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


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
     * @return 影响行数
     */
    int insert(Blog blog);

    /**
     * 修改数据
     *
     * @param blog 实例对象
     * @return 影响行数
     */
    int update(Blog blog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     *  根据条件查询
     * @param condition     查询条件
     * @return              符合条件的所有博客
     */
    List<Blog> queryAllByCondition(Condition condition);

    Long getIdByTitle(String title);

    void protectBlogAndTags(@Param("blogId") Long blogId, @Param("tagId") Long tagId);

    void deleteBlogAndTagsById(Long id);

    List<Blog> queryAllSortByTime(@Param("offset") int offset, @Param("limit")int limit, @Param("commentabled")boolean commentabled);

    List<Blog> selectByTypeId(Long id);

    List<Blog> selectByTagId(Long id);

    List<String> queryYear();

    List<Blog> queryByYear(@Param("year") String year);

    int queryCount();

    List<Blog> selectByIds(@Param("ids") Set<Long> ids);
}