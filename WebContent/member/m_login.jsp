<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./LoginAction.do" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="회원가입" onclick="javascript:location.href='./Join.do';">
					<input type="submit" value="로그인">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>