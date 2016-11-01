<%@page import="net.FComment.db.FComDAO"%>
<%@page import="net.FComment.db.FComBean"%>
<%@page import="java.util.List"%>
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
String fsub = request.getParameter("fsub");
FComDAO cdao = new FComDAO();
List commentList = cdao.getCommentList(fsub); 
int count = cdao.getCommentCount();
%>
<table border="1">
<tr><td>Name</td><td>Content</td></tr>
<%
if(count !=0){
	for(int i=0; i<commentList.size(); i++){
		FComBean cb =(FComBean) commentList.get(i);
		%><tr><td><%=cb.getName() %></td><td><%=cb.getContent() %></td></tr><%
	}
}
%>
</table>
</body>
</html>