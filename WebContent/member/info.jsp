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
//���ǰ� �������� String id=
String id=(String)session.getAttribute("id");
//���ǰ������� loginForm.jsp�̵�
if(id==null){
	response.sendRedirect("loginForm.jsp");
}
//MemberDAO ��ü ����  mdao
MemberDAO mdao=new MemberDAO();
//MemberBean mb =�޼���ȣ�� getMember(id)
MemberBean mb=mdao.getMember(id); 
%>
<h1>member/info.jsp</h1>
���̵�:  <%=mb.getId() %><br>
��й�ȣ: <%=mb.getPass() %> <br>
�̸�: <%=mb.getName() %> <br>
÷������:<br>
<img src="./upload/<%=mb.getFile().split(",")[0]%>" width="300" height="250">
<br>
<a href="./MemberMain.mc">����ȭ������ �̵�</a>
</body>
</html>