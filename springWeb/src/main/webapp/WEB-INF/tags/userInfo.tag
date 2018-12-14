<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- userInfo.tag --%>
<c:if test="${not empty sessionScope.login}">
${login.userid} 님 환영.<a href="Logout">로그아웃</a>
</c:if>

<c:if test="${empty sessionScope.login}">
<input type ="text" placeholder="id">
<input type ="text" placeholder="pw">
<input type="button"value="로그인">
</c:if>