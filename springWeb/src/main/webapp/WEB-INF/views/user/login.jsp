<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h3>로그인</h3>
	<form action="login">
		id <input type="text" name="id" value="${user.id }"> <br>
		password <input type="text"	name="password" value="${user.password}"> <br>
		<button>로그인</button>
	</form>
</body>
</html>