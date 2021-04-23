<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>검색된 쪽지</h3>
<hr color="red">
<table border="1" id="result">
	<tr align ="center">
	<tr>
		<td>번호</td>
		<td>수신자</td>
		<td>내용</td>
		<td>시각</td>
	</tr>
		<c:forEach var="vo" items="${messageSearch}">
		<tr>
			<td>${vo.MID}</td>
			<td>${vo.IDRE}</td>
			<td>${vo.CONTENT}</td>
			<td>${vo.MTIME}</td>
		</tr>
	</c:forEach>
</table>
<a href = "messageListSend.jsp">돌아가기</a>
</body>
</html>