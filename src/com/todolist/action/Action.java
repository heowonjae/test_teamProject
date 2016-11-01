package com.todolist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.Http11AprProcessor;

public interface Action {
   //static final 멤버변수
   //추상메서드
   public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
