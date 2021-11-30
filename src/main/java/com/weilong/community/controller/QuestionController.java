package com.weilong.community.controller;

import com.weilong.community.dto.CommentDTO;
import com.weilong.community.dto.QuestionDto;
import com.weilong.community.enums.CommentTypeEnum;
import com.weilong.community.service.CommentService;
import com.weilong.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;
    //查看具体某个问题
    @GetMapping("/question/{id}")
    public String questionDetail(@PathVariable(name = "id") Integer id, Model model){
        QuestionDto questionDto=questionService.getquestionById(id);
        List<CommentDTO> commentDTOList=commentService.getCommentsListById(id, CommentTypeEnum.QUESTION.getType());
        questionService.addViewCount(id);
        model.addAttribute("question",questionDto);
        model.addAttribute("comments",commentDTOList);
        return "question";
    }
}
