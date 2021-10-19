package com.weilong.community.mapper;

import com.weilong.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modify,author,tag) values(#{title},#{description},#{gmt_create},#{gmt_modify},#{author},#{tag})")
    void insertQuestion(Question question);
}
