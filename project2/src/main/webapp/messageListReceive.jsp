<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/message.css">
<script type="text/javascript" src="resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript"> 
/* 보낸 쪽지함 - 들어오자마자 리스트가 뜨는 상황 */
	$(function() {
		$.ajax({
			url : 'messageListReceive', /* 컨트롤러와 주소일치!! */
		    data : {
				IDRE : $('#IDRE').val(),
			},
			success : function(result) {
				$('#result').html(result)
	        }
		})
	})
</script>
</head>
<!-- view부분!! -->
<h3>받은 쪽지함</h3>
<hr color="red">
	<input type="text" id="IDRE" value="<%=session.getAttribute("Mid")%>" readonly hidden><br>
	<div id = 'result'></div><br>
	<!-- 세션 임시 부여 중. 원래는 로그인중인 세션으로 받아와야함. -->
<a href = "messageListSend.jsp">보낸 쪽지함</a>
</body>
</html>