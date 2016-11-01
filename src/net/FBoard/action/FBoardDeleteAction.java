package net.FBoard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.FBoard.db.FBoardDAO;

public class FBoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardDeleteAction execute()");
		
		request.setCharacterEncoding("utf-8");
		String pageNum=request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		FBoardDAO fdao = new FBoardDAO();
		int check = fdao.deleteBoard(num,pass);
		
		if(check==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==-1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('num없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('삭제성공');");
		out.println("location.href='./FBoardList.fbo?pageNum="+pageNum+"';");
		out.println("</script>");
		out.close();
		return null;
	}

}
