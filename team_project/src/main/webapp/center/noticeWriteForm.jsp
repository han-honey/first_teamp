<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

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
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	
	String id = (String)session.getAttribute("sessionId");
	%>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 들어가는곳 -->
	<div class="container">
		<div id="questionWriteInfo">
			<form action="./NoticeWritePro.bo" method="post" enctype="multipart/form-data">
				<table border="1" id="notice" class="table table-hover">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="name" value="관리자" readonly="readonly" class="form-control"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="subject" required="required" class="form-control"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="10" cols="20" name="content" required="required" class="form-control"></textarea></td>
					</tr>
					<tr>
						<td>이미지 파일</td>
						<td><input type="file" name="img" class="form-control"></td>
					</tr>
				</table>
				<input type="submit" value="글 쓰기" class="form-control">
				<input type="button" value="뒤로 가기" onclick="history.back()" class="form-control">
			</form>
		</div>
	</div>
	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/bottom.jsp" />
	<!-- 푸터 들어가는곳 -->
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