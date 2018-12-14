<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시글 수정</h3>
	<form action="updateBoard" method="post">
		 <input type ="hidden" name ="seq" value="${board.seq}"><br>
	제목 <input type ="text" name ="title" value="${board.title}"><br>
	내용  <textarea rows="2" cols="30" name ="content">"${board.content}"</textarea><br>
	<button>저장</button>
	
	</form>
</body>
</html>