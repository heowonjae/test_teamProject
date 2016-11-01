package com.todolist.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todolist.db.ToDoBean;
import com.todolist.db.ToDoDAO;

public class ToDoUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ToDoUpdate excute()");

		// int num 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		// String pageNum 파라미터 가져오기
		String pageNum = request.getParameter("pageNum");
		// 디비작업 객체 생성
		ToDoDAO tdao = new ToDoDAO();
		// 자바빈 ToDoBean tb=메서드호출
		ToDoBean tb = tdao.getToDoContent(num);

		// request bb저장 pageNum 저장
		request.setAttribute("tb", tb);
		request.setAttribute("pageNum", pageNum);

	//	tdao.updateToDo(tb);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./To_Do_List/update.jsp");
		return forward;

	}

}
