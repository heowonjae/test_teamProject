package com.todolist.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todolist.db.ToDoDAO;

public class ToDoDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ToDoDeleteAction excute()");

		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));

		ToDoDAO tdao = new ToDoDAO();
		tdao.deleteToDo(num);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제성공');");
		out.println("location.href='./ToDoList.td?pageNum=" + pageNum + "';");
		out.println("</script>");
		out.close();
		return null;
	}

}
