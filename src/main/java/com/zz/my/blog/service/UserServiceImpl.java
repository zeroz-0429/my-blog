package com.zz.my.blog.service;

import com.zz.my.blog.dao.UserRepository;
import com.zz.my.blog.po.User;
import com.zz.my.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Description: <br/>
 * date: 2020/6/24 22:28
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        //将密码进行MD5加密
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
