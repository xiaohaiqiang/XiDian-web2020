package cn.edu.xidian.community1.advice;

import cn.edu.xidian.community1.dto.ResultDTO;
import cn.edu.xidian.community1.exception.CustomizeErrorCode;
import cn.edu.xidian.community1.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model, HttpServletRequest request, HttpServletResponse response) {

        String contentType = request.getContentType();

        if("application/json".equals(contentType)){
            //返回JSON
            ResultDTO resultDTO;
            if(ex instanceof CustomizeException){
                ex.printStackTrace();
                resultDTO = ResultDTO.errorOf((CustomizeException)ex);
            }else{
                ex.printStackTrace();
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }else{
            //错误页面跳转
            if(ex instanceof CustomizeException){
                ex.printStackTrace();
                model.addAttribute("message", ex.getMessage());
            }else{
                ex.printStackTrace();
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");
        }


    }

}
