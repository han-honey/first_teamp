<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
	MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
	%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>
<script type="text/javascript">
function mailCheck() {    
	var email = document.getElementById("newMail").value;
    var regex =  [\w-\.]+)@((?:[\w]+\.)+)([a-zA-Z]{2,4};

    if(regex.exec(email)) {
		alert("사용가능");
		return true;
	} else {
		alert("사용불가능");
		return false;
	}
}
</script>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>

<body>
	<jsp:include page="../inc/top.jsp" />
	<div align="center" >
		
		  	<h2>회원정보 수정</h2>
		  	
		  	<div id="member_info" style="display: inline-block; padding : 20px 50px 50px 0px;">
		  	<form action="./UpdateMemberInfo.me" method="post" onsubmit="return mailCheck()">
			<table border="1" >
				<tr>
					<td>회원아이디</td>
					<td>${memberDTO.getMember_id() } (수정불가)</td>
					<td>이름</td>
					<td>${memberDTO.getMember_name() } (수정불가)</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" value="${memberDTO.getMember_email() }" id="newMail"></td>
					<td>연락처</td>
					<td><input type="text" name="phone" value="${memberDTO.getMember_phone() }"></td>
				</tr>
				<tr>
					<td>마케팅 수신동의</td>
					<td><input type="radio" name="marketing_agree" value="동의">동의 
					<input type="radio" name="marketing_agree" value="비동의">동의 안함 
					</td>
					<td colspan="3"><input type="button" value="비밀번호 변경" onclick="locaton.href='./ChangePasswordForm.me'"></td>
				</tr>
			</table>
					<input type="submit" value="확인" class="btn" >
				<span>|</span>
				<input type="button" value="취소" class="btn"  onclick="location.href='./MemberInfo.me'">
			</form>
			</div>
		</div>
	<jsp:include page="../inc/bottom.jsp" />
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.nice-select.min.js"></script>
    <script src="../js/jquery-ui.min.js"></script>
    <script src="../js/jquery.slicknav.js"></script>
    <script src="../js/mixitup.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/main.js"></script>
    <script src="../js/jquery-3.6.0.js"></script>
</body>
</html>


