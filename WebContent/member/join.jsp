<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
 <script type="text/javascript">
 	function idcheck() {
 		//id������ value��
 		fid=document.fr.id.value;
 		//id���� ��������� "���̵��Է��ϼ���"  //id ��Ŀ�� 
 		if(fid==""){
 			alert("���̵��Է��ϼ���");
 			document.fr.id.focus();
 			return;
 		}
		//â����
		//window.open("�����̸�","â�̸�","âũ��,��ġ...");
 	window.open("./MemberId_Check.mc?userid="+fid,"","width=400,height=200");
	}
 </script>
</head>
<body>

<form action="./MemberJoinAction.mc" method="post" enctype="multipart/form-data" name="fr">
���̵�:<input type="text" name="id"><br>
<input type="button" value="check" class="dup" onclick="idcheck()"><br>
��й�ȣ:<input type="password" name="pass"><br>
�̸�:<input type="text" name="name"><br>
�̸���:<input type="text" name="email"><br>
����:<input type="file" name="file"><br>
<input type="submit" value="ȸ������"><input type="reset" value="�ʱ�ȭ">
</form>
</body>
</html>