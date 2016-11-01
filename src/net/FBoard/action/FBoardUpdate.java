package net.FBoard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.FBoard.db.FBoardBean;
import net.FBoard.db.FBoardDAO;

public class FBoardUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FBoardUpdate execute()");
		
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		FBoardDAO fdao=new FBoardDAO();
		
		FBoardBean fb=fdao.getBoard(num);
		
		request.setAttribute("fb", fb);
		request.setAttribute("pageNum", pageNum);
		System.out.println(num);
		ActionForward forward=new ActionForward();
		forward.setRedirect(false); //forward
		forward.setPath("./board/update.jsp");
		return forward;
	}

}
