package com.weilong.community.provider;

import com.weilong.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagProvider {
    public static List<TagDTO> getTagList(){
        List<TagDTO> result = new ArrayList<>();
        TagDTO qiandaun = new TagDTO();
        qiandaun.setCategoryName("前端");
        qiandaun.setTags(Arrays.asList("javascript","vue","css","html","node.js","react.js","jquery"
        ,"css6","typescript","npm","bootstrap","angular"));
        result.add(qiandaun);

        TagDTO houduan = new TagDTO();
        houduan.setCategoryName("后端");
        houduan.setTags(Arrays.asList("php","java","golang","C++","C","Spring","SpringBoot"
        ,"diango","flask","C#"));
        result.add(houduan);

        TagDTO yidong = new TagDTO();
        yidong.setCategoryName("移动端");
        yidong.setTags(Arrays.asList("android","java","ios","小程序","swift","xcode","android-studio"
                ,"flutter","webapp","kotlin"));
        result.add(yidong);

        TagDTO shujuku = new TagDTO();
        shujuku.setCategoryName("数据库");
        shujuku.setTags(Arrays.asList("mysql","redis","sql","mongodb","json","elasticsearch","nosql"
                ,"postgresql","sqlite","mariadb"));
        result.add(shujuku);

        return result;
    }
}
