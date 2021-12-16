package com.weilong.community.service;

import com.weilong.community.dto.NotificationDto;
import com.weilong.community.enums.NotificationEnum;
import com.weilong.community.enums.NotificationStatusEnum;
import com.weilong.community.mapper.CommentMapper;
import com.weilong.community.mapper.NotificationMapper;
import com.weilong.community.mapper.QuestionMapper;
import com.weilong.community.mapper.UserMapper;
import com.weilong.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    public List<NotificationDto> getUnreadNotification(User user) {
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(user.getId());
        example.setOrderByClause("GMTCREATE desc");
        example.setOrderByClause("STATUS");
        List<Notification> notifications = notificationMapper.selectByExample(example);
        ArrayList<NotificationDto> resultDto = new ArrayList<>();
        for(Notification notification:notifications){
            NotificationDto notificationDto = new NotificationDto();
            User notifier = userMapper.selectByPrimaryKey(notification.getNotifier());
            //发起人
            notificationDto.setNotifier(notifier);
            //回复了问题还是回复了评论
            notificationDto.setType(NotificationEnum.getNameByTypeId(notification.getType()));
            //问题的标题（用来点击跳转到哪个问题上）
            if(notification.getType()==NotificationEnum.NOTIFICATION_QUESTION.getType()){
                Question question = questionMapper.selectByPrimaryKey(notification.getOuterid());
                notificationDto.setOuterTitle(question.getTitle());
                notificationDto.setOuterId(question.getId());
            }else{
                Comment comment = commentMapper.selectByPrimaryKey(notification.getOuterid());
                notificationDto.setOuterTitle(comment.getContent());
                if(comment.getType()==1){
                    notificationDto.setOuterId(comment.getParentid());
                }else{
                    Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentid());
                    Question question = questionMapper.selectByPrimaryKey(comment1.getParentid());
                    notificationDto.setOuterId(question.getId());
                }

            }
            //每个Dto的创建时间
            notificationDto.setGmtCreate(notification.getGmtcreate());
            //Dto的状态 未读和已读
            notificationDto.setStatus(notification.getStatus());
            ///notification的ID方便改对应的阅读状态
            notificationDto.setNotificationId(notification.getId());

            resultDto.add(notificationDto);
        }
        return resultDto;
    }
    //获取每个提醒的状态
    public Integer getUnreadCount(User user) {
        NotificationExample example = new NotificationExample();
        example.createCriteria().andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        example.createCriteria().andReceiverEqualTo(user.getId());
        List<Notification> notifications = notificationMapper.selectByExample(example);
        return notifications.size();
    }
}
