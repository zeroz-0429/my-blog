package com.zz.my.blog.service;

import com.zz.my.blog.handler.NotFoundException;
import com.zz.my.blog.dao.BlogRepository;
import com.zz.my.blog.po.Blog;
import com.zz.my.blog.po.Type;
import com.zz.my.blog.util.MarkdownUtils;
import com.zz.my.blog.util.MyBeanUtils;
import com.zz.my.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * ClassName: BlogServiceImpl
 * Description: 处理博客业物逻辑
 * date: 2020/6/25 23:27
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 根据id查询单个数据
     * @param id
     * @return
     */
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    /**
     * 获取和转换博客内容为Markdown格式
     * @param id
     * @return
     */
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return b;
    }

    /**
     * 后台博客分页查询
     * @param pageable
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            /**
             *第一个参数Root指定查询的对象，
             *第二个参数CriteriaQuery为查询的条件容器，
             *第三个参数criteriaBuilder设置具体某一条件的表达式
             */
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //predicates.add()方法，将查询条件加入一个list中；cb.方法第一个参数指定查询对应的实体类，第二个参数指定对应前端所传递的参数
                //根据博客标题查询
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                //根据博客类型查询
                if (blog.getTypeId() != null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                //根据博客是否被推荐查询
                if (blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    /**
     * 前台分页
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join=root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    /**
     * 首页搜索
     * @param query
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    /**
     * 前台博客推荐列表
     * @param size
     * @return
     */
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    /**
     * 查询博客归档列表
     * @return
     */
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        /*为了保证归档年份按顺序显示，使用LinkedHashMap存放*/
        //Map<String, List<Blog>> map = new HashMap<>();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    /**
     * 统计博客的数量
     * @return
     */
    @Override
    public Long countBlog() {
        return blogRepository.countBlog();
    }

    /**
     * 博客的保存
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        //如果博客id不存在
        if (blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }
        //id存在
        else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    /**
     * 博客更新
     * @param id
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog updateBlog(Long id,Blog blog) {
        Blog b = blogRepository.getOne(id);
        //判断id是否存在
        if (b == null){
            throw new NotFoundException("该博客不存在");
        }
        //将blog的值给b,MyBeanUtils工具类过滤掉属性值为空的属性,这样当属性值为空则保留原有值，执行更新操作
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);

    }

    /**
     * 博客删除
     * @param id
     */
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
