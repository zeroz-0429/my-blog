package com.zz.my.blog.web;

import com.zz.my.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName: ArchiveShowController
 * Description: <br/>
 * date: 2020/6/29 18:11
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }
}
