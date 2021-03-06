package com.weilong.community.mapper;

import com.weilong.community.dto.QuestionDto;
import com.weilong.community.dto.QuestionQueryDto;
import com.weilong.community.model.Question;
import com.weilong.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    void incView(Question question);
    void incCommentCount(Question question);
    List<Question> selectRelaticeQuestions(Question question);

    Integer countBySearch(QuestionQueryDto queryDto);

    List<Question> selectListBySearch(QuestionQueryDto queryDto);

    List<Question> selectQuestionlistByUserId(Integer userid, Integer offset, Integer size);
}