package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.FBoardBean;
import com.board.db.FBoardDAO;

public class FBoardContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FBoardContentAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		String pageNum=request.getParameter("pageNum");
		
		FBoardDAO fdao=new FBoardDAO();
		
		fdao.updateReadCount(num);
		
		FBoardBean fb=fdao.getBoard(num);
		
		request.setAttribute("fb", fb);
		request.setAttribute("pageNum", pageNum);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false); //forward
		forward.setPath("./board/content.jsp");
		return forward;
	}

}
