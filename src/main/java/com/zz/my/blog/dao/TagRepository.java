package com.zz.my.blog.dao;

import com.zz.my.blog.po.Tag;
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
public interface TagRepository extends JpaRepository<Tag,Long> {

    /**
     * 通过名称获取标签
     * @param name
     * @return
     */
    Tag findByName(String name);

    /**
     * 以分页放入方式查询某个标签下博客的的条数并排序
     * @param pageable
     * @return
     */
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
