package com.zz.my.blog.vo;

/**
 * ClassName: BlogQuery
 * Description: <br/>
 * date: 2020/6/26 12:22
 *
 * @author ZZ
 * @version 1.0.0
 * @since JDK 1.8
 */
public class BlogQuery {
    private String title;//标题
    private Long typeId;//分类id
    private boolean recommend;//是否推荐

    public BlogQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
