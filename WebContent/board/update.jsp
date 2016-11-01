<%@page import="net.FBoard.db.FBoardBean"%>
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
// request.setAttribute("fb", fb);
// request.setAttribute("pageNum", pageNum);
FBoardBean fb=(FBoardBean)request.getAttribute("fb");
String pageNum=(String)request.getAttribute("pageNum");

%>
<%=fb.getNum() %>
<h1>board/update.jsp</h1>
<h1>게시판 글수정</h1>
<form action="./FBoardUpdateAction.fbo?pageNum=<%=pageNum %>" method="post" enctype="multipart/form-data">
<input type="hidden" name="num" value="<%=fb.getNum()%>">
<table border="1">
<tr><td>글쓴이</td>
<td><input type="text" name="name" value="<%=fb.getName()%>"></td></tr>
<tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
<tr><td>제목</td>
<td><input type="text" name="subject" value="<%=fb.getSubject()%>"></td></tr>
<tr><td>파일</td><td><input type="file" name="file"></td></tr>
<tr><td>내용</td>
<td><textarea name="content" cols="80" rows="40"><%=fb.getContent() %></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="글수정">
<input type="reset" value="초기화"></td></tr>
</table>
</form>
</body>
</html>