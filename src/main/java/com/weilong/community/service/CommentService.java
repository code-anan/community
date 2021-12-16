package com.weilong.community.service;

import com.weilong.community.dto.CommentDTO;
import com.weilong.community.enums.CommentTypeEnum;
import com.weilong.community.enums.NotificationEnum;
import com.weilong.community.enums.NotificationStatusEnum;
import com.weilong.community.exception.BusinessException;
import com.weilong.community.mapper.*;
import com.weilong.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private NotificationMapper notificationMapper;
    @Transactional
    public void insert(Comment record, Integer type) {
        //异常的判断处理
        if (record.getParentid() == null || record.getParentid() == 0) {
            throw new BusinessException(2001, "回复的问题不存在！");
        } else if (record.getType() != 1 && record.getType() != 2) {
            throw new BusinessException(2003, "回复类型不合法！");
        } else if (record.getContent().isEmpty()) {
            throw new BusinessException(2004, "回复内容不能为空！");
        } else if ( type==CommentTypeEnum.QUESTION.getType()) {
            //问题的回复
            Question question = questionMapper.selectByPrimaryKey(record.getParentid());
            if (question == null) {
                throw new BusinessException(2001, "回复的问题不存在！");
            }
            //增加评论
            record.setCommentcount(0);
            record.setLikecount(0);
            commentMapper.insert(record);
            //问题评论数增加
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            //提醒对象的创建
            Notification notification = new Notification();
            notification.setGmtcreate(System.currentTimeMillis());
            notification.setType(NotificationEnum.NOTIFICATION_QUESTION.getType());
            notification.setOuterid(record.getParentid());
            notification.setNotifier(record.getCommentauthor());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(question.getAuthor());
            notificationMapper.insert(notification);
        } else if(type==CommentTypeEnum.COMMENT.getType()){
            //评论的回复
            Comment comment = commentMapper.selectByPrimaryKey(record.getParentid());
            if (comment == null) {
                throw new BusinessException(2005, "回复的评论不存在！");
            }
            //评论数增加
            commentMapper.insert(record);
            comment.setCommentcount(1);
            commentExtMapper.incCommentCount(comment);
            //提醒对象创建
            Notification notification = new Notification();
            notification.setGmtcreate(System.currentTimeMillis());
            notification.setType(NotificationEnum.NOTIFICATION_COMMENT.getType());
            notification.setOuterid(record.getParentid());
            notification.setNotifier(record.getCommentauthor());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setReceiver(comment.getCommentauthor());
            notificationMapper.insert(notification);
        }

    }
    //根据问题id或者回复id获取评论
    public List<CommentDTO> getCommentsListById(Integer id, Integer type) {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentidEqualTo(id).andTypeEqualTo(type);
        example.setOrderByClause("GMTCREATE desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment:comments){
            CommentDTO commentDTO=new CommentDTO();
            User user = userMapper.selectByPrimaryKey(comment.getCommentauthor());
            BeanUtils.copyProperties(comment,commentDTO);
            commentDTO.setUser(user);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
