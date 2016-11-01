package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberLoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction execute()");
		//  id    passwd
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		// MemberDAO 객체생성 mdao
		MemberDAO mdao=new MemberDAO();
		// int check=  userCheck(id,passwd) 메서드호출
		int check=mdao.userCheck(id, pass);
		// check==0  "비밀번호틀림" 뒤로이동
		// check==-1  "아이디없음" 뒤로이동
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
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		// 세션객체 생성
		HttpSession  session=request.getSession();
		// 세션값 생성 "id" , id 
		session.setAttribute("id", id);
		// 이동  ./MemberMain.me
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./MemberMain.mc");
		return forward;
	}

}
