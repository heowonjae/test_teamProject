package net.FBoard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.FBoard.db.FBoardDAO;
import net.FComment.db.FComDAO;

public class FBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardListAction execute()");

		FBoardDAO fdao = new FBoardDAO();

		int count = fdao.getBoardCount();
		
		
		int pageSize = 10;

		String pageNum = request.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);

		int startRow = (currentPage - 1) * pageSize + 1;

		List boardList = null;
		

		if (count != 0) {
			boardList = fdao.getBoardList(startRow, pageSize);
		}

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((currentPage / pageBlock) - (currentPage % pageBlock == 0 ? 1 : 0)) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/list.jsp");
		return forward;
	}

}
