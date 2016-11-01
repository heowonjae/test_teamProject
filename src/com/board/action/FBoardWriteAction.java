package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.FBoardBean;
import com.board.db.FBoardDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FBoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FBoardWriteAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		String realpath=request.getRealPath("/upload");
		System.out.println(realpath);
		//�ø� ���� �ִ�ũ��
		int maxSize=5*1024*1024; //5M

		MultipartRequest multi= new MultipartRequest(request,realpath,maxSize,"utf-8",new DefaultFileRenamePolicy());

		
		FBoardBean fb=new FBoardBean();
		
		fb.setName(multi.getParameter("name"));
		fb.setPass(multi.getParameter("pass"));
		fb.setSubject(multi.getParameter("subject"));
		fb.setContent(multi.getParameter("content"));
		fb.setFile(multi.getFilesystemName("file"));
		System.out.println("upload������ �ö�����: "+multi.getFilesystemName("file"));
		System.out.println("������: "+multi.getOriginalFileName("file"));
		fb.setIp(request.getRemoteAddr());
		
		FBoardDAO fdao = new FBoardDAO();
		fdao.insertBoard(fb);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./FBoardList.fbo");
		return forward;
	}

}
