<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	//댓글 수정 
	$(function() {
		loadCommentList();
		function makeCommentView(comment) {
			var div = $("<div>");
			div.attr("id", "c" + comment.seq);
			div.addClass('comment');
			div[0].comment = comment; //{id:1,.... }

			var str = "<strong class='commentName'>" + comment.name
					+ "</strong>" + "<span class='commentContent'>"
					+ comment.content + "</span>"
					+ "<button type=\"button\" class=\"btnUpdFrm\">수정</button>"
					+ "<button type=\"button\" class=\"btnDel\">삭제</button>"
			div.html(str);
			return div;
		}

		//댓글 목록 조회 요청

		function loadCommentList() {
			var params = {
				boardSeq : '${board.seq}'
			};
			$.getJSON("getCommentsList", params, function(datas) {
				for (i = 0; i < datas.length; i++) {
					var div = makeCommentView(datas[i]);
					/* "<div>" +datas[i].name + datas[i].content +"</div>"; */
					$(div).appendTo("#commentslist");
				}

			});
		}

		//댓글 등록 처리 이벤트
		$("#btnAdd").click(function() {
			var params = $("#addForm").serialize();
			console.log(params);
			$.getJSON("insertComments", params, function(datas) {
				var div = makeCommentView(datas);
				$(div).prependTo("#commentslist");

			})
		}); //end btnadd click event
	
		//댓글 삭제 이벤트
		$("#commentslist").on("click", ".btnDel", function() {
			var seq = $(this).parent().attr("id").substr(1);
			if (confirm("삭제할까요?")) {
				var params = "seq=" + seq; // { seq : seq };
				var url = "deleteComments";
				$.getJSON(url, params, function(datas) {
					$('#c' + datas.seq).remove(); //tag 지우기
				});
			}
		});
	}); //$() end ready event

	
</script>
</head>
<body>
	<h3>게시글 보기</h3>
	<br> 제목 :${board.seq} ${board.title}
	<br> 내용 :${board.content}
	<br> 작성자 :${board.writer}
	<br> 작성일자 :${board.regdate}
	<br> 조회수 :${board.cnt}
	<c:if test="${not empty board.uploadFileName}">
	<a href="FileDown?atchFileId=${board.uploadFileName}">${board.uploadFileName}</a>
	<img src="./resources/${board.uploadFileName} "/>
	
	</c:if>
	<a href="./updateBoardform?seq=${board.seq}">수정</a>
	<a href="./deleteBoard?seq=${board.seq}">삭제</a>

	<hr>
	<h3>댓글</h3>
	<div id="commentslist"></div>

	<!-- 댓글등록시작 -->
	<div id="commentAdd">
		<form name="addForm" id="addForm">
			<input type="hidden" name="boardSeq" value="${board.seq}">
			이름: <input type="text" name="name" size="10"><br /> 내용:
			<textarea name="content" cols="20" rows="2"></textarea>
			<br /> <input type="button" value="등록" id="btnAdd" />
		</form>
	</div>
	<!-- 댓글등록끝 -->
</body>
</html>