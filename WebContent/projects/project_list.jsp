<%@page import="com.project.bean.ProjectBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/base.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/redmond/jquery-ui.min.css" />
<style type="text/css">
#wrap {
	width: 1123px;
	margin-left: auto;
	margin-right: auto;
}

#btn_box {
	text-align: right;
}
</style>
</head>
<body>
	<div id="wrap">
		<table class="data">
			<thead>
			<tr>
				<th>체크</th>
				<th>프로젝트명</th>
				<th>프로젝트 내용</th>
				<th>시작일</th>
				<th>종료일</th>
			</tr>
			</thead>
			<tbody>
<%
			List<ProjectBean> list = (List<ProjectBean>) request.getAttribute("list");
			for (int i = 0; i < list.size(); i++) {
				ProjectBean project = list.get(i);
%>
				<tr>
					<td><input type="checkbox" value="<%=project.getP_num() %>" name="p_num"></td>
					<td><%=project.getP_name() %></td>
					<td><%=project.getP_content() %></td>
					<td><%=project.getP_start_short() %></td>
					<td><%=project.getP_end_short() %></td>
				</tr>
<%
			}
%>
			</tbody>
		</table>
		<div id="btn_box">
			<input type="button" value="프로젝트생성" id="dl">
			<input type="button" value="취소">
		</div>
		<div id="dialog" title="프로젝트생성">
			<form action="./ProjectAddAction.pr" method="post" id="frm">
			<table>
				<tr>
					<td>프로젝트명</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="5" cols="30" name="content"></textarea>
				</tr>
				<tr>
					<td>시작일</td>
					<td><input type="date" name="start"></td>
				</tr>
				<tr>
					<td>종료일</td>
					<td><input type="date" name="end"></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.min.js"></script>
<script src="js/jquery.fixheadertable.min.js"></script>
<script>
$(function() {
	$('.data').fixheadertable({
		caption		: 'project_list',		
		colratio	: [40, 300, 503, 140, 140],	
		height		: 300,
		width		: 1123,
		zebra		: false,
		sortable	: true,
		sortedColId	: 3,
		resizeCol	: true,
		pager		: true,
		rowPerPage	: 10,
		sortType	: ['', 'string', 'string', 'date', 'date'],
		dateFormat	: 'Y-m-d'
	});
	
	$('input[type="button"]').button();
	
	$('#dialog').dialog({
		width 	 : 510,
		height	 : 375,
		autoOpen : false,
		modal	 : true,
		buttons	 : {
			'생성' : function() {
				$('#frm').submit();
			},
			'닫기' : function() {
				$(this).dialog('close');
			}
		}
	});
	
	$('#dl').click(function() {
		$('#dialog').dialog('open');
	});
	
});
</script>
</body>
</html>