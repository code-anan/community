package com.weilong.community.controller;

import com.weilong.community.dto.PaginationDto;
import com.weilong.community.model.User;
import com.weilong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
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
        }else if("reply".equals(action)){
            model.addAttribute("section","reply");
            model.addAttribute("sectionName","回复我的问题");
        }
        PaginationDto paginationDto = questionService.getlistbyId(user.getId(), page, size);
        model.addAttribute("pagination",paginationDto);
        return "profile";
    }
}
