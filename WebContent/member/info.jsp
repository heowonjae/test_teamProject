<%@page import="net.member.db.MemberBean"%>
<%@page import="net.member.db.MemberDAO"%>
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
request.setCharacterEncoding("utf-8");
//세션값 가져오기 String id=
String id=(String)session.getAttribute("id");
//세션값없으면 loginForm.jsp이동
if(id==null){
	response.sendRedirect("loginForm.jsp");
}
//MemberDAO 객체 생성  mdao
MemberDAO mdao=new MemberDAO();
//MemberBean mb =메서드호출 getMember(id)
MemberBean mb=mdao.getMember(id); 
%>
<h1>member/info.jsp</h1>
아이디:  <%=mb.getId() %><br>
비밀번호: <%=mb.getPass() %> <br>
이름: <%=mb.getName() %> <br>
첨부파일:<br>
<img src="./upload/<%=mb.getFile().split(",")[0]%>" width="300" height="250">
<br>
<a href="./MemberMain.mc">메인화면으로 이동</a>
</body>
</html>