package com.zz.my.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName: AboutShowController
 * Description: <br/>
 * date: 2020/6/29 22:59
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Controller
public class AboutShowController {

    /**
     * 跳转到关于我页面
     * @return
     */
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
