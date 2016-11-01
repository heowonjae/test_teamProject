package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;

public class MemberLoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction execute()");
		//  id    passwd
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		// MemberDAO ��ü���� mdao
		MemberDAO mdao=new MemberDAO();
		// int check=  userCheck(id,passwd) �޼���ȣ��
		int check=mdao.userCheck(id, pass);
		// check==0  "��й�ȣƲ��" �ڷ��̵�
		// check==-1  "���̵����" �ڷ��̵�
		if(check==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('��й�ȣƲ��');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==-1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('���̵����');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		// ���ǰ�ü ����
		HttpSession  session=request.getSession();
		// ���ǰ� ���� "id" , id 
		session.setAttribute("id", id);
		// �̵�  ./MemberMain.me
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./MemberMain.mc");
		return forward;
	}

}
