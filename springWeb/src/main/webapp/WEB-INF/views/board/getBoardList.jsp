<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<my:userInfo>

<c:if test="${not empty sessionScope.login}">
<input type ="text" placeholder="id">
<input type ="text" placeholder="pw">
<input type="button"value="로그인">
</c:if></my:userInfo>
	<!--<c:if test="${empty sessionScope.login}">
		<a href="loginForm">로그인</a>
	</c:if>
	<!-- 로그인 ok -->
	<!--<c:if test="${not empty sessionScope.login}">
			${sessionScope.login.id}님 <a href="logout">로그아웃</a>
	</c:if>-->
	<h3>일반게시판</h3>
	<hr>
<input type="button" value="pdf 내려받기" onclick="location.href='./boardreport'"/>
<input type="button" value="엑셀 내려받기" onclick="location.href='./boardExcel'"/>
<hr>
	<!-- <a href="<%=request.getContextPath() %>/insertBoardform">등록</a><br> 페이지 경로 설정-->
	<a href="${pageContext.request.contextPath}/insertBoard">등록</a>
	<br>

	<form name="frm">
	 게시판 구분<my:boardType  optionColor="yellow"/>
		<select name="searchCondition">
			<option value="title">제목 
			<option value="content">내용
		</select>
		<input type="text" name="searchKeyword">
		<button>검색</button>
		<input type="hidden" name="sortCol">
	
	<input type="hidden" name="page" />
	</form>
	<script>
	function go_sort(sortCol){
		document.frm.sortCol.value=sorcCo;
		document.frm.submit();
	}
	</script>
	<form action="deleteBoardList">
	<button>선택 삭제</button>
	<table border="1">
		<tr>
			<td>선택</td>
			<td>번호<a href="#" onclick="go_sort('seq')">▲</a></td>
			<td>제목<a href="#" onclick="go_sort('title')">▲</a></td>
			<td>내용<a href="#" onclick="go_sort('content')">▲</a></td>
			<td>작성자<a href="#" onclick="go_sort('writer')">▲</a></td>
			<td>작성일자<a href="#" onclick="go_sort('regdate')">▲</a></td>
			<td>조회수<a href="#" onclick="go_sort('cnt')">▲</a></td>
		</tr>

		<c:forEach items="${boardList}" var="board">

			<tr>
				<td><input type="checkbox" name="seqlist" value="${board.seq}"></td>
				<td>${board.seq}</td>
				<td><a href="./getBoard?seq=${board.seq}">${board.title}</a></td>
				<td>${board.content}</td>
				<td>${board.writer}</td>
				<td>${board.regdate}</td>
				<td>${board.cnt}</td>
			</tr>
		</c:forEach>
	</table>
	
</form>
<my:paging paging="${paging}"/>
<script>
function go_page(page){
	document.frm.page.value=page;
	document.frm.submit();	//검색폼
}
</script>
</body>
</html>