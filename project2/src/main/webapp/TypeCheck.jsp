<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%  String tid = (String)session.getAttribute("Mid"); %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){
			$.ajax({
				url : "tCheck",
				 success: function(check) {
						if(check) {
							alert('이미 이상형 조건 입력을 완료 하였습니다')
							location.href = 'main.jsp'
						} else {
							
							location.href = 'TypeInsert.jsp'
						}
					}
			})
	
	})
</script>
</head>

<body>

</body>
</html>