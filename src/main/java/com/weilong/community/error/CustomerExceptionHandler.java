package com.weilong.community.error;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import com.weilong.community.dto.ResultDTO;
import com.weilong.community.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ModelAndView handler(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            try {
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(ResultDTO.error((BusinessException) ex)));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }else{
            if(ex instanceof BusinessException){
                model.addAttribute("message",ex.getMessage());
            }else {
                model.addAttribute("message","服务器出错了，请检查");
            }
            return new ModelAndView("error");
        }

    }

}
