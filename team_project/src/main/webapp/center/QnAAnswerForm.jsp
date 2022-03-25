<%@page import="vo.QnADTO"%>
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
			QnADTO board = (QnADTO)request.getAttribute("board");
		%>	
	<div id="wrap">
		<!-- 헤더 들어가는곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더 들어가는곳 -->

		<!-- 본문 내용 -->
		
			
			<form action="./QnAAnswerPro.bo?idx=<%=idx %>" method="post" enctype="multipart/form-data">
				<table border="1" id="notice">
					<tr>
						<td>작성자</td>
						<td><input type="text" value="<%=board.getMember_id()%>" readonly="readonly"></td>
					</tr>
					
					<tr>
						<td>제목</td>
						<td><input type="text" name="subject" value="<%=board.getQuestion_subject()%>"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="10" cols="20" ><%=board.getQuestion_content()%></textarea></td>
					</tr>
					<tr>
						<td>기존 이미지 파일</td>
						<td><%=board.getQuestion_img() %></td>
					</tr>
					<tr>
						<td>이미지 파일(기존 파일 덮어쓰기)</td>
						<td><input type="file" value="<%=board.getQuestion_img() %>"></td>
					</tr>
					
				</table>
				
				<table border="1">
					<tr>
						<td>답글 작성자</td>
						<td><input type="text" name="name" value="<%=board.getMember_id()%>" readonly="readonly"></td>
					</tr>
					<tr>
						<td>답글 내용</td>
						<td><textarea rows="10" cols="20" name="content"><%=board.getQuestion_content()%></textarea></td>
					</tr>
					<tr>
						<td>답글 이미지 파일</td>
						<td><input type="file" name="file" value="파일을 첨부하세요"></td>
					</tr>
				</table>

					<input type="submit" value="글쓰기" class="btn">
			</form>
		


		
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</div>
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