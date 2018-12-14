<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="optionColor" type="java.lang.String"
	required="true" %>
<!-- attribute 가 기본 값으로 설정되어서 페이지에 없으면 에러뜸 -->
<select name="boardType" style="background-color:${optionColor}">

<option>일반 게시판
<option> 공지사항
<option>자료게시판
</select>