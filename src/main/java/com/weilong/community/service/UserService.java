package com.weilong.community.service;

import com.weilong.community.mapper.UserMapper;
import com.weilong.community.model.User;
import com.weilong.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        System.out.println(user);
        System.out.println(user.getAccountId());
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if(users.size() == 0){
            //新增一个用户
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新用户信息
            User dbUser=users.get(0);
            User userExample=new User();
            userExample.setAvatorUrl(user.getAvatorUrl());
            userExample.setGmtModify(System.currentTimeMillis());
            userExample.setName(user.getName());
            userExample.setToken(user.getToken());
            UserExample userExample1=new UserExample();
            userExample1.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(userExample,userExample1);
        }
    }
}
