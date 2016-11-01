<%@page import="net.ToDo.db.ToDoBean"%>
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
	<%
		ToDoBean tb =(ToDoBean)request.getAttribute("tb");
		String pageNum =(String)request.getAttribute("pageNum");
		System.out.println("pageNum="+pageNum);
		
		
		String str1=tb.getDeadline();
        String[] str2 = null;
        String str3=null;
        str2 = str1.split("-");
        
        for(int i=0; i<str2.length; i++) {
        	System.out.println(str2[i]);
        }
        
       str3=str2[1]+"/"+str2[2]+"/"+str2[0]; 
       System.out.println("str3"+str3);
	%>
	<h1>update.jsp</h1>

	<h1>[할 일 수정]</h1>
	<form action="./ToDoUpdateAction.td?pageNum=<%=pageNum %>" method="post">
		<input type="hidden" name="num" value="<%=tb.getNum()%>">
		
		<table border="1">

			<tr>
				<td colspan="2"><select name="category">
						<option value="0" >완료됨</option>
						<option value="1" >진행중</option>
						<option value="2" >일1</option>
						<option value="3" >일2</option>
						<option value="4" selected>없음</option>
				</select></td>
				</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="id" value="<%=tb.getId()%>"></td>
			</tr>
			<tr>
				<td>중요 여부</td>
				<td><input type="checkbox" name="important" value="1"> </td>
			</tr>
			<tr>
			<tr>
				<td>할 일</td>
				<td><textarea rows="5" cols="20" name="content"><%=tb.getContent()%></textarea></td>
			</tr>
			<tr>
				<td>기간</td>
				<td><input type="date" name="deadline"
					value="<%=str3%>">
			</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록"> <input
					type="reset" value="초기화"></td>
			</tr>
		</table>
	</form>
</body>
</html>