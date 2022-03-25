<%@page import="vo.ProductBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ProductBoardDTO dto =(ProductBoardDTO)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>상품 수정</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="./inc/adminTop.jsp"/>
<h1>상품 수정</h1>
	<form action="./AdminProductModifyPro.ad" method="post" >
		<input type="hidden" name="product_code" value="<%=request.getParameter("product_code") %>">
		<table>
			<tr>
				<td>재고량</td>
				<td><input type="text" name="product_stock" value="<%=dto.getProduct_stock()%>"></td>
			</tr>
		</table>
		<div>
			<input type="submit" value="수정" class="btn">
		</div>
	
	</form>
	<script src="../js/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>
</html>