package com.weilong.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Hello {
    @GetMapping("/hello")
    public String doSome(@RequestParam(name="name")String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
