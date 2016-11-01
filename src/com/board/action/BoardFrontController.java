package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI=request.getRequestURI();
		System.out.println(RequestURI);
		
		String contextPath=request.getContextPath();
		System.out.println(contextPath);
		
		System.out.println(contextPath.length());
		String command=RequestURI.substring(contextPath.length());
		System.out.println(command);
		
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/FBoardWrite.fbo")){
			
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/write.jsp");
		}else if(command.equals("/FBoardWriteAction.fbo")){
			action=new FBoardWriteAction();
			try {
				//�θ𿡼� �������̵���  �޼���ȣ��
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FBoardList.fbo")){
			action=new FBoardListAction();
			try {
				// alt shift z   y   trycatch�ڵ�����
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FBoardContent.fbo")){
			action=new FBoardContentAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FBoardUpdate.fbo")){
			action=new FBoardUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FBoardUpdateAction.fbo")){
			action=new FBoardUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FBoardDelete.fbo")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/delete.jsp");
		}else if(command.equals("/FBoardDeleteAction.fbo")){
			action=new FBoardDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		//System.out.println("BoardFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
