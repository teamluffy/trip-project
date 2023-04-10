<%@page import="db.PublicWifi"%>
<%@page import="java.util.List"%>
<%@page import="db.PublicWifiService"%>
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
		
		td {
			border: 0.5px solid #d3d3d3;
			text-align: center;
			padding: 30px;
		}
	</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	
	<br>
	<div>
		LAT: <input type="text" name = "lat" size = "20">
		, LNT: <input type="text" name = "lnt" size = "20">
		
		<button id="curLocation" onclick="">내 위치 가져오기</button>
		<button id="closeWifi" onclick="">근처 WIFI 정보 보기</button>
	</div>
		
	<table>
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<td colspan="17"><b>위치 정보를 입력한 후에 조회해 주세요.</b></td>
		</tbody>
	</table>
	<%
	%>
</body>
</html>