<%@page import="net.FComment.db.FComBean"%>
<%@page import="net.FBoard.db.FBoardBean"%>
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
// request.setAttribute("boardList", boardList);
// request.setAttribute("count", count);
// request.setAttribute("pageNum", pageNum);
// request.setAttribute("pageCount", pageCount);
// request.setAttribute("pageBlock", pageBlock);
// request.setAttribute("startPage", startPage);
// request.setAttribute("endPage", endPage);

List boardList =(List)request.getAttribute("boardList");
String pageNum=(String)request.getAttribute("pageNum");
int count=((Integer)request.getAttribute("count")).intValue();

/* int pageCount=((Integer)request.getAttribute("pageCount")).intValue(); */
/* int pageBlock=((Integer)request.getAttribute("pageBlock")).intValue();
int startPage=((Integer)request.getAttribute("startPage")).intValue();
int endPage=((Integer)request.getAttribute("endPage")).intValue(); */
%>
<h1>board/list.jsp</h1>
<h1>글목록[글개수:<%=count %>]</h1>
<h3><a href="./FBoardWrite.fbo">글쓰기</a></h3>
<table border="1">
<tr><td>번호</td><td>제목</td><td>작성자</td>
    <td>날짜</td><td>조회수</td></tr>
    <%
    if(count!=0){
   	 for(int i=0;i<boardList.size();i++){
    		FBoardBean fb=(FBoardBean)boardList.get(i);
    		%><tr><td><%=fb.getNum() %></td>
    		      <td>
    		      <%
    		      int wid=0;
    	if(fb.getRe_lev()>0){//답글이면 답글이미지 보이게 함
    		wid=10*fb.getRe_lev();
    		   %>
    		   <img src="./board/level.gif" width="<%=wid%>">
    		   <img src="./board/re.gif">
    		   <% 	  
    	}
    		      %>
    		      <a href="./FBoardContent.fbo?num=<%=fb.getNum()%>&pageNum=<%=pageNum%>&fsub=<%=fb.getSubject()%>"><%=fb.getSubject() %></a></td>
    		      <td><%=fb.getName() %></td> 
        	      <td><%=fb.getDate() %></td>
        	      <td><%=fb.getReadcount()%></td></tr><%
   	 }
    }else{
    	%><tr><td colspan="5">게시판 글없음</td></tr><%
    }
    %>
</table>
</body>
</html>





