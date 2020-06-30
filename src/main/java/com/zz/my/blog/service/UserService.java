package com.zz.my.blog.service;

import com.zz.my.blog.po.User;

/**
 * ClassName: UserService
 * Description: <br/>
 * date: 2020/6/24 22:26
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface UserService {
    /**
     * 根据用户名和密码登录
     * @param username
     * @param password
     * @return
     */
    User  checkUser(String username,String password);
}
