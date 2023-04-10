<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<style>
			table {
				width: 100%;
				border: 1px solid #444444;
				border-collapse: collapse; 
			}
			
			th, td {
				border: 1px solid white;
				padding: 10px;
			}
			
			th {
				background-color: #3cb371;
				color: white;
			}
		</style>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	
	<br>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
</body>
</html>