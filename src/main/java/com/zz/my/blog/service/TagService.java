package com.zz.my.blog.service;

import com.zz.my.blog.po.Tag;
import com.zz.my.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ClassName: TypeService
 * Description: <br/>
 * date: 2020/6/25 9:38
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public interface TagService {

    /**
     * 保存
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 根据名称获取
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 分页
     * @param pageable
     * @return
     */
    Page<Tag> listTag(Pageable pageable);

    /**
     * 获取标签集合
     * @return
     */
    List<Tag> listTag();

    /**
     * 定义标签列表的大小
     * @param size
     * @return
     */
    List<Tag> listTagTop(Integer size);

    /**
     *
     * @param ids
     * @return
     */
    List<Tag> listTag(String ids);

    /**
     * 更新
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除
     * @param id
     */
    void deleteTag(Long id);
}
