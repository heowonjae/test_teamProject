package net.FComment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());

		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/FCommentWrite.fco")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/comment.jsp");
		
		}else if(command.equals("/FCommentWriteAction.fco")){
			
			action=new FCommentWriteAction(); 
			
			try {
				//부모에서 오버라이딩한  메서드호출
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/FCommentList.fco")){
		}
		if(forward!=null){
			if(forward.isRedirect()){ //true  sendRedirect()
				response.sendRedirect(forward.getPath());
			}else{//false  forward()
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
