package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute Delete");
		// 세션값 가져오기
		// 세션가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 세션값 없으면 loginForm.jsp이동
		if (id == null) {
			response.sendRedirect("loginForm.jsp");
		}
		// 파라미터 passwd 가져오기
		String pass = request.getParameter("pass");

		// 디비작업파일 객체 생성 MemberDAO mdao
		MemberDAO mdao = new MemberDAO();
		// int check =삭제메서드 deleteMember(id,passwd)
		int check = mdao.deleteMember(id, pass);
		// check==1 세션값초기화 "삭제성공" main.jsp이동
		// check==0 "비밀번호틀림" 뒤로이동
		// check==-1 "아이디없음" 뒤로이동
		if (check == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();	
			out.println("<script>");
			out.println("session.invalidate()");
			out.println("alert('삭제성공');");
			out.println("location.href='/MemberLogin.mc'");
			out.println("</script>");
			out.close();
			return null;
		} else if (check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

	}

}
