package com.chobocho.ShareMemory_back_end.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException){
        log.error("----------------------");
        log.error(numberFormatException.getMessage());

        return "NUMBER FORMAT EXCEPTION";
    }
}
