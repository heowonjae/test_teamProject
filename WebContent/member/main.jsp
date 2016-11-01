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
//세션값 가져오기
String id=(String)session.getAttribute("id");
//세션값이 없으면 loginForm.jsp이동
if(id==null){
	response.sendRedirect("./MemberLogin.mc");
}
%>
<h1>member/main.jsp</h1>
<%=id %>님 로그인 하셨습니다.<br>
<input type="button" value="로그아웃" 
       onclick="location.href='./MemberLogout.mc'"><br>

<a href="./MemberInfo.mc">회원정보조회</a><br>      
<a href="./MemberUpdate.mc">회원정보수정</a><br>
<a href="./MemberDelete.mc">회원탈퇴</a><br>

</body>
</html>






