
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://cdn.socket.io/socket.io-1.3.5.js"></script>
<style>
#chat_panel{margin:10px; width:450px;height:500px;border:1px solid black; background:white;}
#chatinput{margin:10px; width:390px;}
#chatwindow{width:470px; height:570px; border:1px solid black; background: #bdbdbd;}
</style>
<script>
var socket = io.connect('http://192.168.2.250:5000');

$(function(){
	
	$('input').focus();
	
	$(function(){
		$('input').keydown(function(event){
			if(event.which == 13){
				socket.emit('chat',$('.chatinput').val());
				$('.chatinput').val('');				
			}
		})
	})
	
	$('button').click(function(){
		socket.emit('chat',$('.chatinput').val());
		$('.chatinput').val('');
	});
		
	
	socket.on('chat',function(data){
		$('#chat_panel').append(data+"<br>");
	});

});
</script>
</head>
<body>
<h1>Messenger</h1>
<div id="chatwindow">
<div id="chat_panel"></div>
<input id="chatinput" type="text" class="chatinput"><button type="button">Send</button>
</div>
</body>
</html>