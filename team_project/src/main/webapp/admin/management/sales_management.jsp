<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ProductBoardDTO"%>
<%@page import="vo.PaymentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    ArrayList<ProductBoardDTO> chartList = (ArrayList<ProductBoardDTO>)request.getAttribute("chartList");
    DecimalFormat formatter = new DecimalFormat("###,###");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>매출차트</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	 <jsp:include page="../inc/adminTop.jsp"/>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">매출차트</h1>
					<div class="card mb-4" style="width: 800px; float: left;">
						<div class="card-header">
							<i class="fas fa-cubes"></i> 판매내역
						</div>
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>제품코드</th>
										<th>판매갯수</th>
										<th>판매금액</th>
									</tr>
								</thead>
								<tbody>
								<%for(ProductBoardDTO chart : chartList){ %>
									<tr>
										<td><%=chart.getProduct_code()%></td>
										<td><%= chart.getProduct_sell_amount() %></td>
										<td><%= formatter.format(chart.getProduct_price()) %></td>
									</tr>
									<%} %>
								</tbody>
							</table>
						</div>
					</div>
<!-- 					<div class="card mb-4" style="width: 800px; float: right;"> -->
<!-- 						<div class="card-header"> -->
<!-- 							<i class="fas fa-chart-bar me-1"></i> 월별매출 -->
<!-- 						</div> -->
<!-- 						<div class="card-body"> -->
<%-- 							<canvas id="myBarChart" width="100%" height="50"></canvas> --%>
<!-- 						</div> -->
<!-- 						<div class="card-footer small text-muted">마지막 업데이트 날짜</div> -->
<!-- 					</div> -->
				</div>


			</main>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="js/datatables-simple-demo.js"></script>
</body>
</html>
