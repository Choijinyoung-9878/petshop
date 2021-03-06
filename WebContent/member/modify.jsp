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
  <link href="${conPath }/css/modify.css" rel="stylesheet">
 <style>
   	.AllForm {
   		height:700px;
   	}
 </style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script>
 	$(document).ready(function(){
 		$('form').submit(function(){
 			var chk = $('#chk').val();
 			if(chk == 'all'){
 				$('form').attr('action','${conPath }/modifyAllView.do?modify=${param.modify }');
 			} else if(chk == 'pw'){
 				$('form').attr('action','${conPath }/modifyAllView.do?modify=${param.modify }');
 			}
 		});
 	});
 </script>  
</head>
  <body>
	<jsp:include page="../main/header.jsp" />
	<div class="AllForm">
	<jsp:include page="../main/MyPageMenu.jsp"/>	
	<div id="form">
		<form action="${conPath }/modifyAllView.do" method="post">
			<input type="hidden" id="chk" name="chK" value="${param.modify }">
			<table id="input_form">
			<caption>비밀번호 확인</caption>
			 <tr>
			 	<th>비밀번호</th>
			 	<td>
			 		<input type="password" name="mpw">
			 	</td>
			 </tr>
			 <tr>
			 	<td colspan="2">
			 		<input type="submit" value="확인" class="btn">
			 	</td>
			 </tr>
			</table>
		</form>
	</div>	
	</div>
	<jsp:include page="../main/footer.jsp" />
  </body>
</html>