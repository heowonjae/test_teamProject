package com.prototype.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LogoutAction execute()");
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("./");
		
		return null;
	}

}
