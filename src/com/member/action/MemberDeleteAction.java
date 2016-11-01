package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("execute Delete");
		// ���ǰ� ��������
		// ���ǰ�������
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// ���ǰ� ������ loginForm.jsp�̵�
		if (id == null) {
			response.sendRedirect("loginForm.jsp");
		}
		// �Ķ���� passwd ��������
		String pass = request.getParameter("pass");

		// ����۾����� ��ü ���� MemberDAO mdao
		MemberDAO mdao = new MemberDAO();
		// int check =�����޼��� deleteMember(id,passwd)
		int check = mdao.deleteMember(id, pass);
		// check==1 ���ǰ��ʱ�ȭ "��������" main.jsp�̵�
		// check==0 "��й�ȣƲ��" �ڷ��̵�
		// check==-1 "���̵����" �ڷ��̵�
		if (check == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();	
			out.println("<script>");
			out.println("session.invalidate()");
			out.println("alert('��������');");
			out.println("location.href='/MemberLogin.mc'");
			out.println("</script>");
			out.close();
			return null;
		} else if (check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��й�ȣƲ��');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���̵����');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

	}

}
