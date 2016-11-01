package com.prototype.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prototype.dao.MemberDAO;
import com.prototype.factory.DaoFactory;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginAction execute()");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = response.getWriter();
		
		MemberDAO dao = new DaoFactory().memberDAO();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		int result = dao.memberLoginCheck(id, pass);
		
		if (result == 0) {
			out.write("<script>");
			out.write("alert('비밀번호 틀림');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
			return null;
		} else if (result == -1) {
			out.write("<script>");
			out.write("alert('확인 후\n다시 시도 하시기 바랍니다.');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
			return null;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setMaxInactiveInterval(60 * 10);
		response.sendRedirect("./");
		
		return null;
	}

}
