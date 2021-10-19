package com.weilong.community.mapper;

import com.weilong.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modify,avator_url) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modify},#{avator_url})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User findUserByToken(@Param("token") String token);
}
