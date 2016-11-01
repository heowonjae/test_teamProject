package net.ToDo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ToDo.db.ToDoBean;
import net.ToDo.db.ToDoDAO;

public class ToDoUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ToDoUpdateAction excute()");

		// 한글처리
		request.setCharacterEncoding("utf-8");
		// pageNum 가져오기
		String pageNum =request.getParameter("pageNum");
		System.out.println("pageNum="+pageNum);
		
		// 자바빈 객체 생성 BoardBean bb
		ToDoBean tb = new ToDoBean();

		System.out.println(request.getParameter("important"));
		String important1 = request.getParameter("important");
		int important;
		
//		int important;
		//if(Integer.parseInt(request.getParameter("important")) == 1) {
		if(important1==null){	
		   important=0;
		} else{
			important=1;
		}
		System.out.println("important:"+important);
		// 폼=> 자바빈 멤버변수 저장 num id important complete content deadline
		tb.setNum(Integer.parseInt(request.getParameter("num")));
		tb.setCategory(Integer.parseInt(request.getParameter("category")));
		System.out.println("request:"+request.getParameter("category"));
		tb.setId(request.getParameter("id"));
		tb.setImportant(important);
	   // tb.setComplete(Integer.parseInt(request.getParameter("complete")));
		tb.setContent(request.getParameter("content"));
		 
        String str1=request.getParameter("deadline");
        System.out.println("str1:"+str1);
        String[] str2 = null;
        str2 = str1.split("/");
        
        for(int i=0; i<str2.length; i++) {
        	System.out.println(str2[i]);
        }
        tb.setDeadline(str2[2]+"/"+str2[0]+"/"+str2[1]);
		//tb.setDeadline(request.getParameter("deadline"));
		//System.out.println(request.getParameter("deadline"));

		// 디비객체 생성 ToDoDAO tdao
		ToDoDAO tdao = new ToDoDAO();

		// int check=메서드 호출
		tdao.updateToDo(tb);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('수정성공');");
		out.println("location.href='./ToDoList.td?pageNum=" + pageNum + "';");
		out.println("</script>");
		out.close();
		return null;
	}

}
