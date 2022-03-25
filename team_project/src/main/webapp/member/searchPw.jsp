<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<jsp:include page="../inc/top.jsp" />
		
		  <!-- 본문 내용 -->
		  <div align="center">
		  	<h4>비밀번호 찾기</h4>
		  	<form action="./SearchPasswordPro.me" method="post" >
		  	<div style="width: 600px;">
		  			<div>가입 시 등록하신 정보를 입력하시면 고객님의 변경된 비밀번호를 안내해 드립니다.</div>
		  			<div>본 서비스에서 비밀번호 찾기는 변경된 임시 비밀번호를 제공합니다. <br>
		  			임시 비밀번호로 로그인 후에는 패스워드를 변경해주세요!</div>
		  		<table style="text-align: justify;">
		  			<tr>
		  			<td>이름</td>
		  			<td><input type="text" name="name" required="required"></td>
		  			</tr>
		  			<tr>
		  			<td>아이디</td>
		  			<td><input type="text" name="id" required="required"></td>
		  			</tr>
		  			<tr>
		  			<td>이메일</td>
		  			<td><input type="text" name="email" required="required" ></td>
		  			</tr>
		  			<tr>
		  			<td colspan="2">
		  			<input type="submit" value="확인" >
		  			<span>|</span>
		  			<input type="button" value="취소" onclick="history.back()">
		  			</td>
		  			</tr>
		  		</table>
		  		</div>
		  	</form>
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


