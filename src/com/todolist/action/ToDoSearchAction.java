package com.todolist.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todolist.db.ToDoDAO;



public class ToDoSearchAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


    int category=Integer.parseInt(request.getParameter("category"));
      
	ToDoDAO tdao=new ToDoDAO();
	//전체글의 개수 int count=메서드 호출 getListCount(category)
	int count=tdao.getListCount(category);
	//pageSize 설정 한페이지에 보여줄 글수
	int pageSize=10;
	String pageNum=request.getParameter("pageNum");
	//pageNum 파라미터 가져오기 pageNum 비어있으면 "1"
	if(pageNum==null){
		pageNum="1";
	}
	//currentPage <=정수형 pageNum
	int currentPage=Integer.parseInt(pageNum);
	//StartRow 구하기
	int startRow=(currentPage-1)*pageSize+1;
	//List BoardList =null;
	List ToDoList=null;
	//count 0이 아니면 
	//boardList=메서드 호출 getBoardList(starRow,pageSize,category);
	if(count!=0){
		ToDoList=tdao.getList(startRow, pageSize,category);
	}
	
	int pageCount=count/pageSize+(count%pageSize==0?0:1);
	//한화면에 보여줄 페이지 수 설정
	int pageBlock=10;
	//화면에 보여주는 시작페이지 번호 구하기 1~10 => 1   11~20 =>11
	int startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock+1;
	//화면에 보여주는 끝페이지 번호 구하기
	int endPage=startPage+pageBlock-1;
	if(endPage > pageCount){
		endPage=pageCount;
	}
	
	//request 정보저장 count pageNum boardList pageCount
	//PageBlock startPage endpage
	//모든클래스형 저장 가능 =>Object형 업캐스팅 형변환 저장
	request.setAttribute("ToDoList", ToDoList);
	request.setAttribute("count", count);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("pageCount", pageCount);
	request.setAttribute("pageBlock", pageBlock);
	request.setAttribute("startPage", startPage);
	request.setAttribute("endPage", endPage);


	
	//이동    
	//true false?
	ActionForward forward=new ActionForward();
	//forward.setRedirect(isRedirect);  - true/false
	forward.setRedirect(false);
	//forward.setPath(path);
	forward.setPath("./To_Do_List/search.jsp");
	return forward;
}
}



