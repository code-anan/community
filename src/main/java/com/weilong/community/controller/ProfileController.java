package com.weilong.community.controller;

import com.weilong.community.dto.IDDto;
import com.weilong.community.dto.NotificationDto;
import com.weilong.community.dto.PaginationDto;
import com.weilong.community.enums.NotificationStatusEnum;
import com.weilong.community.mapper.NotificationMapper;
import com.weilong.community.model.Notification;
import com.weilong.community.model.NotificationExample;
import com.weilong.community.model.User;
import com.weilong.community.service.NotificationService;
import com.weilong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationMapper notificationMapper;
    //根据个人中心选择的具体路由动态选择
    @GetMapping(value = "/profile/{action}")
    public String profile(@PathVariable(name="action") String action, Model model, HttpServletRequest request,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size){
        User user= (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            PaginationDto paginationDto = questionService.getlistbyId(user.getId(), page, size);
            model.addAttribute("pagination",paginationDto);
        }else if("reply".equals(action)){
            List<NotificationDto> notificationList=notificationService.getUnreadNotification(user);
            model.addAttribute("notificationList",notificationList);
            model.addAttribute("section","reply");
            model.addAttribute("sectionName","回复我的问题");
        }
        return "profile";
    }

    @PostMapping("/readStatus")
    public void changeReadStatus(@RequestBody IDDto IDDto,HttpServletRequest request){
        System.out.println("我来到了这里哦");
        int id=IDDto.getId();
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer count = notificationService.getUnreadCount(user);
        session.setAttribute("unreadCount",count);

    }
}
