<%@page import="vo.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BoardDTO board = (BoardDTO) request.getAttribute("board");
String pageNum = (String) request.getAttribute("page");
String sessionId = (String) request.getAttribute("sessionId");
%>
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
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 들어가는곳 -->

	<div class="container">
		<hr>
		<div class="row">
				<table class="table table-condensed">
					<thead>
						<tr align="center">
					
							<th colspan="4"><%=board.getSubject()%></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="150px">작성일</td>
							<td><%=board.getDate()%>
							<td width="100px">글번호</td>
							<td align="center"><%=board.getIdx()%></td>
						</tr>
						<tr>
							<td width="150px">글쓴이</td>
							<td><%=board.getName()%> 
							<td width="100px">조회수</td>
							<td align="center"><%=board.getReadcount()%></td>
						</tr>
						<tr>
							<td width="150px"s>파일</td>
							<td colspan="3"><%=board.getImg()%>&nbsp;&nbsp; <a
								href="../upload/<%=board.getImg()%>"
								download="<%=board.getImg()%>">
							<input type="button" value="다운로드">
				</a></td>
				</tr>
						<tr>
							<td colspan="4">
								<p style="margin-left: 50px; margin-right: 50px;"><%=board.getContent()%></p>
							</td>
						</tr>
					</tbody>
				</table>
				<table id="commentTable" class="table table-condensed"></table>
				<table class="table table-condensed">
					<thead>
						<tr>
							<td><span style='float: right'>
							<%if("admin".equals(session.getAttribute("sessionId"))) {%> 
								<input type="button"
									value="글수정" class="btn"
									onclick="location.href='./NoticeModify.bo?idx=<%=board.getIdx()%>&page=<%=request.getParameter("page")%>'">
									<input type="button" value="글삭제" class="btn"
									onclick="location.href='./NoticeDeletePro.bo?idx=<%=board.getIdx()%>&page=<%=request.getParameter("page")%>'">
							<%} %>	
									<input type="button" value="글목록" class="btn"
									onclick="location.href='./NoticeList.bo?page=<%=pageNum%>'">
							</span></td>
						</tr>
					</thead>
				</table>
			</div>
	
	</div>
</body>
</html>

<div class="clear"></div>
<!-- 본문 내용 -->

<!-- 푸터 들어가는곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터 들어가는곳 -->
<!-- Js Plugins -->
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