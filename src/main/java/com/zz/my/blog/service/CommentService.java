package com.zz.my.blog.service;

import com.zz.my.blog.po.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CommentService
 * Description: <br/>
 * date: 2020/6/28 23:19
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */

public interface CommentService {

    /**
     * 根据博客id获取评论列表
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment
     * @return
     */
    Comment saveComment(Comment comment);
}
