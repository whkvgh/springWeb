<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
<script src="//www.google.com/jsapi"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
	var options = {
		title : '부서별 사원수',
		width : 400,
		height : 500,
		colors: ['#e0440e', '#e6693e', '#ec8f6e', '#f3b49f', '#f6c7b6'],
		legend : 'non',
		bar : {groundWidth : '95%'},
		vAxis: { gridlines: { count: 4 } }
		
	};
	google.load('visualization', '1.0', {
		'packages' : [ 'corechart' ]
	});
google.setOnLoadCallback(function() {
//차트에 넣을 data를 ajax 요청해서 가져옴
$.ajax({
			url : "./getChartData.do",
			method : "post",
			type : "json",
			success : function(data) {
				//ajax결과를 chart에 맞는 data 형태로 가공
				var chartData = [];
				chartData.push([ '사원명', '사원수', {type: 'string', role: 'tooltip'} ])		//data table  몇
				for (i = 0; i < data.length; i++) {
					var tootip = data[i].name + " : " + data[i].cnt
					var subarr = [ data[i].name, parseInt(data[i].cnt), tootip ];
					chartData.push(subarr);
				}
				//챠트 그리기
				var chart = new google.visualization.ColumnChart(document	//차트 모양 바꾸기
						.querySelector('#chart_div'));
				chart.draw(google.visualization.arrayToDataTable(chartData),
						options);

				// 이벤트 열기
				google.visualization.events.addListener(chart, 'select', selectHandler);

				function selectHandler(e) {
				  alert('A table row was selected');
				}
			}
		});
	});
</script>
</head>
<body>
	<div id="chart_div"></div>
</body>
</html>