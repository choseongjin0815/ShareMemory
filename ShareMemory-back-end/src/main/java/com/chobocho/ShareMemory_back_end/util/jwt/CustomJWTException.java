package com.chobocho.ShareMemory_back_end.util.jwt;

public class CustomJWTException extends RuntimeException{

    public CustomJWTException(String msg){
        super(msg);
    }
}
