package com.zz.my.blog.dao;

import com.zz.my.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ClassName: CommentRepository
 * Description: <br/>
 * date: 2020/6/28 23:23
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    /**
     * 根据博客id获取评论并排序
     * @param blogId
     * @param sort
     * @return
     */
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
