<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
 <script type="text/javascript">
 	function idcheck() {
 		//id상자의 value값
 		fid=document.fr.id.value;
 		//id상자 비어있으면 "아이디입력하세요"  //id 포커스 
 		if(fid==""){
 			alert("아이디입력하세요");
 			document.fr.id.focus();
 			return;
 		}
		//창열기
		//window.open("파일이름","창이름","창크기,위치...");
 	window.open("./MemberId_Check.mc?userid="+fid,"","width=400,height=200");
	}
 </script>
</head>
<body>

<form action="./MemberJoinAction.mc" method="post" enctype="multipart/form-data" name="fr">
아이디:<input type="text" name="id"><br>
<input type="button" value="check" class="dup" onclick="idcheck()"><br>
비밀번호:<input type="password" name="pass"><br>
이름:<input type="text" name="name"><br>
이메일:<input type="text" name="email"><br>
파일:<input type="file" name="file"><br>
<input type="submit" value="회원가입"><input type="reset" value="초기화">
</form>
</body>
</html>