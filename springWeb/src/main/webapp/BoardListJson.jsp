<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	width: 45%;
	border: 1px solid blue;
	min-height: 300ps;
	display: inline-block;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function(){
	boardList();	//전체 조회 실행
	boardDelete(); //삭제 버튼에 이벤트 지정
	boardSelect(); //조회버튼에 이벤트 지정
	userUpdate();	
	boardInsert();
})
	//사용자 목록 조회 요청
	function boardList() {
		$.ajax({
			url : 'board',
			type : 'GET', //메소드는 get 방식으로 넘어감.
			dataType : 'json', //결과값
			error : function(xhr, status, msg) {
				alert("상태값 :" + status + " Http에러메시지 :" + msg);
			},
			success : userListResult	
		});
	}//userList

	//사용자 목록 조회 응답
	function userListResult(data) {	//json r결과값이 넘어옴
		console.log(data);	
		$("tbody").empty();
		$.each(data, function(idx, item) {		//index. 값정보 가져옴
			$('<tr>')
			.append($('<td>').html(item.seq))//<td>item 내용</td>
			.append($('<td>').html(item.writer))
			.append($('<td>').html(item.title))
			.append($('<td>').html('<button id=\'btnSelect\'>조회</button>'))
			.append($('<td>').html('<button id=\'btnDelete\'>삭제</button>'))
			.append($('<input type=\'hidden\' id=\'hidden_seq\'>').val(item.seq))
			.appendTo('tbody');
		});//each
	}//userListResult
	
	//사용자 삭제 요청
	function boardDelete() {
		//삭제 버튼 클릭
		$('body').on('click','#btnDelete',function(){
			var seq = $(this).closest('tr').find('#hidden_seq').val();	//이벤트 클릭시 부모tag에서 위임된 이벤트 실행됨.
			var result = confirm(seq +" 사용자를 정말로 삭제하시겠습니까?");
			if(result) {
				$.ajax({
					url:'board/'+seq, 
					type:'DELETE',	
					dataType:'json',
					error:function(xhr,status,msg){
						console.log("상태값 :" + status + " Http에러메시지 :"+msg);
					}, success:function(xhr) {
						console.log(xhr.result);
						 boardList();	//전체 조회
					}
				});      }//if
		}); //삭제 버튼 클릭
	}//userDelete
	
	
	//사용자 조회 요청
	function boardSelect() {
		//조회 버튼 클릭
		$('body').on('click','#btnSelect',function(){
			var seq = $(this).closest('tr').find('#hidden_seq').val();
			//특정 사용자 조회
			$.ajax({
				url:'board/'+seq,
				type:'GET',
				dataType:'json',
				error:function(xhr,status,msg){
					alert("상태값 :" + status + " Http에러메시지 :"+msg);
				},
				success:userSelectResult	//성공할 때 넘어감
			});
		}); //조회 버튼 클릭
	}//userSelect
	
	//사용자 조회 응답
	function userSelectResult(board) {
		$('input:text[name="seq"]').val(board.seq);
		$('input:text[name="writer"]').val(board.writer);
		$('input:text[name="title"]').val(board.title);
		$('testarea[name="content"]').val(board.content);
	//$('input:radio[name="gender"][value="'+user.gender+'"]').prop('checked', true);
	//	$('select[name="city"]').val(user.city).attr("selected", "selected");
	}//userSelectResult
	
	//사용자 수정 요청
	function userUpdate() {
		//수정 버튼 클릭
		$('#btnUpdate').on('click',function(){	//직접형 dlqpsxm
			var seq = $('input:hidden[name="seq"]').val();
			var writer = $('input:text[name="writer"]').val();
			var title = $('input:text[name="title"]').val();
			var content = $('testarea[name="content"]').val();
		/* 	var gender = $('input:radio[name="gender"]:checked').val();
			var city = $('select[name="city"]').val();	 */
			$.ajax({ 
			    url: "board", 
			    type: 'PUT', 
			    dataType: 'json', 
			    data: JSON.stringify({ seq: seq, writer: writer, title: title,  content: content }),
			    contentType : 'application/json', 
			    success: function(data) { 
			        boardList();
			    },
			    error:function(xhr, status, message) { 
			        alert(" status: "+status+" er:"+message);
			    }
			});
		});//수정 버튼 클릭
	}//userUpdate
	
	//사용자 등록 요청
	function boardInsert(){
		//등록 버튼 클릭
		$('#btnInsert').on('click',function(){
			var writer = $('input:text[name="writer"]').val();
			var title = $('input:text[name="title"]').val();
			var content = $('testarea[name="content"]').val();
			/* var gender = $('input:radio[name="gender"]:checked').val();
			var city = $('select[name="city"]').val();		 */
			$.ajax({ 
			    url: "users",  
			    type: 'POST',  
			    dataType: 'json', 
			    data: JSON.stringify({writer:writer, title: title, content: content }),
			    contentType: 'application/json', 
			    mimeType: 'application/json',
			    success: function(response) {
			    	if(response.result == true) {
			    		userList();
			    	}
			    }, 
			    error:function(xhr, status, message) { 
			        alert(" status: "+status+" er:"+message);
			    } 
			 });  
		});//등록 버튼 클릭
	}//userInsert
	
</script>
</head>
<body>
	<div>목록
		<table class="table text-center">
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div>등록/수정폼
	<form action="./insertBoard" method="post">

		작성자 <input type="text" name="writer"><br>
		제목 <input type="text" name="title"><br> 
		내용 	<textarea rows="2" cols="30" name="content"></textarea><br>
	<button  type="button" id="btnInsert">입력</button>
	<button  type="button" id="btnUpdate">수정/등록폼</button>


	</form>
	</div>
</body>
</html>