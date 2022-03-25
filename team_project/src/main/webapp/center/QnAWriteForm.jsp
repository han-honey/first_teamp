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
    <title>
    </title>
    <script src="../js/jquery-3.6.0.js" type="text/javascript">
    $(function() {
    	$('#questionWriteInfo').css('text-align', 'center');
    });
    </script>
</head>
<body>
	
	<%
	request.setCharacterEncoding("UTF-8");
	String ref = request.getParameter("ref");
	String subject = request.getParameter("subject");
	String category = request.getParameter("category");
	String lev = request.getParameter("lev");
	String id = (String)session.getAttribute("sessionId");
	if(id == null) {
	%>
	<script>
// 		alert("회원만 접근 가능합니다.");
// 		history.back();
	</script>
	<%
	}
	%>
	<!-- 헤더 들어가는곳 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 들어가는곳 -->
		<div id="questionWriteInfo">		
			<form method="post" action="./QnAWritePro.bo" enctype="multipart/form-data">
			<% if(ref == null) {%>
				<input type="hidden" name="ref" value="0"/>
				<input type="hidden" name="lev" value="0"/>
			<% } else { %>
				<input type="hidden" name="ref" value="<%=ref%>"/>
				<input type="hidden" name="lev" value="<%=lev%>"/>
				<input type="file" class="form-control" name="file" style="display:none";>				
			<%} %>
		<div class="container">
			<h2>1:1 문의</h2>
			<table class="table table-hover">
				<tbody>
				<% if(ref == null) {%>
					
					<tr>
						<td colspan="2"><input type="file" class="form-control" name="file"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="form-control" name="product_code" 
						placeholder="상품코드(상품문의일 경우 작성해주세요.)"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="text" class="form-control" style="background: white;"
							placeholder="<%=id%>" name="name" readonly="readonly"></td>
	
					</tr>
					<tr>
						<td><input type="text" class="form-control"
							placeholder="글 제목" name="subject" maxlength="40" style="width: 1000px;" required="required"></td>
				
						<td>
							<select name="QnACategory">			
								<option value="기타문의">기타문의</option>					
								<option value="상품문의">상품문의</option>
								<option value="배송문의">배송문의</option>
							</select>
						</td>
					</tr>
					<%} else { %>
						<tr>
						<td><input type="text" class="form-control"
							placeholder="글 제목" name="subject" maxlength="40" style="width: 1000px;" required="required" value="[RE] <%= subject %>"></td>
				
						<td>
						<input type="text" name="QnACategory"  value=<%=category %> readonly="readonly"/>
						</td>
					</tr>
					<% } %>
					<tr>
						<td colspan="2"><textarea class="form-control"
								placeholder="글 내용을 작성하세요" name="content" maxlength="1024"
								style="height: 400px; resize: none;" required="required"></textarea></td>
					</tr>				
				</tbody>
			</table>
			<div align="right">
			<input type="submit" class="site-btn" value="글쓰기">
			<input type="button"  class="site-btn" value="뒤로 가기" onclick="history.back()">
			</div>
		</div>
	</form>
		</div>
		
		<br><br>
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
			