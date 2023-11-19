package com.mlfc.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLIntegrityConstraintViolationException;


@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Rest<String> exceptionHandler(SQLIntegrityConstraintViolationException sicve){
        log.error(sicve.getMessage());
        if (sicve.getMessage().contains("Duplicate entry"))
        {
            String[] split = sicve.getMessage().split(" ");
            String msg = split[2]+"已存在";
            return Rest.error(msg);
        }
        return Rest.error("未知异常");
    }

    @ExceptionHandler(CustomException.class)
    public Rest<String> exceptionHandler(CustomException ex){
        log.error(ex.getMessage());
        return Rest.error(ex.getMessage());
    }
}
