package com.weilong.community.model;

public class Comment {
    private Integer id;

    private Integer parentid;

    private Integer type;

    private Integer commentauthor;

    private Long gmtcreate;

    private Long gmtmodify;

    private Integer likecount;

    private String content;

    private Integer commentcount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCommentauthor() {
        return commentauthor;
    }

    public void setCommentauthor(Integer commentauthor) {
        this.commentauthor = commentauthor;
    }

    public Long getGmtcreate() {
        return gmtcreate;
    }

    public void setGmtcreate(Long gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    public Long getGmtmodify() {
        return gmtmodify;
    }

    public void setGmtmodify(Long gmtmodify) {
        this.gmtmodify = gmtmodify;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }
}