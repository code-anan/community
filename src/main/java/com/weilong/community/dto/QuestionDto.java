package com.weilong.community.dto;

import com.weilong.community.model.User;

public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modify;
    private Integer author;
    private Integer comment_count;
    private Integer like_count;
    private Integer view_count;
    private String tag;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Long getGmt_modify() {
        return gmt_modify;
    }

    public void setGmt_modify(Long gmt_modify) {
        this.gmt_modify = gmt_modify;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Integer getView_count() {
        return view_count;
    }

    public void setView_count(Integer view_count) {
        this.view_count = view_count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
