<%@page import="net.FComment.db.FComBean"%>
<%@page import="java.util.List"%>
<%@page import="net.FBoard.db.FBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// request.setAttribute("fb", fb);
FBoardBean fb=(FBoardBean)request.getAttribute("fb");
//request.setAttribute("pageNum", pageNum);
String pageNum=(String)request.getAttribute("pageNum");
String fsub = (String) request.getAttribute("fsub");
%>
<h1>board/content.jsp</h1>
<table border="1">
<tr><td>글번호</td><td><%=fb.getNum() %></td>
    <td>조회수</td><td><%=fb.getReadcount() %></td></tr>
<tr><td>작성자</td><td><%=fb.getName() %></td>
<td>작성일</td><td><%=fb.getDate() %></td></tr>
<tr><td>글제목</td><td colspan="3"><%=fb.getSubject() %></td></tr>
<tr><td>첨부파일</td><td colspan="3"><%
if(fb.getFile()!=null){
	%><a href="../upload/<%=fb.getFile()%>"><%=fb.getFile()%></a>
	 <img src="../upload/<%=fb.getFile()%>" width="20" height="20"><%
}
%></td></tr>
<tr><td>글내용</td><td colspan="3"><%=fb.getContent() %></td></tr>
<tr><td colspan="4">
<input type="button" value="글수정" 
onclick="location.href='./FBoardUpdate.fbo?num=<%=fb.getNum()%>&pageNum=<%=pageNum%>'">
<input type="button" value="글삭제" 
onclick="location.href='./FBoardDelete.fbo?num=<%=fb.getNum()%>&pageNum=<%=pageNum%>'">
<input type="button" value="글목록" 
onclick="location.href='./FBoardList.fbo?pageNum=<%=pageNum%>'"></td></tr>
</table>
<table>

</table>
<jsp:include page="comment_list.jsp">
<jsp:param value="<%=fb.getSubject()%>" name="fsub"/>
</jsp:include>
<form action="./FCommentWriteAction.fco" method="post">
<input type="hidden" name="fsub" value="<%=fb.getSubject()%>">
<input type="hidden" name="fnum" value="<%=fb.getNum()%>">
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




