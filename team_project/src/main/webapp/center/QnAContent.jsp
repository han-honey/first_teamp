<%@page import="vo.QnAReplyDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.QnADTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<QnAReplyDTO> replyList = (ArrayList<QnAReplyDTO>) request.getAttribute("replyList");
String replyCount = request.getParameter("replyCount");
QnADTO board = (QnADTO) request.getAttribute("board");
String pageNum = (String) request.getAttribute("page");
String id = (String) session.getAttribute("sessionId");
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

	<!-- 본문 내용 -->
	<div class="container">
	
		<div class="row">
			<table class="table table-condensed">
				<thead>
					<tr align="center">
						<th colspan="4" style="margin-right: 30px;"><%=board.getQuestion_subject()%></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="150px">작성일</td>
						<td><%=board.getQuestion_date()%></td>
						<td width="100px">글번호</td>
						<td align="center"><%=board.getQuestion_idx()%></td>
					</tr>
					
					<tr>
						<td width="150px">글쓴이</td>
						<td><%=board.getMember_id()%></td>
						<%if("".equals(board.getProduct_code())) {%>
							<td width="100px">상품코드</td>
							<td align="center"><%=board.getProduct_code()%></td>
						<%} %>
					</tr>
					<tr>
						<%if("".equals(board.getQuestion_img())) {%>					
							<td width="150px">파일</td>
							<td colspan="3">파일 없음&nbsp;&nbsp; <a
								href="../upload/<%=board.getQuestion_img()%>"
								download="<%=board.getQuestion_img()%>"> <input type="button"
									value="다운로드">
								</a>
							</td>
						<%} else { %>
							<td width="150px">파일</td>
							<td colspan="3"><%=board.getQuestion_img()%>&nbsp;&nbsp; <a
								href="../upload/<%=board.getQuestion_img()%>"
								download="<%=board.getQuestion_img()%>"> <input type="button"
								value="다운로드">
								</a>
							</td>
						<%} %>
					</tr>
					<tr>
						<td colspan="4">
							<p style="margin-left: 50px; margin-right: 50px;"><%=board.getQuestion_content()%></p>
						</td>
						
					</tr>
				</tbody>
			</table>
<%-- 		<% --%>

<%-- %>	 --%>
			<table class="table table-condensed">
				<%
				if(replyList != null && replyList.size() > 0) {
					for(QnAReplyDTO reply : replyList) {
				%>		
					<tr>
						<td align="center" width="150px"><%=reply.getReply_member_id() %></td>
						<td align="left" width="800px" colspan="3"><%=reply.getReply_content() %></td>
					</tr>
				<%
					}
				}
				%>
			</table>
			<table id="commentTable" class="table table-condensed"></table>
			<form action="./QnAWriteForm.bo" method="post" >
				<input type="hidden" name="ref" value="<%=board.getAnswer_ref() %>">
				<input type="hidden" name="page" value="<%=request.getParameter("page")%>">
				<input type="hidden" name="subject" value="<%=board.getQuestion_subject()%>">	
				<input type="hidden" name="category" value="<%=board.getQuestion_category()%>">	
				<input type="hidden" name="lev" value="<%=board.getAnswer_lev()%>">
				
			</form>			
			
			<table class="table table-condensed">
				<thead>
					<tr>
						<td>
							<span style='float: right'>
								<%if("admin".equals(session.getAttribute("sessionId"))) {%> 
									 <input style="float: right; margin-top:10px;" class="site-btn" type="submit" value="답글달기" />
									 <input type="button" value="글수정" class="btn"
										onclick="location.href='./QnAModify.bo?idx=<%=board.getQuestion_idx()%>&page=<%=request.getParameter("page")%>'">
									 <input type="button" value="글삭제" class="btn"
										onclick="location.href='./QnADeletePro.bo?idx=<%=board.getQuestion_idx()%>&page=<%=request.getParameter("page")%>'">
								<%} %>		
									 <input type="button" value="글목록" class="btn"
										onclick="location.href='./QnAList.bo?page=<%=request.getParameter("page")%>'">
							</span>
						</td>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- 본문 내용 -->

	<!-- 푸터 들어가는곳 -->
	<jsp:include page="../inc/bottom.jsp" />
	<!-- 푸터 들어가는곳 -->
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