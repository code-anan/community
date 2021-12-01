package com.weilong.community.mapper;

import com.weilong.community.model.Comment;

public interface CommentExtMapper {
    void incCommentCount(Comment comment);
}
