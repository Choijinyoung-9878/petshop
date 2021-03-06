<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Insert title here</title>
  <link href="${conPath }/css/MyPageMenu.css" rel="stylesheet">
 <style>
 	 
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		
 	});
 </script>  
</head>
  <body>
	<div id="navigation">
		<ul class="menu">
			<li>
				<a href="#">마이 페이지</a>
				<ul class="submenu">
					<li><a href="${conPath }/modifyView.do?modify=all">개인정보 변경</a></li>  
					<li><a href="${conPath }/modifyView.do?modify=pw">비밀번호 변경</a></li>
					<li><a href="#">수신 설정</a></li>
				</ul>
			</li>
			<li>
				<a href="#">내 찜 목록</a>
				<ul class="submenu">
					<li><a href="#">강아지 찜 목록 </a></li>
					<li><a href="#">예약 조회</a></li>
					<li><a href="#">배송 조회</a></li>
				</ul>
			</li>
			<li>
				<a href="#">분양/예약 조회</a>
				<ul class="submenu">
					<li><a href="#">분양 조회 </a></li>
					<li><a href="#">예약 조회</a></li>
					<li><a href="#">배송 조회</a></li>
				</ul>
			</li>
			<li>
				<a href="#">회원탈퇴</a>
				<ul class="submenu">
					<li><a href="#">회원 탈퇴</a></li>
				</ul>
			</li>
		</ul>
	</div>
  </body>
</html>