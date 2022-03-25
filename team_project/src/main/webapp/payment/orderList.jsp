<%@page import="java.util.Date"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.PaymentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<PaymentDTO> orderList = (ArrayList<PaymentDTO>) request.getAttribute("paymentDTO");
String[] frontOrderNum = new String[orderList.size()];
String[] backOrderNum = new String[orderList.size()];

for(int i=0; i<orderList.size(); i++){
	String strDate = orderList.get(i).getOrder_date()+"";
	frontOrderNum[i] = strDate.split(" ")[0].replace("-", ""); //2022-01-01 21:59:59
	System.out.println(frontOrderNum[i]);
	
	int order_idx = orderList.get(i).getOrder_idx();
	backOrderNum[i] = (String)(String.format("%08d", order_idx)); 
	
}
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
			<h2>주문내역</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>주문번호</th>
						<th>주문상품명</th>
						<th>주문개수</th>
						<th>상품금액</th>
						<th>결제금액</th>
						<th>결제날짜</th>
						<th>배송요청사항</th>
					</tr>
				</thead>
				<tbody>

					<%
 				if (orderList != null && orderList.size() > 0) {
 					for (int i=0; i<orderList.size(); i++) {
				%>
				<tr style="cursor: pointer;"
					onclick="location.href='./OrderContent.pa?idx=<%=orderList.get(i).getOrder_idx()%>&page=<%=pageNum%>'">
					<td><%=frontOrderNum[i]%>-<%=backOrderNum[i]%></td>
					<td><%=orderList.get(i).getProuct_name()%></td>
					<td><%=orderList.get(i).getOrder_product_amount()[0]%></td>
					<td><%=orderList.get(i).getProduct_price()%></td>
					<td><%=orderList.get(i).getOrder_product_amount()[0]*orderList.get(i).getProduct_price()%></td>
					<td><%=orderList.get(i).getOrder_date()%></td>
					<%if(orderList.get(i).getOrder_request_message()==null) {%>
						<td> </td>
					<%} else { %>
						<td><%=orderList.get(i).getOrder_request_message()%></td>
					<%} %>
				</tr>
				<%
 				}
 				} else {
				%>
<!-- 				주문내역이 없을 경우 -->
				<tr>
					<td colspan="5">주문내역이 없습니다.</td>
				</tr>
				<%
				}
				%> 
				</tbody>
			</table>
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
				Prev&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<%
				} else {
				%>
				<a href="./notice.ma?page=<%=pageNum - 1%>">Prev</a>
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
				<%=i%>
				<%
				} else {
				%>
				<a href="./notice.ma?page=<%=i%>"><%=i%></a>
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Next
				<%
				} else {
				%>
				<a href="./notice.ma?page=<%=pageNum + 1%>">Next</a>
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