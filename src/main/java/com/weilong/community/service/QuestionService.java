package com.weilong.community.service;

import com.weilong.community.dto.PaginationDto;
import com.weilong.community.dto.QuestionDto;
import com.weilong.community.mapper.QuestionMapper;
import com.weilong.community.mapper.UserMapper;
import com.weilong.community.model.Question;
import com.weilong.community.model.QuestionExample;
import com.weilong.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    //获取所有问题列表
    public PaginationDto getlist(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalpage;//总页数
        Integer count = (int)questionMapper.countByExample(new QuestionExample());
        if(count/size==0){
            totalpage=count/size;
        }else {
            totalpage=count/size +1;
        }
        if (page < 1) {
            page = 1;
        } else if (page > totalpage) {
            page=totalpage;
        }
        paginationDto.setPagination(totalpage, page);
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getAuthor());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }
    //获取当前登录用户的问题列表
    public PaginationDto getlistbyId(Integer userid, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalpage;//总页数
        QuestionExample example = new QuestionExample();
        example.createCriteria().andAuthorEqualTo(userid);
        Integer count = (int)questionMapper.countByExample(example);
        if(count/size==0){
            totalpage=count/size;
        }else {
            totalpage=count/size +1;
        }
        if (page < 1) {
            page = 1;
        } else if (page > totalpage) {
            page=totalpage;
        }
        paginationDto.setPagination(totalpage, page);
        Integer offset = size * (page - 1);

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andAuthorEqualTo(userid);
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getAuthor());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }
    //根据问题id获取问题详情
    public QuestionDto getquestionById(Integer id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getAuthor());
        QuestionDto questionDto=new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(user);
        return questionDto;
    }
    //新增或者更新问题
    public void insertOrUpdateQuestion(Question question) {
        if(question.getId()== null){
            //新建问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            //更新问题
            Question updateQuestion = new Question();
            updateQuestion.setGmtModify(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion, example);
        }
    }

}
