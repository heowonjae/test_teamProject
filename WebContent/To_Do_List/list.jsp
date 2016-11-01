<%@page import="net.ToDo.db.ToDoBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
List ToDoList =(List)request.getAttribute("ToDoList");
String pageNum=(String)request.getAttribute("pageNum");
int count=((Integer)request.getAttribute("count")).intValue();
int pageCount=((Integer)request.getAttribute("pageCount")).intValue();
int pageBlock=((Integer)request.getAttribute("pageBlock")).intValue();
int startPage=((Integer)request.getAttribute("startPage")).intValue();
int endPage=((Integer)request.getAttribute("endPage")).intValue();


List ToDoList1 =(List)request.getAttribute("ToDoList1");
String pageNum1=(String)request.getAttribute("pageNum1");
int count1=((Integer)request.getAttribute("count1")).intValue();
int pageCount1=((Integer)request.getAttribute("pageCount1")).intValue();
int pageBlock1=((Integer)request.getAttribute("pageBlock1")).intValue();
int startPage1=((Integer)request.getAttribute("startPage1")).intValue();
int endPage1=((Integer)request.getAttribute("endPage1")).intValue();

 

%>
<h1>list.jsp</h1>

<h1>할 일 목록[글개수:<%=count %>]</h1>
<h3><a href="./ToDoWrite.td">할 일 추가</a></h3>


<form action="./ToDoSearch.td" method="post">
<select name="category">
<option value="0" selected>완료됨</option>
<option value="1" selected>진행중</option>
<option value="2" selected>일1</option>
<option value="3" selected>일2</option>
<option value="4" selected>없음</option>
</select>
<input type="submit" value="search">
</form>


<form action="./ToDoFinish.td?pageNum=<%=pageNum %>" method="post">
<table border="1">
<tr><td>완료체크</td><td>중요여부</td><td>작성자</td><td>카테고리</td><td>내용</td>
    <td>기간</td><td>수정/삭제</td></tr>
    <%
    if(count!=0){
   	 for(int i=0;i<ToDoList.size();i++){
    		ToDoBean tb=(ToDoBean)ToDoList.get(i);
                
    		%>
    		 <input type="hidden" name="num" value="<%=tb.getNum() %>">
    		<tr>
    		       <td><input type="checkbox" name="complete" value="1"></td>
                       <td><% if(tb.getImportant()==1){%>YES<%}else{%>NO<%}%></td>
    		      <td><%=tb.getId() %></td>
    		      <td><%if(tb.getCategory()==1)%>진행중
    		          <%if(tb.getCategory()==2)%>일1
    		          <%if(tb.getCategory()==3)%>일2
    		          <%if(tb.getCategory()==4)%>없음
    		      </td>
        	      <td><%=tb.getContent() %></td>
        	      <td><%=tb.getDeadline() %></td>
                      <td><input type="button" value="수정" onclick="location.href='./ToDoUpdate.td?num=<%=tb.getNum()%>&pageNum=<%=pageNum%>'">
                          <input type="button" value="삭제" onclick="location.href='./ToDoDelete.td?num=<%=tb.getNum()%>&pageNum=<%=pageNum%>'"></td></tr><%
   	 }
    }else{
    	%><tr><td colspan="6">할 일 없음 놀아라~</td></tr><%
    }
    %>
</table>
<input type="submit" value="완료">
</form>
<%
if(count>0){
	//[이전] 11 > 10  21 >10
	if(startPage > pageBlock){
		%><a href="./ToDoList.td?pageNum=<%=startPage-pageBlock%>">[이전]</a><%
	}
	//[1]~[10]
	for(int i=startPage;i<=endPage;i++){
		%><a href="./ToDoList.td?pageNum=<%=i%>">[<%=i%>]</a><%
	}
	//[다음] 
	if(endPage < pageCount){
		%><a href="./ToDoList.td?pageNum=<%=startPage+pageBlock%>">[다음]</a><%
	}
}

%>

<h1>완료 목록[글개수:<%=count1 %>]</h1>
<table border="1">
<tr><td>중요여부</td><td>작성자</td><td>카테고리</td><td>내용</td>
    <td>기간</td></tr>
    <%
    if(count1!=0){
   	 for(int i=0;i<ToDoList1.size();i++){
    		ToDoBean tb1=(ToDoBean)ToDoList1.get(i);
    		%><tr>
                  <td><%if(tb1.getImportant()==1){%>YES<%}else{%>NO<%}%></td>
    		      <td><%=tb1.getId() %></td>
        	      <td><%=tb1.getCategory() %></td>
        	      <td><%=tb1.getContent() %></td>
        	      <td><%=tb1.getDeadline() %></td></tr><%
   	 }
    }else{
    	%><tr><td colspan="5">완료 목록 없음</td></tr><%
    }
    %>
</table>

<%
if(count1>0){
	//[이전] 11 > 10  21 >10
	if(startPage1 > pageBlock1){
		%><a href="./ToDoList.td?pageNum1=<%=startPage1-pageBlock1%>">[이전]</a><%
	}
	//[1]~[10]
	for(int i=startPage1;i<=endPage1;i++){
		%><a href="./ToDoList.td?pageNum1=<%=i%>">[<%=i%>]</a><%
	}
	//[다음] 
	if(endPage1 < pageCount1){
		%><a href="./ToDoList.td?pageNum1=<%=startPage1+pageBlock1%>">[다음]</a><%
	}
}
%>







</body>
</html>