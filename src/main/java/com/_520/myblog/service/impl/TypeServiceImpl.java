package com._520.myblog.service.impl;

import com._520.myblog.entity.Type;
import com._520.myblog.mapper.TypeMapper;
import com._520.myblog.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * (Type)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-02-15 12:24:44
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeMapper typeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Type queryById(Long id) {
        return this.typeMapper.queryById(id);
    }

    /**
     * 按条件查询数据
     *
     * @param type 实例对象
     * @return 对象列表
     */
    @Override
    public List<Type> queryAll(Type type) {
        return this.typeMapper.queryAll(type);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Type> queryAllByLimit(int offset, int limit) {
        return this.typeMapper.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Type> queryAllSortByBlogSize(int offset, int limit){

        List<Type> types = queryAllByLimit(offset, limit);

        types.sort(((o1, o2) -> {
            return o2.getBlogs().size() - o1.getBlogs().size();
        }));

        return types;
    }

    @Override
    public List<Type> queryAllSortByBlogSize() {
        List<Type> types = queryAll(null);

        types.sort(((o1, o2) -> {
            return o2.getBlogs().size() - o1.getBlogs().size();
        }));

        return types;
    }

    /**
     * 新增数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type insert(Type type) {
        this.typeMapper.insert(type);
        return type;
    }

    /**
     * 修改数据
     *
     * @param type 实例对象
     * @return 实例对象
     */
    @Override
    public Type update(Type type) {
        this.typeMapper.update(type);
        return this.queryById(type.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.typeMapper.deleteById(id) > 0;
    }

    @Override
    public PageInfo<Type> listTypePage() {
        List<Type> types = typeMapper.queryAll(null);
        return new PageInfo<>(types);
    }
}