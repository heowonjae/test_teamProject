package com.member.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdate execute()");
		//���ǰ� ��������
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//���ǰ� ������  ./MemberLogin.me
		if(id==null){
			response.sendRedirect("./MemberLogin.mc");
		}
		
		// ���ü ���� MemberDAO mdao
		MemberDAO mdao=new MemberDAO();
		// MemberBean mb =  �޼���ȣ�� getMember(id)
		MemberBean mb=mdao.getMember(id);
		
		// request �Ӽ� ����  "mb",mb
		request.setAttribute("mb", mb);
		// �̵�   ./member/updateForm.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/update.jsp");
		return forward;
	}
}
