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
	<h3>회원 목록 조회</h3>

	<form>
		<select name="searchCondition">
			<option value="id">아이디
			<option value="name">이름
		</select> <input type="text" name="searchKeyword">
		<button>검색</button>
	</form>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>비밀번호</td>
			<td>역할</td>
		</tr>
		
		<c:forEach items="${userList}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.name}</td>
			<td>${user.password}</td>
			<td>${user.role}</td>
		</tr>
		
		</c:forEach>
	</table>
</body>
</html>