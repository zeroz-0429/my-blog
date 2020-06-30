package com.zz.my.blog.service;

import com.zz.my.blog.handler.NotFoundException;
import com.zz.my.blog.dao.TypeRepository;
import com.zz.my.blog.po.Blog;
import com.zz.my.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: TypeServiceImpl
 * Description: <br/>
 * date: 2020/6/25 9:45
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * 保存
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    /**
     * 通过id单个查询
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    /**
     * 通过名称查询
     * @param name
     * @return
     */
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    /**
     * 分页
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    /**
     * 查询分类列表
     * @return
     */
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    /**
     * 以分页放入方式查询某个分类下博客的的条数并排序
     * @param size
     * @return
     */
    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort =  Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }


    /**
     * 更新
     * @param id
     * @param type
     * @return
     */
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    /**
     * 删除
     * @param id
     */
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
