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
	/* 보낸 쪽지함 - 들어오자마자 리스트가 뜨는 상황(비동기식 - ajax사용) */
	$(function() {
		$.ajax({
			url : 'listSend', /* 컨트롤러와 주소일치!! */
			data : {
				IDSEND : $('#IDSEND').val(),
			},
			success : function(json) {
				$(json).each(function(index, vo) { // foreach문을 사용하여 리스트에 쌓인 각 컬럼의 데이터를 반복해서 출력
					
					console.log(vo) // 에러 확인을 위해 콘솔에 찍어봄

					item = "<tr><td style='width: 50px;'>"+vo.mid+"</td><td style='width: 100px;'>"+vo.idre+"</td><td style='width: 1000px;'>"+vo.content+"</td><td style='width: 200px;'>"+vo.mtime+"</td></tr>"
					$('#result').append(item)
				})
			}

		})
		
		/* 쪽지 삭제 */
		$('#messageDelete').click(function() { // 버튼 클릭시 실행
			if($('#MID').val().replace(/|s/g, "").length == 0){ // 공백일 경우 실행을 안되게 하기 위함.
					alert('빈칸 없이 입력란을 작성하세요.')
			}else{
				var result = confirm('보냈던 메시지를 삭제/취소 하시겠습니까?'); // 정말로 삭제할지 재확인
					if (result) { //yes(확인)
						location.href="messageDelete?MID=" + $('#MID').val() 
							} else { //no(취소), 아무일도 일어나지 않음
							}
				}
			})

	})
</script>
</head>
<!-- view부분!! -->
<h3>보낸 쪽지함</h3>
<hr color="red">
로그인 중인 발신자:
<input type="text" id="IDSEND" value="<%=session.getAttribute("Mid")%>" readonly>
<br>

<hr>
<table border="1" id="result" class="type02">
	<tr align ="center">
		<th style='width: 50px;'>번호</th>
		<th style='width: 100px;'>수신자</th>
		<th style='width: 1000px;'>내용</th>
		<th style='width: 200px;'>시각</th>
	</tr>
</table>
	<!-- 세션 임시 부여 중. 원래는 로그인중인 세션으로 받아와야함. -->

<form action='messageSearch'> <!-- form action 사용 - 페이지 넘김(동기식) -->
<input type="hidden" name="IDSEND" value="<%=session.getAttribute("Mid")%>">
수신자: <input type="text" name="IDRE" size=10 required>
<button>검색</button>
</form><br>

번호: <input id='MID' size=1 ></input>
<button id='messageDelete'>삭제하기</button><br><br>

<a href = "messageListReceive.jsp">받은 쪽지함</a>

<a href = "messageSend.jsp">쪽지 보내기</a>
</body>
</html>