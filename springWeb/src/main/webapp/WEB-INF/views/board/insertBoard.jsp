<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>게시글 등록</h3>
<form action="./insertBoard" method="post" enctype="multipart/form-data">
		
	작성자 <input type ="text" name ="writer"><br><!-- name명은 vo에 있는 필등명과 동일하게 입력 -->
	제목 <input type ="text" name ="title"><br>
	내용  <textarea rows="2" cols="30" name ="content"></textarea>
	첨부파일<input type="file" name ="uploadFile"><br>
	<button>저장</button>
	
	
	</form>
</body>
</html>