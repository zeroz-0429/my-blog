package com.zz.my.blog.web.admin;

import com.zz.my.blog.po.Blog;
import com.zz.my.blog.po.User;
import com.zz.my.blog.service.BlogService;
import com.zz.my.blog.service.TagService;
import com.zz.my.blog.service.TypeService;
import com.zz.my.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * ClassName: BlogController
 * Description: <br/>
 * date: 2020/6/24 23:36
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "/admin/blogs-input";
    private static final String LIST = "/admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 跳转到博客列表页面，并通过更新时间倒序分页获取博客列表
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 3,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        //获取分类下拉列表中的分类数据
        model.addAttribute("types",typeService.listType());
        //获取标签列表中的数据
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

    /**
     * 搜索，并通过更新时间倒序分页获取博客列表
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 3,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "/admin/blogs :: blogList";
    }

    /**
     * 跳转到博客发布页面
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAngTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    /**
     * 跳转到博客编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        setTypeAngTag(model);
        Blog blog = blogService.getBlog(id);
        //初始化标签
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    /**
     * 设置类别和标签的下拉选项
     * @param model
     */
    private void setTypeAngTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    /**
     * 保存博客
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        //id为空，执行新建操作
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        }
        //id有值，执行更新操作
        else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if(b == null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

    /**
     * 删除博客
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

}
