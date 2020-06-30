package com.zz.my.blog.web.admin;

import com.zz.my.blog.po.User;
import com.zz.my.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * ClassName: LoginController
 * Description: <br/>
 * date: 2020/6/24 22:38
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        //判断用户是否存在
        //存在，保存用户信息到session
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "/admin/index";
        }
        //不存在，保存错误提示信息
        else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //清除session
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
