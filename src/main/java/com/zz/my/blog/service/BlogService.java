package com.zz.my.blog.service;

import com.zz.my.blog.po.Blog;
import com.zz.my.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * ClassName: BlogService
 * Description: <br/>
 * date: 2020/6/25 23:23
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface BlogService {

    /**
     * 查询
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 获取和转换博客内容
     * @param id
     * @return
     */
    Blog getAndConvert(Long id);

    /**
     * 后台分页
     * @param pageable
     * @param blog
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
     * 前台分页
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Pageable pageable);

    /**
     * 根据标签id查询博客
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
     * 首页搜索
     * @param query
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(String query,Pageable pageable);

    /**
     * 前台推荐博客列表
     * @param size
     * @return
     */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
     * 查询博客归档列表
     * @return
     */
    Map<String,List<Blog>> archiveBlog();

    /**
     * 统计博客的数量
     * @return
     */
    Long countBlog();

    /**
     * 新增
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 更新
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id ,Blog blog);

    /**
     * 删除
     * @param id
     */
    void deleteBlog(Long id);
}
