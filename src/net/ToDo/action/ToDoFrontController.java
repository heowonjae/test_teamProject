package net.ToDo.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class ToDoFrontController extends HttpServlet{
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
	/*
		if(command.equals("/ToDoList.td")){
		    forward =new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./To_Do_List/list.jsp");
		}*/if(command.equals("/ToDoWrite.td")){
        	forward =new ActionForward();
 			forward.setRedirect(false);
 			forward.setPath("./To_Do_List/write.jsp");
         } 
	    else if(command.equals("/ToDoWriteAction.td")){
   	        action=new ToDoWriteAction(); 		
   	        try{
			forward=action.execute(request, response);
		   }catch(Exception e){
			e.printStackTrace();}
       } else if(command.equals("/ToDoList.td")){
  	        action=new ToDoListAction(); 		
  	        try{
			forward=action.execute(request, response);
		   }catch(Exception e){
			e.printStackTrace();}
      }else if(command.equals("/ToDoDelete.td")){
	        action=new ToDoDeleteAction(); 		
	        try{
			forward=action.execute(request, response);
		   }catch(Exception e){
			e.printStackTrace();}
    } else if(command.equals("/ToDoUpdate.td")){
	        action=new ToDoUpdate(); 		
	        try{
			forward=action.execute(request, response);
		   }catch(Exception e){
			e.printStackTrace();}
    }else if(command.equals("/ToDoUpdateAction.td")){
	        action=new ToDoUpdateAction(); 		
	        try{
			forward=action.execute(request, response);
		   }catch(Exception e){
			e.printStackTrace();}
    }else if(command.equals("/ToDoFinish.td")){
	        action=new ToDoFinishAction(); 		
	        try{
			forward=action.execute(request, response);
		   }catch(Exception e){
			e.printStackTrace();
			}
    }else if(command.equals("/ToDoSearch.td")){
	        action=new ToDoSearchAction(); 		
	        try{
			forward=action.execute(request, response);
		   }catch(Exception e){
			e.printStackTrace();}
    }
		
		

	//이동
	if(forward!=null){
		if(forward.isRedirect()){    //true sendRedirect()
		response.sendRedirect(forward.getPath());	
		}else{   //false forward()
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
		    dispatcher.forward(request,response);
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




