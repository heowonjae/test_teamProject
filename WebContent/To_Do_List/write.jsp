<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/ui-lightness/jquery-ui-1.10.0.custom.css"
	rel="stylesheet">
<script src="./js/jquery.js"></script>
<script src="./js/jquery-ui.custom.js"></script>
<script src="./js/modernizr.js"></script>
<script>
	Modernizr.load({
		test : Modernizr.inputtypes.date,
		nope : "./js/jquery-ui.custom.js",
		callback : function() {
			$("input[type=date]").datepicker();
		}
	});
</script>
</head>
<body>
	<h1>write.jsp</h1>

	<h1>[할 일 등록]</h1>
	<form action="./ToDoWriteAction.td" method="post">
		<table border="1">
			<tr>
				<td>작성자</td>
				<td><input type="text" name="id" value=""></td>
			</tr>

			<tr>
				<td>카테고리</td>
				<td><select name="category">
						<option value="0" >완료됨</option>
						<option value="1" >진행중</option>
						<option value="2" >일1</option>
						<option value="3" >일2</option>
						<option value="4" selected>없음</option>
				</select></td>
			</tr>

			<tr>
				<td>중요 여부</td>
				<td><input type="checkbox" name="important" value="1"></td>
			</tr>
			<tr>
			<tr>
				<td>할 일</td>
				<td><textarea rows="5" cols="20" name="content"></textarea></td>
			</tr>
			<tr>
				<td>기간</td>
				<td><input type="date" name="deadline"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록"> <input
					type="reset" value="초기화"></td>
			</tr>
		</table>
	</form>

</body>
</html>