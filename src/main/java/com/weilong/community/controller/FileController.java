package com.weilong.community.controller;

import com.weilong.community.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

    @PostMapping("/files/upload")
    @ResponseBody
    public FileDto upload(){
        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setMessage("本地上传暂不支持哦~");
        fileDto.setUrl("/images/favicon.ico");
        return fileDto;
    }
}
