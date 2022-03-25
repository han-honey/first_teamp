<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
MemberDTO memberDTO = (MemberDTO) request.getAttribute("memberDTO");
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
<link rel="stylesheet" href="../css/memberinfo.css" type="text/css">
</head>

<body>
	<jsp:include page="../inc/top.jsp" />
	<div align="center">

		<h2>My Page</h2>

		<div id="member_info"
			style="display: inline-block; padding: 20px 50px 50px 0px;">
			<h4 style="margin-right: 700px;">회원정보</h4>
			<table class="tb">
				<tr>
					<th scope="row">회원아이디</th>
					<td>${memberDTO.getMember_id() }</td>
					<th scope="row">항목명</th>
					<td>${memberDTO.getMember_name() }</td>			
				</tr>
				<tr>
					<th scope="row">생년월일</th>
					<td>${memberDTO.getMember_birth() }</td>
					<th scope="row">연락처</th>
					<td>${memberDTO.getMember_phone() }</td>			
				</tr>
					<tr>
					<th scope="row">이메일</th>
					<td>${memberDTO.getMember_email() }</td>
					<th scope="row">가입일</th>
					<td>${memberDTO.getMember_joinDate() }</td>			
				</tr>
					<tr>
					<th scope="row">총구입액</th>
					<td>${memberDTO.getMember_total_purchase() }</td>
					<th scope="row">회원등급</th>
					<td>${memberDTO.getMembership_grade() }</td>			
				</tr>
					<tr>
					<th scope="row">최근로그인날짜</th>
					<td>${memberDTO.getRecent_login_date() }</td>
					<th scope="row">마케팅수신동의</th>
					<td>${memberDTO.getMember_marketing_agree() }</td>
				</tr>
			</table>
		</div>

<!-- 		<div id="recent_product" -->
<!-- 			style="display: inline-block; padding: 20px 50px 50px 0px;"> -->
<!-- 			<h4>최근에 본 상품</h4> -->
<!-- 			<table class="tb"> -->
<!-- 				<tr> -->
<!-- 					<td>첫번째상품썸네일</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>두번째상품썸네일</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>세번째상품썸네일</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->

		<div id="other_page" style="padding : 20px 50px 50px 0px;">
				<input type="button" value="배송지 관리" class="btn" onclick="location.href='./MemberAddressList.me'">
				<span>|</span>
				<input type="button" value="회원정보수정" class="btn" onclick="location.href='./CheckPassword.me'"> 
				<span>|</span>
				<input type="button" value="상품주문내역" class="btn" onclick="location.href='../payment/OrderList.pa'"> 
				<span>|</span>
				<input type="button" value="관심상품리스트" class="btn" onclick="location.href='../payment/Wishlist.pa'">
				<span>|</span>
				<input type="button" value="내게시글관리" class="btn" onclick="#">
				<span>|</span>
				<input type="button" value="회원탈퇴" class="btn" onclick="location.href='./MemberDeleteForm.me'">
			</div>
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


