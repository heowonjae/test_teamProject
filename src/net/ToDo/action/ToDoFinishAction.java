package net.ToDo.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ToDo.db.ToDoDAO;

public class ToDoFinishAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ToDoFinishAction excute()");
		
		String pageNum=request.getParameter("pageNum");
		//int num=Integer.parseInt(request.getParameter("num"));
		//System.out.println("num"+num);
		int num = Integer.parseInt(request.getParameter("complete"));
		
		//System.out.println("complete" + (complete + 1));
		
		
				ToDoDAO tdao=new ToDoDAO();
				tdao.ToDoFinish(num);
				
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out =response.getWriter();
				out.println("<script>");
				out.println("alert('완료성공');");
				out.println("location.href='./ToDoList.td?pageNum="+pageNum+"';");
				out.println("</script>");
				out.close();
				return null;
	}

}
