package com.shivam.fullstack.backend.Exception;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class MyException extends Exception{
    private String str="";
    public MyException(){
        super();
    }
    public MyException(String str){
    this.str=str;
    }
    public void setException(String str){
        this.str=str;
    }
    public String getException(){
        return str;
    }



}
