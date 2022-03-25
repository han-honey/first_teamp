<%@page import="vo.QnADTO"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<QnADTO> articleList = (ArrayList<QnADTO>)request.getAttribute("articleList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
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
			<h2>1:1 문의</h2>
		<form action="./QnAWriteForm.bo">
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="text-align:center;">글번호</th>
						<th style="text-align:center;">카테고리</th>						
						<th style="text-align:center;">제목</th>
						<th style="text-align:center;">상품코드</th>
						<th style="text-align:center;">작성자</th>
						<th style="text-align:center;">작성일</th>
					</tr>
				</thead>
				<tbody>

			<%
			
				if(articleList != null && articleList.size() > 0) {		
					for(QnADTO qnaDTO : articleList) {
							%> 
						<tr style="cursor: pointer;" onclick="location.href='./QnAContent.bo?idx=<%=qnaDTO.getQuestion_idx() %>&page=<%=pageNum%>'">
							<td style="text-align:center;"><%=qnaDTO.getQuestion_idx() %></td>						
							<td style="text-align:center;"><%=qnaDTO.getQuestion_category() %></td>							
							<td><%=qnaDTO.getQuestion_subject() %></td>
							<%if(qnaDTO.getQuestion_category().equals("상품문의")) { %>
								<td style="text-align:center;"><%=qnaDTO.getProduct_code() %></td>
							 <%} else { %> 
							 	<td style="text-align:center;">-</td>
							 <% } %>
							<td style="text-align:center;"><%=qnaDTO.getMember_id() %></td>
							<td style="text-align:center;"><%=qnaDTO.getQuestion_date() %></td>
						</tr>
						<%
// 						}
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
			<%if("".equals(session.getAttribute("sessionId"))) {%>
				<div>
					<input type="button" class="site-btn" style="float: right;" value="로그인 후 문의가능">
				</div>
				
			<%} else {%>	
				<div>
					<input type="submit" class="site-btn" style="float: right;" value="1:1 문의하기">
				</div>
			<%} %>
			</form>
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
				<input type="button" value="&lt;" onclick="location.href='./QnAList.bo?page=<%=pageNum - 1%>'">
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
				<input type="button" value="<%=i%>" onclick="location.href='./QnAList.bo?page=<%=i%>'">
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
				<input type="button" value=">" onclick="location.href='./QnAList.bo?page=<%=pageNum + 1%>'">
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
