package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction execute()");
		// �ѱ�ó��
		request.setCharacterEncoding("utf-8");

		String realPath = request.getRealPath("/upload");
		System.out.println(realPath);
		// �ø� ���� �ִ�ũ��
		int maxSize = 5 * 1024 * 1024; // 5M

		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		// BoardBean ��ü ���� bb
		MemberBean mb = new MemberBean();
		// set�޼��� �� => �ڹٺ� ������� ����
		mb.setId(multi.getParameter("id"));
		mb.setName(multi.getParameter("name"));
		mb.setPass(multi.getParameter("pass"));
		mb.setFile(multi.getFilesystemName("file"));
		System.out.println("upload������ �ö�����:" + multi.getFilesystemName("file"));
		System.out.println("�������̸�:" + multi.getOriginalFileName("file"));
		// set�޼���ȣ�� ip request.getRemoteAddr()

		// System.out.println(bb);
		// ����۾����� ��Ű�� board �����̸� BoardDAO
		// BoardDAO ��ü ���� bdao
		MemberDAO mdao = new MemberDAO();
		// �޼���ȣ�� insertBoard(bb) sql���� Mysql ���ý��� ��¥�ð� now()
		mdao.insertMember(mb);

		
	
		
		
		
		
		
		
		
		
		// �̵� ./MemberLogin.me
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./MemberLogin.mc");
		return forward;
	}
}
