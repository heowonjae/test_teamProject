<%@page import="net.FComment.db.FComBean"%>
<%@page import="java.util.List"%>
<%@page import="net.FComment.db.FComDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./FCommentWriteAction.fco" method="post">
	<table >
		<tr>
			<td colspan="4"><input type="text" id="commentParentName"
				name="name" data-rule-required="true" placeholder="이름" maxlength="10">
			<input type="password" id="commentParentPassword"
				name="pass" data-rule-required="true" placeholder="패스워드" maxlength="10">
			<input type="submit" value="확인" >
			</td>
			</tr>
			<tr>
			<td colspan="4">
			<textarea rows="4" name="content" style="width: 388px;"></textarea>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>