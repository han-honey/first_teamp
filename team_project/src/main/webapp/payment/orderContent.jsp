<%@page import="vo.PaymentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
PaymentDTO paymentDTO = (PaymentDTO) request.getAttribute("paymentDTO");
String pageNum = (String) request.getAttribute("page");

String strDate = paymentDTO.getOrder_date() + "";
String[] frontOrderNum = strDate.split(" ")[0].split("-");
int order_idx = paymentDTO.getOrder_idx();
String backOrderNum = String.format("%08d", order_idx);
String productCd = "";
for(int i = 0; i < paymentDTO.getOrder_product_code().length; i++){
	if(paymentDTO.getOrder_product_code()[i] != null){
		productCd += paymentDTO.getOrder_product_code()[i].replace("null", "");		
	}	
}
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
		<div class="row">
				<table class="table table-condensed">
					<thead>
						<tr align="center">
					
							<th colspan="2">주문상세</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="150px">주문상품</td>
							<td width="150px"><%=paymentDTO.getProuct_name()%> <!-- 상품명 -->
						</tr>
						<tr>
							<td width="150px">상품가격</td>
							<td><%=paymentDTO.getProduct_price()*paymentDTO.getOrder_product_amount()[0]%>원</td> <!-- 주문금액 -->
						</tr>
						<tr>
							<td width="150px">주문상태</td>
							<td width="240px"><%=paymentDTO.getOrder_status() %></td> <!-- 상품준비중 -->
						</tr>
						<tr>
							<td width="150px">주문날짜</td>
							<td><%=paymentDTO.getOrder_date()%></td>
						</tr>
						<tr>
							<td width="100px">주문번호</td>
							<td><%=frontOrderNum[0]+frontOrderNum[1]+frontOrderNum[2]%>-<%=backOrderNum %></td>
						</tr>
						<tr align="center">
							<th colspan="2">받는사람 정보</th>
						</tr>
						<tr>
							<td width="150px">받는사람</td>
							<td><%=paymentDTO.getReceiver_name()%></td>
						</tr>
						<tr>
							<td width="100px">연락처</td>
							<td><%=paymentDTO.getReceiver_phone()%></td>
						</tr>
						<tr>
							<td width="100px">받는주소</td>
							<td><%=(paymentDTO.getReceiver_post())+paymentDTO.getReceiver_address()+paymentDTO.getReceiver_extra_Address()%></td>
						</tr>
						<tr>
							<td width="100px">배송요청사항</td>
							<%if(paymentDTO.getOrder_request_message() == null) {%>
							<td>없음</td>
							<%} else { %>
							<td><%=paymentDTO.getOrder_request_message()%></td>
							<%} %>
						</tr>
						<tr align="center">
							<th colspan="2">결제정보</th>
						</tr>
						<tr>
							<td width="150px">결제수단</td>
							<td>무통장입금</td>
						</tr>
						<tr>
							<td width="150px">은행</td>
							<td>부산은행(6920265303593)</td>
						</tr>
					</tbody>
				</table>
				
				
				<table id="commentTable" class="table table-condensed"></table>
				<table class="table table-condensed">
					<thead>
						<tr>
							<td><span style='float: right'>
							<input type="button" value="리뷰작성" class="btn"	onclick="location.href='../product/ReviewForm.bo?product_code=<%=productCd%>'">
							<input type="button" value="뒤로가기" class="btn"	onclick="history.back()">
							</span></td>
						</tr>
					</thead>
				</table>
			</div>
	</div>

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