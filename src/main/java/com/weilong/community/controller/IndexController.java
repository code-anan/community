package com.weilong.community.controller;

import com.weilong.community.dto.PaginationDto;
import com.weilong.community.model.User;
import com.weilong.community.service.NotificationService;
import com.weilong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;
    //项目首页
    @GetMapping("/")
    public String toHome(Model model,
                         @RequestParam(name="page",defaultValue = "1") Integer page,
                         @RequestParam(name="size",defaultValue = "10") Integer size
                          ){
        PaginationDto pagination = questionService.getlist(page, size);
        model.addAttribute("pagination",pagination);
        return "index";
    }

}
