package com.zz.my.blog.service;

import com.zz.my.blog.handler.NotFoundException;
import com.zz.my.blog.dao.TagRepository;
import com.zz.my.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TypeServiceImpl
 * Description: <br/>
 * date: 2020/6/25 9:45
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * 保存
     * @param tag
     * @return
     */
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    /**
     * 分页
     * @param pageable
     * @return
     */
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    /**
     * 查询标签列表
     * @return
     */
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    /**
     * 以分页放入方式查询某个标签下博客的的条数并排序
     * @param size
     * @return
     */
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort =  Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    /**
     * 查询指定博客标签id集合
     * @param ids
     * @return
     */
    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }



    /**
     * 将字符串转换为一个数组list
     * @param ids
     * @return
     */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    /**
     * 更新
     * @param id
     * @param tag
     * @return
     */
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if (t == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    /**
     * 删除
     * @param id
     */
    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
