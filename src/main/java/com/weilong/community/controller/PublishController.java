package com.weilong.community.controller;

import com.weilong.community.dto.QuestionDto;
import com.weilong.community.dto.TagDTO;
import com.weilong.community.model.Question;
import com.weilong.community.model.User;
import com.weilong.community.provider.TagProvider;
import com.weilong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;
    //获取的标签集合
    List<TagDTO> tagList = TagProvider.getTagList();
    //编辑问题
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        QuestionDto question = questionService.getquestionById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tags",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("taglist",tagList);
        return "publish";
    }
    //跳转到发布问题页面
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("taglist",tagList);
        return "publish";
    }
    //更新或插入新问题
    @PostMapping("/publish")
    public String dopublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tags")String tags,
            @RequestParam(value = "id",required = false)Integer id ,
            HttpServletRequest request, Model model
            ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);
        model.addAttribute("taglist",tagList);
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
        User user= (User) request.getSession().getAttribute("user");
        if(user ==null){
            model.addAttribute("error","用户未登录，请先进行登录");
            return "publish";
        }
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setAuthor(user.getId());
        question.setTag(tags);
        question.setId(id);
        questionService.insertOrUpdateQuestion(question);
        return  "redirect:/"
;    }


}
