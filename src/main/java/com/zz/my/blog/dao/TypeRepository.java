package com.zz.my.blog.dao;

import com.zz.my.blog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ClassName: TypeRepository
 * Description: <br/>
 * date: 2020/6/25 9:52
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    /**
     * 通过名称获取分类
     * @param name
     * @return
     */
    Type findByName(String name);

    /**
     * 以分页放入方式查询某个分类下博客的的条数并排序
     * @param pageable
     * @return
     */
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
