<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./JoinAction.do" method="post">
		<table>
			<tr>
				<td><label>아이디</label></td>
				<td>
					<input type="text" name="id">
				</td>
			</tr>
			<tr>
				<td><label>비밀번호</label></td>
				<td>
					<input type="password" name="pass">
				</td>
			</tr>
			<tr>
				<td><label>이름</label></td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td><label>E-Mail</label></td>
				<td>
					<input type="email" name="email">
				</td>
			</tr>
			<tr>
				<td><label>주소</label></td>
				<td>
					<input type="text" name="address">
				</td>
			</tr>
			<tr>
				<td><label>전화번호</label></td>
				<td>
					<input type="tel" name="phone">
				</td>
			</tr>
			<tr>
				<td><label>핸드폰번호</label></td>
				<td>
					<input type="tel" name="mobile">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="가입">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>