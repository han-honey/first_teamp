<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="../css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../css/style.css" type="text/css">
 
</head>

<body>
<%
MemberDTO memberDTO = (MemberDTO)request.getAttribute("memberDTO");
%>

<jsp:include page="../inc/top.jsp" />
		
		  <!-- 본문 내용 -->
		  <div align="center">
		  	<h4>비밀번호 찾기 결과</h4>
		  		<c:choose>
		  		<c:when test="${memberDTO !=null }">
		  	<div style="width: 600px;">
		  		${memberDTO.member_id } 님의 임시 비밀번호가 ${memberDTO.member_email } 주소로 전송되었습니다.
		  			<br> 메일의 임시 비밀번호를 확인하여 로그인 하신 후, 비밀번호 변경을 진행해주세요.
		  		</div>
		  		<div class="button_section">
     			   <span><a href="./MemberLoginForm.me">로그인 하기</a></span>
        			 <span>|</span>
        			<span><a href="./MemberAgreeForm.me">회원가입</a></span>
   				 </div>
		  		</c:when>
		  		<c:otherwise>
		  		<div style="width: 600px;">
		  			입력하신 정보로 조회된 가입 이력이 없습니다.
		  			<br> 회원가입을 진행해주시거나 이메일 주소를 재확인해주세요.
		  		</div>
		  		<div class="button_section">
     			   <span><a href="./SearchPasswordForm.me">다른 이름으로 비밀번호 찾기</a></span>
       				 <span>|</span>
        			<span><a href="./MemberAgreeForm.me">회원가입</a></span>
   				 </div>
		  		</c:otherwise>
		  		</c:choose>
		  		
		</div>
		
	<jsp:include page="../inc/bottom.jsp" />
	
	   <!-- Js Plugins -->
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


