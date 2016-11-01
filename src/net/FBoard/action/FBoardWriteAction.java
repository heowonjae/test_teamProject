package net.FBoard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.FBoard.db.FBoardBean;
import net.FBoard.db.FBoardDAO;

public class FBoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FBoardWriteAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		String realpath=request.getRealPath("/upload");
		System.out.println(realpath);
		//올릴 파일 최대크기
		int maxSize=5*1024*1024; //5M

		MultipartRequest multi= new MultipartRequest(request,realpath,maxSize,"utf-8",new DefaultFileRenamePolicy());

		
		FBoardBean fb=new FBoardBean();
		
		fb.setName(multi.getParameter("name"));
		fb.setPass(multi.getParameter("pass"));
		fb.setSubject(multi.getParameter("subject"));
		fb.setContent(multi.getParameter("content"));
		fb.setFile(multi.getFilesystemName("file"));
		System.out.println("upload폴더가 올라간파일: "+multi.getFilesystemName("file"));
		System.out.println("원파일: "+multi.getOriginalFileName("file"));
		fb.setIp(request.getRemoteAddr());
		
		FBoardDAO fdao = new FBoardDAO();
		fdao.insertBoard(fb);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./FBoardList.fbo");
		return forward;
	}

}
