package net.FComment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.FComment.db.FComBean;
import net.FComment.db.FComDAO;

public class FCommentWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FBoardWriteAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		
		
		FComBean cb = new FComBean();
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		
		cb.setName(request.getParameter("name"));
		cb.setPass(request.getParameter("pass"));
		cb.setContent(request.getParameter("content"));
		cb.setSubject(request.getParameter("fsub"));
		
		
		System.out.println(request.getParameter("name"));
		FComDAO cdao = new FComDAO();
		cdao.insertCom(cb);
		
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("./FBoardContent.fbo?num="+fnum);
		
		return forward;
	}

}
