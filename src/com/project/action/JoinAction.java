package com.project.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.bean.MemberBean;
import com.project.dao.MemberDAO;
import com.project.factory.DaoFactory;

public class JoinAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JoinAction execute()");
		response.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = response.getWriter();
		MemberBean member = new MemberBean();
		MemberDAO dao = new DaoFactory().memberDAO();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");
		
		member.setId(id);
		member.setPass(pass);
		member.setName(name);
		member.setEmail(email);
		member.setAddress(address);
		member.setPhone(phone);
		member.setMobile(mobile);
		
		if (dao.memberAdd(member)) {
			out.write("<script>");
			out.write("alert('가입성공');");
			out.write("location.href = './Login.do';");
			out.write("</script>");
			out.close();
		} else {
			out.write("<script>");
			out.write("alert('가입실패');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
		}
		
		
		return null;
	}

}
