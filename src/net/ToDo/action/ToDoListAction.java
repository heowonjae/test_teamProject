package net.ToDo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ToDo.db.ToDoDAO;



public class ToDoListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


	ToDoDAO tdao=new ToDoDAO();
	//전체글의 개수 int count=메서드 호출 getBoardCount()
	int count=tdao.getListCount();
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
	//boardList=메서드 호출 getBoardList(starRow,pageSize);
	if(count!=0){
		ToDoList=tdao.getList(startRow, pageSize);
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
	
	
	
	
	
	ToDoDAO tdao1=new ToDoDAO();
	//전체글의 개수 int count=메서드 호출 getBoardCount()
	int count1=tdao1.getListCount_c();
	//pageSize 설정 한페이지에 보여줄 글수
	int pageSize1=10;
	String pageNum1=request.getParameter("pageNum1");
	//pageNum 파라미터 가져오기 pageNum 비어있으면 "1"
	if(pageNum1==null){
		pageNum1="1";
	}
	//currentPage <=정수형 pageNum
	int currentPage1=Integer.parseInt(pageNum1);
	//StartRow 구하기
	int startRow1=(currentPage1-1)*pageSize1+1;
	//List BoardList =null;
	List ToDoList1=null;
	//count 0이 아니면 
	//boardList=메서드 호출 getBoardList(starRow,pageSize);
	if(count1!=0){
		ToDoList1=tdao1.getList_c(startRow1, pageSize1);
	}
	
	int pageCount1=count1/pageSize1+(count1%pageSize1==0?0:1);
	//한화면에 보여줄 페이지 수 설정
	int pageBlock1=10;
	//화면에 보여주는 시작페이지 번호 구하기 1~10 => 1   11~20 =>11
	int startPage1=((currentPage1/pageBlock1)-(currentPage1%pageBlock1==0?1:0))*pageBlock1+1;
	//화면에 보여주는 끝페이지 번호 구하기
	int endPage1=startPage1+pageBlock1-1;
	if(endPage1 > pageCount1){
		endPage1=pageCount1;
	}
	
	//request 정보저장 count pageNum boardList pageCount
	//PageBlock startPage endpage
	//모든클래스형 저장 가능 =>Object형 업캐스팅 형변환 저장
	request.setAttribute("ToDoList1", ToDoList1);
	request.setAttribute("count1", count1);
	request.setAttribute("pageNum1", pageNum1);
	request.setAttribute("pageCount1", pageCount1);
	request.setAttribute("pageBlock1", pageBlock1);
	request.setAttribute("startPage1", startPage1);
	request.setAttribute("endPage1", endPage1);
	
	
	
	
	
	//이동    
	//true false?  ./board/list.jsp
	ActionForward forward=new ActionForward();
	//forward.setRedirect(isRedirect);  - true/false
	forward.setRedirect(false);
	//forward.setPath(path);
	forward.setPath("./To_Do_List/list.jsp");
	return forward;
}
}



