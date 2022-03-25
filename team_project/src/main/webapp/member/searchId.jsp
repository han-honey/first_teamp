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
		  	<h1>이메일로 아이디 찾기</h1>
		  	<form action="./SearchIdPro.me" method="post" id="join" name="fr" onsubmit="return checkForm()">
		  	<div style="width: 600px;">
		  		<table style="text-align: justify;">
		  			<tr>
		  			<th colspan="2">가입 시 입력한 정보를 입력해주세요.</th>
		  			</tr>
		  			<tr>
		  			<td>이름</td>
		  			<td><input type="text" name="name" required="required"></td>
		  			</tr>
		  			<tr>
		  			<tr>
		  			<td>이메일</td>
		  			<td><input type="text" name="email" required="required" >
		  			</td>
		  			</tr>
		  			<tr>
		  			<td colspan="2">
		  			<input type="submit" value="확인" class="btn" >
		  			<span>|</span>
		  			<input type="button" value="취소" class="btn"  onclick="history.back()">
		  			</td>
		  			</tr>
		  		</table>
		  		</div>
		  	</form>
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


