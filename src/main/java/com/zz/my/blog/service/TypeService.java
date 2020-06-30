package com.zz.my.blog.service;

import com.zz.my.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ClassName: TypeService
 * Description: <br/>
 * date: 2020/6/25 9:38
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface TypeService {

    /**
     * 保存
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 根据分类名称获取
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 分页
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 获取分类集合
     * @return
     */
    List<Type> listType();

    /**
     * 定义分类列表的大小
     * @param size
     * @return
     */
    List<Type> listTypeTop(Integer size);
    /**
     * 更新
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id,Type type);

    /**
     * 删除
     * @param id
     */
    void deleteType(Long id);
}
