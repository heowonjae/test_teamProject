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
//���ǰ�������
String id=(String)session.getAttribute("id");
//���ǰ� ������ loginForm.jsp�̵�
if(id==null){
	response.sendRedirect("./MemberLogin.mc");
}
%>
<h1>member/deleteForm.jsp</h1>
<form action="./MemberDeleteAction.mc" method="post">
���̵�:
<input type="text" name="id" value="<%=id%>" readonly><br>
��й�ȣ:<input type="password" name="pass"><br>
<input type="submit" value="ȸ������">
</form>
</body>
</html>