<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function() {
		$('#messageSend').click(function() {
			if($('#IDRE').val().replace(/|s/g, "").length == 0 || $('#CONTENT').val().replace(/|s/g, "").length == 0){
				alert('빈칸 없이 입력란을 작성하세요.')
			}else{
			$.ajax({
				url: 'messageSend', /* 컨트롤러와 주소일치!! */
				data : {
					IDSEND : $('#IDSEND').val(), /* 데이터를 보냄*/
					IDRE : $('#IDRE').val(),
					CONTENT : $('#CONTENT').val()
				},
				success : function(result) { /* views로 넘어갔니?! */
					alert('메시지 발송이 완료되었습니다')
				}
			})}
		})
	})
</script>
</head>
<!-- view부분!! -->
<h3>메시지 보내기</h3>
<hr color="red">
	발신자: <input type="text" id="IDSEND" value="<%=session.getAttribute("Mid")%>" readonly><br> 
	<!-- 현재 임시 세션. 로그인 세션 받아올 예정 -->
	
	수신자: <input type="text" id="IDRE"><br> 
	<!-- 평소에는 공백이지만, 매칭이 될 경우 매칭 상대의 id를 가져옴 -->
	
	내용:<br>
	<textarea cols="30" rows="5" id="CONTENT"></textarea><br>
	<button id="messageSend">쪽지 보내기</button><br><br>
	<a href = "messageListSend.jsp">돌아가기</a>
</body>
</html>