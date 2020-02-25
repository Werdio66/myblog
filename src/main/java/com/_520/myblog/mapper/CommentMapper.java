package com._520.myblog.mapper;

import com._520.myblog.entity.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-14 19:26:27
 */
public interface CommentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> queryAll(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<Comment> queryAllByBlogId(Long id);

    /**
     *  查询父评论
     */
    Comment queryParent(Long blogId, Long parentId);

    /**
     *  查询子评论
     */
    List<Comment> queryChird(Long blogId, Long pid);
}