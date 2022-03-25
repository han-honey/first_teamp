<%@page import="vo.BoardEventDTO"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<BoardEventDTO> articleList = (ArrayList<BoardEventDTO>) request.getAttribute("articleList");
PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int pageNum = pageInfo.getPageNum();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount();
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

<!-- 부트스트랩 사용하기 위해 -->

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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

<style type="text/css">
	table {font-size: 12pt;}
</style>

<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 들어가는곳 -->

	<!-- 본문 내용 -->
	<article>
		<div class="container">
			<h2>이벤트</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>

				<%
				if (articleList != null && articleList.size() > 0) {
					for (BoardEventDTO eventBoard : articleList) {
				%>
				<tr style="cursor: pointer;" onclick="location.href='./EventContent.bo?idx=<%=eventBoard.getEvent_idx()%>&page=<%=pageNum%>'">
					<td><%=eventBoard.getEvent_idx()%></td>
					<td><%=eventBoard.getEvent_subject()%></td>
					<td><%=eventBoard.getEvent_name()%></td>
					<td><%=eventBoard.getEvent_date()%></td>
					<td><%=eventBoard.getEvent_readcount() %></td>
				</tr>
				<%
 				}
 				} else {
				%>
<!-- 				게시물 목록이 없을 경우 -->
				<tr>
					<td colspan="5">작성된 게시물이 없습니다.</td>
				</tr>
				<%
				}
				%> 
				</tbody>
			</table>
			<%if("admin".equals(session.getAttribute("sessionId"))) {%>
			<div>
				<input type="button" class="site-btn" style="float: right;" value="이벤트 작성" 
					onclick="location.href='./EventWriteForm.bo'">
			</div>
			<%} %>
		</div>

		<!-- 페이지 목록 출력하는 곳 -->
		<div class="clear"></div>
		<div class="container">
			<div id="page_control" style="text-align: center; font-size: 16px;">
				<!-- 
				이전페이지 버튼은 현재 페이지 번호가 시작페이지 번호보다 클 때만 링크 표시하며
				현재 페이지번호 - 1 값을 파라미터로 전달
				-->
				<%
				if (pageNum == startPage) {
				%>
				<input type="button" value="&lt;" >
				<%
				} else {
				%>
				<input type="button" value="&lt;" onclick="location.href='./EventList.bo?page=<%=pageNum - 1%>'">
				<%
				}
				%>

				<!-- 페이지 목록은 시작 페이지번호부터 끝 페이지 번호까지 차례대로 표시 -->
				<%
				for (int i = startPage; i <= endPage; i++) {
				%>
				<!-- 페이지 번호 클릭 시 driver.jsp 페이지로 페이지번호(page)를 전달 -->
				<!-- 단, 현재 페이지 번호는 하이퍼링크 없이 표시 -->
				<%
				if (i == pageNum) {
				%>
				<input type="button" value="<%=i%>" >
				<%
				} else {
				%>
				<input type="button" value="<%=i%>" onclick="location.href='./EventList.bo?page=<%=i%>'">
				<%
				}
				%>
				<%
				}
				%>

				<!-- 
				다음페이지 버튼은 현재 페이지 번호가 끝페이지 번호보다 작을 때만 링크 표시하며
				현재 페이지번호 + 1 값을 파라미터로 전달
				-->
				<%
				if (pageNum == endPage) {
				%>
				<input type="button" value=">" >
				<%
				} else {
				%>
				<input type="button" value=">" onclick="location.href='./EventList.bo?page=<%=pageNum + 1%>'">
				<%
				}
				%>
			</div>
		</div>
		<!-- 푸터 들어가는곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는곳 -->
	</article>
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