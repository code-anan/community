package com.weilong.community.controller;

import com.weilong.community.dto.PaginationDto;
import com.weilong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    //项目首页
    @GetMapping("/")
    public String toHome(Model model,
                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                         @RequestParam(name = "size", defaultValue = "5") Integer size,
                         @RequestParam(value = "search", required = false) String search) {
        PaginationDto pagination = questionService.getlist(search,page, size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        return "index";
    }

}
