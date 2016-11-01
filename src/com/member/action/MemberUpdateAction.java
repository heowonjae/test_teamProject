package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateAction execute()");
		// ���ǰ� ��������
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// ���ǰ� ������ ./MemberLogin.me
		if (id == null) {
			response.sendRedirect("./MemberLogin.mc");
		}
		
		// �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		// �ڹٺ� ��ü ���� MemberBean mb

		String realPath = request.getRealPath("/upload");
		int maxSize = 5 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		MemberBean mb = new MemberBean();
		// �޼���ȣ�� set�޼��� <= ���Ķ����
		// id passwd name age gender email
		mb.setId(multi.getParameter("id"));
		mb.setPass(multi.getParameter("pass"));
		mb.setName(multi.getParameter("name"));
		mb.setEmail(multi.getParameter("email"));
		mb.setFile(multi.getParameter("file"));
		
		if(multi.getFilesystemName("file")!=null){

			mb.setFile(multi.getFilesystemName("file"));	
		}else{
			mb.setFile(multi.getParameter("file2")); //���� ���� �̸�
		}
		
		// ���ü ���� MemberDAO mdao
		MemberDAO mdao = new MemberDAO();
		// int check= �޼���ȣ�� updateMember(mb)
		int check = mdao.updateMember(mb);
		// check==0 "��й�ȣƲ��" �ڷ��̵�
		// check==-1 "���̵����" �ڷ��̵�
		if (check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��й�ȣƲ��');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else if (check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('���̵����');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		// "��������" ./MemberMain.me �̵�
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('��������');");
		out.println("location.href='./MemberMain.mc'");
		out.println("</script>");
		out.close();
		return null;
	}
}
