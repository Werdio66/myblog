package com._520.myblog.service.impl;

import com._520.myblog.entity.Comment;
import com._520.myblog.mapper.CommentMapper;
import com._520.myblog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * (Comment)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-02-15 12:24:44
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Comment queryById(Long id) {
        return this.commentMapper.queryById(id);
    }

    /**
     * 按条件查询数据
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    @Override
    public List<Comment> queryAll(Comment comment) {
        return this.commentMapper.queryAll(comment);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Comment> queryAllByLimit(int offset, int limit) {
        return this.commentMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insert(Comment comment) {
        comment.setCreatTime(LocalDateTime.now());
        this.commentMapper.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentMapper.update(comment);
        return this.queryById(comment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.commentMapper.deleteById(id) > 0;
    }

    @Override
    public List<Comment> queryAllByBlogId(Long id) {

        // 查询指定博客对应的所有评论
        List<Comment> allComments = commentMapper.queryAllByBlogId(id);

        // 存放父评论
        List<Comment> parentComments = new ArrayList<>();

        Map<Long, Comment> map = new HashMap<>();

        Map<Long, Comment> allComms = new HashMap<>();

        // 找出父评论
        for (Comment comment : allComments){
            if (comment.getParentId() == -1){
                parentComments.add(comment);
                // 将父评论存入 map 中，便于设置子评论
                map.put(comment.getId(), comment);
            }

            allComms.put(comment.getId(), comment);
        }

        // 设置子评论
        for (Comment comment : allComments){

            if (comment.getParentId() != -1){
                // 取出当前评论的父评论
                Comment parent = map.get(comment.getParentId());
                // 将当前评论加到父评论的子评论中
                if (parent == null){
                    parent = allComms.get(comment.getParentId());
                }
                 parent.getChildComments().add(comment);
                // 为当前评论设置父评论
                comment.setParentName(parent.getNickName());
            }
        }

        for (int i = 0; i < parentComments.size(); i++) {
            merge(parentComments.get(i));
        }

//        parentComments.forEach(System.out::println);
        return parentComments;
    }

    private void merge(Comment parentComment) {
        if (parentComment.getChildComments().isEmpty()){
            return;
        }

        List<Comment> childComments = parentComment.getChildComments();
        for (int i = 0; i < childComments.size(); i++) {
            merge(childComments.get(i));
            parentComment.getChildComments().addAll(childComments.get(i).getChildComments());
        }
    }
}