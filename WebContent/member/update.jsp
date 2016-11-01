<%@page import="net.member.db.MemberBean"%>
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
//세션값 가져오기 String id=
String id=(String)session.getAttribute("id");
//세션값없으면 loginForm.jsp이동
if(id==null){
	response.sendRedirect("./MemberLogin.mc");
}
//request.setAttribute("mb", mb);
//MemberBean mb= 
MemberBean mb=(MemberBean)request.getAttribute("mb");
%>
<h1>member/updateForm.jsp</h1>
<form action="./MemberUpdateAction.mc" method="post" enctype="multipart/form-data">
아이디:<input type="text" name="id" value="<%=id%>" readonly><br>
비밀번호:<input type="password" name="pass"><br>
이름:<input type="text" name="name" value="<%=mb.getName()%>"><br>
이메일:<input type="text" name="email" value="<%=mb.getEmail()%>"><br>
파일:<input type="file" name="file"><br><br>
<input type="submit" value="회원수정">
</form>
</body>
</html>



