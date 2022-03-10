package com.weilong.community.service;

import com.weilong.community.dto.PaginationDto;
import com.weilong.community.dto.QuestionDto;
import com.weilong.community.dto.QuestionQueryDto;
import com.weilong.community.exception.BusinessException;
import com.weilong.community.mapper.QuestionExtMapper;
import com.weilong.community.mapper.QuestionMapper;
import com.weilong.community.mapper.UserMapper;
import com.weilong.community.model.Question;
import com.weilong.community.model.QuestionExample;
import com.weilong.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    //获取所有问题列表
    public PaginationDto getlist(String search, Integer page, Integer size) {

        if(StringUtils.isNoneBlank(search)){
            String[] split = search.split(" ");
            search = Arrays.stream(split).collect(Collectors.joining("|"));
        }

        PaginationDto paginationDto = new PaginationDto();
        Integer totalpage;//总页数
        QuestionQueryDto queryDto = new QuestionQueryDto();
        queryDto.setSearch(search);
        Integer count =  questionExtMapper.countBySearch(queryDto);
        if (count / size == 0) {
            totalpage = count / size;
        } else {
            totalpage = count / size + 1;
        }
        if(totalpage<=1){
            totalpage=1;
        }
        if (page < 1) {
            page = 1;
        } else if (page > totalpage) {
            page = totalpage;
        }
        paginationDto.setPagination(totalpage, page);
        Integer offset = size * (page - 1);
        queryDto.setOffset(offset);
        queryDto.setSize(size);
        List<Question> questionList = questionExtMapper.selectListBySearch(queryDto);
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
        Integer count = (int) questionMapper.countByExample(example);
        if (count / size == 0) {
            totalpage = count / size;
        } else {
            totalpage = count / size + 1;
        }
        if (page <= 1) {
            page = 1;
        } else if (page > totalpage) {
            page = totalpage;
        }
        if(totalpage<=1){
            totalpage=1;
        }
        paginationDto.setPagination(totalpage, page);
        Integer offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andAuthorEqualTo(userid);
        List<Question> questionList = questionExtMapper.selectQuestionlistByUserId(userid,offset,size);
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
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new BusinessException("问题不存在，换个问题试试");
        }
        User user = userMapper.selectByPrimaryKey(question.getAuthor());
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(user);
        return questionDto;
    }

    //新增或者更新问题
    public void insertOrUpdateQuestion(Question question) {
        if (question.getId() == null) {
            //新建问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            question.setLikeCount(0);
            question.setViewCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
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
    //访问数增加
    public void addViewCount(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
    //获取相关问题
    public List<QuestionDto> getRelativeQuestions(QuestionDto questionDto) {
        if(StringUtils.isBlank(questionDto.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionDto.getTag(), ",");
        String resulttag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setTag(resulttag);
        List<Question> questionList = questionExtMapper.selectRelaticeQuestions(question);
        List<QuestionDto> resultQuestionDtoList=new ArrayList<>();
        for(Question question1:questionList){
            QuestionDto dto = new QuestionDto();
            BeanUtils.copyProperties(question1,dto);
            User user = userMapper.selectByPrimaryKey(questionDto.getAuthor());
            dto.setUser(user);
            resultQuestionDtoList.add(dto);
        }
        return resultQuestionDtoList;
    }
}
