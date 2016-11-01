package com.member.action;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���ǰ� �ʱ�ȭ
		HttpSession session=request.getSession();
		session.invalidate();
		// "�α׾ƿ�"
		// �̵� ./MemberMain.me
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		out.println("<script>");
		out.println("alert('�α׾ƿ�');");
		out.println("location.href='./MemberMain.mc';");
		out.println("</script>");
		out.close();
		return null;
	}
}
