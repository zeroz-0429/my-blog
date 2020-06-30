package com.zz.my.blog.dao;

import com.zz.my.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: UserDao
 * Description: <br/>
 * date: 2020/6/24 22:31
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
