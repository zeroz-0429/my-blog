package com.zz.my.blog.dao;

import com.zz.my.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: BlogRepository
 * Description: JpaSpecificationExecutor封装了复杂查询和方法
 * date: 2020/6/25 23:28
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor<Blog> {

    /**
     * 查询推荐博客列表
     * @return
     */
    @Query("select b from Blog b where b.recommend =true")
    List<Blog> findTop(Pageable pageable);

    /**
     * 搜索
     * @param query
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    /*在归档模块展示博客*/
    /*获取博客年份并显示年份*/
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();
    /*将博客按年份分组*/
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1 and b.published=true")
    List<Blog> findByYear(String year);

    /*查询博客总数量，在归档页面显示，过滤掉草稿数量*/
    @Query("select count(b.id) from Blog b where b.published=true")
    Long countBlog();
}
