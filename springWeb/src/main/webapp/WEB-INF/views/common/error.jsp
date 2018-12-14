<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    isErrorPage="true"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--<%=exception.getMessage() %><!-- 페이지가 에러일 경우에만 사용가능 , 아무거나 쓰면됨 -->
<h3>집에 보내주가...... </h3>
${exception.message}
</body>
</html>