package net.ToDo.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ToDo.db.ToDoBean;
import net.ToDo.db.ToDoDAO;

public class ToDoWriteAction implements Action{


		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("ToDoWriteAction execute()");
			//한글처리
			request.setCharacterEncoding("utf-8");
			
			
			String important1 = request.getParameter("important");
			int important;
			
//			int important;
			//if(Integer.parseInt(request.getParameter("important")) == 1) {
			if(important1==null){	
			   important=0;
			} else{
				important=1;
			}
			
            System.out.println("important:"+important);
			//자바빈 객체 생성
			//폼->자바빈 멤버변수 저장
			ToDoBean tb=new ToDoBean();
			tb.setId(request.getParameter("id"));
			tb.setCategory(Integer.parseInt(request.getParameter("category")));
			System.out.println("category: "+Integer.parseInt(request.getParameter("category")));
			tb.setContent(request.getParameter("content"));
			tb.setImportant(important);
	        
			String str1=request.getParameter("deadline");
			System.out.println("deadline:"+str1);
	        String[] str2 = null;
	        str2 = str1.split("/");
	        
	        for(int i=0; i<str2.length; i++) {
	        	System.out.println(str2[i]);
	        }
	        tb.setDeadline(str2[2]+"/"+str2[0]+"/"+str2[1]);
			//디비작업 객체 생성 bdao
			ToDoDAO tdao=new ToDoDAO();
			//메서드 호출 insertBoard(bb)
			tdao.insertToDoList(tb);
			
			//이동  ActionForward객체 생성 forward
			ActionForward forward=new ActionForward();
			//true/false? 
			forward.setRedirect(true);
			forward.setPath("./ToDoList.td");
			return forward;
		}


	
}
