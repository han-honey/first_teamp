<%@page import="vo.BoardEventCommentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.BoardEventDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
BoardEventDTO board = (BoardEventDTO)request.getAttribute("board");
ArrayList<BoardEventCommentDTO> commentList = (ArrayList<BoardEventCommentDTO>)request.getAttribute("boardEventCommentDTO");
String pageNum = (String)request.getAttribute("page");
String id = (String)session.getAttribute("sessionId");
System.out.println(id);
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
<script src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#write').click(function() {
		var text = $('#commentParentText').val();
		var id = $('#sessionId').val();
		var idx = $('#idx').text();
		alert(idx);
		var today = new Date();

		var year = today.getFullYear();
		var month = ('0' + (today.getMonth() + 1)).slice(-2);
		var day = ('0' + today.getDate()).slice(-2);

		var dateString = year + '-' + month  + '-' + day;
	
		$.ajax({
			type: "post",
			url: "EventCommentPro.bo",
			data:{
				id: id,
				text: text,
				idx: idx,
			},
			dataType: "json",
			success: function(response) {
				var html = "<tr id=\"c_"+response.c_idx+"\">";
				html += "<td align=\"center\" width=\"150px\">" + response.id + "</td>";
				html += "<td align=\"left\" width=\"600px\" colspan=\"3\">" + response.text + "</td>";
				html += "<td align=\"left\" width=\"150px\" colspan=\"3\">" + dateString + "</td>";
				html += "<td><input type=\"button\" value=\"삭제\"  onclick=\"del('"+ response.c_idx+"')\"/></td>";
				html += "</tr>";
				
				$("#comment_table").append(html);
				$("#commentParentText").val("");
			},
			error: function(xhr, textStatus, errorThrown) {
				alert(xhr + "" + textStatus + "" + errorThrown);
			}
		})
	});
});
function del(c_idx){
	$.ajax({
		type: "post",
		url: "EventCommentDelete.bo",
		data:{
			c_idx: c_idx
		},
		dataType: "text",
		success: function(response) {
	$("#c_"+c_idx).hide();
		},
		error: function(xhr, textStatus, errorThrown) {
			alert(xhr + "" + textStatus + "" + errorThrown);
		}
	})
}
</script>
</head>
<body>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 들어가는곳 -->


<div class="container">
		<div class="row">
				<table class="table table-condensed">
					<thead>
						<tr align="center">
							<th colspan="4"><%=board.getEvent_subject()%></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="150px">작성일</td>
							<td><%=board.getEvent_date() %></td>
							<td width="100px">글번호</td>
							<td align="center" id="idx"><%=board.getEvent_idx()%></td>
						</tr>
						<tr>
							<td width="150px">글쓴이</td>
							<td><%=board.getEvent_name() %></td> 
							<td width="100px">조회수</td>
							<td align="center"><%=board.getEvent_readcount() %></td>
						</tr>
						<tr>
							<td width="150px">파일</td>
							<td colspan="3"><%=board.getEvent_img() %>&nbsp;&nbsp;
								<a href="../upload/<%=board.getEvent_img()%>" download="<%=board.getEvent_img() %>">
							<input type="button" value="다운로드">
				</a></td>
				</tr>
						<tr>
							<td colspan="4">
								<p style="margin-left: 50px; margin-right: 50px;"><%=board.getEvent_content() %></p>
							</td>
						</tr>
					</tbody>
				</table>
				
				<table id="comment_table" class="table table-condensed">
					<tr>
						<td align="center" width="150px">댓글아이디</td>
						<td align="center" width="600px" colspan="3">댓글내용</td>
						<td align="left" width="150px">작성일</td>
						<td align="left" width="150px"></td>
					</tr>
					<%if(commentList.size() > 0 || commentList != null) { %>
						<%for(int i=0; i<commentList.size(); i++){ %>
						<tr id="c_<%=commentList.get(i).getComment_idx()%>">
							<td align="center" width="150px"><%=commentList.get(i).getEvent_comment_id() %></td>
							<td align="left" width="600px" colspan="3"><%=commentList.get(i).getEvent_comment_content()%></td>
							<td align="left" width="150px" colspan="3"><%=commentList.get(i).getEvent_comment_date()%></td>
							<td><input type="button" value="삭제"  onclick="del('<%=commentList.get(i).getComment_idx()%>')"/></td>
						</tr>
						<%} %>
					<%} %>
				</table>
				
				<table id="commentTable" class="table table-condensed"></table>
				<table class="table table-condensed">
					<tr>
						<td><span class="form-inline" role="form"> 
						<textarea id="commentParentText" class="form-control col-lg-12"
									style="width: 100%; resize: none;" rows="4"></textarea>
						</span>
						<input type="hidden" id="sessionId" value="<%=session.getAttribute("sessionId")%>">
						<input style="float: right;" id="write" class="site-btn" type="button" value="댓글달기" >
						</td>
						
					</tr>
					
				</table>
				<table class="table table-condensed">
					<thead>
						<tr>
							<td><span style='float: right'> 
								<%if("admin".equals(session.getAttribute("sessionId"))) {%>
									<input type="button"
									value="글수정" class="btn"
									onclick="location.href='./EventModify.bo?idx=<%=board.getEvent_idx()%>&page=<%=request.getParameter("page")%>'">
									<input type="button" value="글삭제" class="btn"
									onclick="location.href='./EventDeletePro.bo?idx=<%=board.getEvent_idx()%>&page=<%=request.getParameter("page")%>'">
								<%} %>	
									<input type="button" value="글목록" class="btn"
									onclick="location.href='./EventList.bo?page=<%=request.getParameter("page")%>'">
							</span></td>
						</tr>
					</thead>
				</table>
			</div>
	
	</div>

		<!-- 본문 내용 -->
		
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
</body>
</html>