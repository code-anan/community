package com.weilong.community.controller;

import com.weilong.community.dto.CommentCreateDTO;
import com.weilong.community.dto.CommentDTO;
import com.weilong.community.dto.ResultDTO;
import com.weilong.community.enums.CommentTypeEnum;
import com.weilong.community.exception.BusinessException;
import com.weilong.community.model.Comment;
import com.weilong.community.model.User;
import com.weilong.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    //新增评论（一级评论和二级评论）
    @ResponseBody
    @PostMapping("/comment")
    public Object addComment(@RequestBody CommentCreateDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return new ResultDTO(2002,"未登陆，请先进行登陆哦");
        }
        if(StringUtils.isBlank(commentDTO.getContent())){
            return new ResultDTO(2004, "回复内容不能为空！");
        }
        Comment record = new Comment();
        record.setCommentauthor(user.getId());
        record.setContent(commentDTO.getContent());
        record.setType(commentDTO.getType());
        record.setGmtcreate(System.currentTimeMillis());
        record.setGmtmodify(System.currentTimeMillis());
        record.setParentid(commentDTO.getParentid());
        commentService.insert(record,commentDTO.getType());
        return new ResultDTO(200,"添加成功");
    }



    //获取二级评论数据
    @ResponseBody
    @GetMapping("/comment/{id}")
    public List<CommentDTO> getCommentsComments(@PathVariable(name="id") Integer id){
        List<CommentDTO> commentscomments=commentService.getCommentsListById(id, CommentTypeEnum.COMMENT.getType());
        return commentscomments;
    }
}
