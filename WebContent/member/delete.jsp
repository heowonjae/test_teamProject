<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
//세션가져오기
String id=(String)session.getAttribute("id");
//세션값 없으면 loginForm.jsp이동
if(id==null){
	response.sendRedirect("./MemberLogin.mc");
}
%>
<h1>member/deleteForm.jsp</h1>
<form action="./MemberDeleteAction.mc" method="post">
아이디:
<input type="text" name="id" value="<%=id%>" readonly><br>
비밀번호:<input type="password" name="pass"><br>
<input type="submit" value="회원삭제">
</form>
</body>
</html>