<%@page import="net.ToDo.db.ToDoBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>


<%
int category=Integer.parseInt(request.getParameter("category"));

List ToDoList =(List)request.getAttribute("ToDoList");
String pageNum=(String)request.getAttribute("pageNum");
int count=((Integer)request.getAttribute("count")).intValue();
int pageCount=((Integer)request.getAttribute("pageCount")).intValue();
int pageBlock=((Integer)request.getAttribute("pageBlock")).intValue();
int startPage=((Integer)request.getAttribute("startPage")).intValue();
int endPage=((Integer)request.getAttribute("endPage")).intValue();

%>


<h1>[
    <%if(category==0){%>완료됨<%}%>
    <%if(category==1){%>진행중<%}%>
    <%if(category==2){%>일1<%}%>
    <%if(category==3){%>일2<%}%> 
    <%if(category==4){%>없음<%}%> ]검색 결과[검색된 글 개수:<%=count %>]</h1>
<table border="1">
<tr>
    <td>작성자</td><td>중요여부</td><td>완료여부</td><td>할일</td><td>기간</td></tr>
    <%
    // ToDoList for 한칸 접근  데이터 가져오면 => ToDoBean bb저장
    if(count!=0){
    	for(int i=0;i<ToDoList.size();i++){
    		ToDoBean tb=(ToDoBean)ToDoList.get(i);
    		%>
<tr><td><%=tb.getId() %></td>
    <td><%=tb.getImportant() %></td>
    <td><%=tb.getComplete() %></td>
    <td><%=tb.getContent() %></td>
    <td><%=tb.getDeadline() %></td></tr>    		
    		<%
    	}
    }else{
    	%><tr><td colspan="5">검색 결과 없음</td></tr><%
    }
    %>
</table>
<input type="button" value="뒤로가기" onclick="location.href='./ToDoList.td'">

<%

	if(endPage > pageCount){
		endPage=pageCount;
	}
	if(startPage > pageBlock){
%><a href="./ToDoSearch.td?pageNum=<%=startPage-pageBlock%>&category=<%=category%>">Prev</a><%
	}
	for(int i=startPage; i<=endPage; i++){
		%><a href="./ToDoSearch.td?pageNum=<%=i%>&category=<%=category%>"><%=i %></a><%
	}
	if(endPage < pageCount){
%><a href="ToDoSearch.td?pageNum=<%=startPage+pageBlock%>&category=<%=category%>">Next</a><%
	}

%>


</body>
</html>