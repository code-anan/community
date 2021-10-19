package com.weilong.community.controller;

import com.weilong.community.mapper.QuestionMapper;
import com.weilong.community.mapper.UserMapper;
import com.weilong.community.model.Question;
import com.weilong.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String dopublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tags")String tags,
            HttpServletRequest request, Model model
            ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        if("".equals(title)||title==null){
            model.addAttribute("error","问题题目未填写");
            return "publish";
        }else if("".equals(description)||description==null){
            model.addAttribute("error","问题详情未填写");
            return "publish";
        }else if("".equals(tags)||tags ==null){
            model.addAttribute("error","问题标签未填写");
            return "publish";
        }
        User user=null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null &&cookies.length!=0)
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    String token=cookie.getValue();
                    user=userMapper.findUserByToken(token);
                    if(user !=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        if(user ==null){
            model.addAttribute("error","用户未登录，请先进行登录");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modify(user.getGmt_create());
        question.setAuthor(user.getId());
        question.setTag(tags);
        questionMapper.insertQuestion(question);
        return  "redirect:/"
;    }


}
