package net.member.action;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doProcess()");
		//�����ּ�  http://localhost:8080/Model2/MemberJoin.me
		// �����ּ�  /MemberJoin.me �ּ� �̾ƿ���
		String RequestURI=request.getRequestURI();
		System.out.println(RequestURI); 
		//  /Model2/MemberJoin.me
		String contextPath=request.getContextPath();
		//  /Model2     7
		System.out.println(contextPath);
		System.out.println(contextPath.length());
		String command=RequestURI.substring(contextPath.length());
		System.out.println(command);
		//   /MemberJoin.me
		// �ּҰ� ��
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/MemberJoin.mc")){
			// �̵�  ./member/insertForm.jsp
			forward=new ActionForward();
			forward.setRedirect(false);//forward
			forward.setPath("./member/join.jsp");
		}else if(command.equals("/MemberJoinAction.mc")){
			//   MemberJoinAction  execute() ȣ�� 
			action=new MemberJoinAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogin.mc")){
			//  ./member/loginForm.jsp  �̵�
			forward=new ActionForward();
			forward.setRedirect(false);//forward
			forward.setPath("./member/login.jsp");
		}else if(command.equals("/MemberLoginAction.mc")){
			//  MemberLoginAction  
			action=new MemberLoginAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberMain.mc")){
			//    �̵�  ./member/main.jsp
			forward=new ActionForward();
			forward.setRedirect(false);//forward
			forward.setPath("./member/main.jsp");
		}else if(command.equals("/MemberUpdate.mc")){
			//  MemberUpdate   execute() 
			action=new MemberUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberUpdateAction.mc")){
			//  MemberUpdateAction   execute()
			action=new MemberUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogout.mc")){
			//   MemberLogoutAction execute()
			action=new MemberLogoutAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberInfo.mc")){
			//    �̵�  ./member/main.jsp
			forward=new ActionForward();
			forward.setRedirect(false);//forward
			forward.setPath("./member/info.jsp");
		}else if(command.equals("/MemberDelete.mc")){
			//    �̵�  ./member/main.jsp
			forward=new ActionForward();
			forward.setRedirect(false);//forward
			forward.setPath("./member/delete.jsp");
		}else if(command.equals("/MemberDeleteAction.mc")){
			//   MemberLogoutAction execute()
			action=new MemberDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// �̵� 
		if(forward!=null){
			if(forward.isRedirect()){ //true
				response.sendRedirect(forward.getPath());
			}else{//false
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
