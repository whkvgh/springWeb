<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$.post("getBoardListAjax", null, function(datas){
		for(i=0; i<datas.length; i++){
			var tr ="<tr><td>"+
					datas[i].seq +
					"</td><td>" +
					datas[i].title +
					"</td><tr>";
			$(tr).appendTo("#boardList");
			
		}
		
	});	//data 호출(url, parameter, callback함수-데이터를 저장한 변수 이름,)
	
});

</script>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody id="boardList"></tbody>
	</table>
</body>
</html>