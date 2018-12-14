<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>게시글 보기</h3>
	제목 :${board.seq} ${board.title}<br>
	내용 :${board.content} <br>
	작성자 :${board.writer}<br>
	작성일자 :${board.regdate} <br>
	조회수 :${board.cnt} <br><hr>
	
	<a href ="./updateBoardform?seq=${board.seq}">수정</a>
	<a href="./deleteBoard?seq=${board.seq}">삭제</a>
</body>
</html>