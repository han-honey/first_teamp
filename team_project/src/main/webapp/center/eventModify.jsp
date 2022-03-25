<%@page import="vo.BoardEventDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
<title>Insert title here</title>
</head>
<body>
	<%
	String id = (String)session.getAttribute("sessionId");
	if(id == null) {
		%>
		<script>
			alert("회원만 접근 가능합니다.");
			history.back();
		</script>
		<%
 	}
	
	int idx = Integer.parseInt(request.getParameter("idx"));
	
	if(request.getParameter("idx") == null) {
		%>
		<script>
			alert("잘못된 접근 입니다.");
			history.back();
		</script>
		<%
		}
			BoardEventDTO board = (BoardEventDTO)request.getAttribute("board");
		%>	
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더 들어가는곳 -->

		<!-- 본문 내용 -->
		<form action="./EventModifyPro.bo?idx=${board.getEvent_idx() }&page=${page}" method="post" enctype="multipart/form-data">
			<div class="container">
				<h2>이벤트 수정</h2>
				<table class="table table-hover">
				<div>
				<input type="submit" class="site-btn" value="글 수정" style="float: right; margin-right: 10px;" >
				</div>
					<tbody>
						<tr>
							<td><input type="file" class="form-control"
								name="contentfile" value="${board.getEvent_img() }" ></td>
						</tr>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글 제목" name="subject" maxlength="40"
								value="${board.getEvent_subject() }" required="required"></td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td><input type="text" class="form-control" -->
<!-- 							placeholder="비밀번호" name="contentTitle" maxlength="40" required="required"></td> -->
<!-- 						</tr> -->
						<tr>
							<td><textarea class="form-control"
									placeholder="글 내용을 작성하세요" name="content" maxlength="1024"
									style="height: 400px; resize: none;" required="required">${board.getEvent_content() }</textarea></td>
						</tr>
					</tbody>	
				</table>		
			</div>
		</form>

		
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
    <script src="../js/jquery-3.3.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.nice-select.min.js"></script>
    <script src="../js/jquery-ui.min.js"></script>
    <script src="../js/jquery.slicknav.js"></script>
    <script src="../js/mixitup.min.js"></script>
    <script src="../js/owl.carousel.min.js"></script>
    <script src="../js/main.js"></script>
</body>
</html>