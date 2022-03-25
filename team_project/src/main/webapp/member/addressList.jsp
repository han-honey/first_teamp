<%@page import="vo.AddressDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
	ArrayList<AddressDTO> addressList = (ArrayList<AddressDTO>)request.getAttribute("addressList");
	
	%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>
<script type="text/javascript">
function insertAddress() {
	
 	window.open("./MemberAddressAddForm.me", "address_input_page", "width=500px, height=300px");
 }
 
</script>
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
<style>
th, td {
  padding: 15px;
}
</style>
</head>
<body>
	<jsp:include page="../inc/top.jsp" />
	<div align="center" >
		
		  	<h4>배송지 목록</h4>
		  	<p>배송지를 함께 관리하실 수 있습니다. <br>
				자주 쓰는 배송지를 편리하게 통합 관리 하세요!</p>
				
		  	<div style="display: inline-block; padding : 20px 50px 50px 0px;">
			<table border="1" style="table-layout: auto;">
				<tr>
					<th>주소</th>
					<th>요청메세지</th>
					<th>삭제</th>
				</tr>
				<c:choose>
				<c:when test="${!empty addressList }">
				<c:forEach items="${addressList }" var="AddressDTO">
				<tr>
					<td>
					<input type="hidden" name="address_idx" value="${AddressDTO.getAddress_idx() }">
					${AddressDTO.getAddress_post() }
					<c:if test="${AddressDTO.getAddress_default() eq 1 }"><span style="font-size: 5px; font-weight: bold; color: red;">기본배송지</span></c:if>
					<br>${AddressDTO.getAddress() }<br>
					${AddressDTO.getExtra_address() }</td>
					<td>${AddressDTO.getRequest_message() }</td>
					<td>
					<input type="button" class="btn" value="수정" onclick="window.open('./MemberAddressUpdateForm.me?address_idx=${AddressDTO.getAddress_idx() }', '', 'width=500px, height=300px' )">
					<input type="button" class="btn" value="삭제" onclick="location.href='./MemberAddressDelete.me?address_idx=${AddressDTO.getAddress_idx() }'">
					
					</td>
				</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
				<tr>
					<td colspan="3">배송지 목록이 비어있습니다.</td>
				</tr>
				
				</c:otherwise>
				</c:choose>
			</table>
		</div>
			<div id="other_page" style="padding : 20px 50px 50px 0px;">
				<input type="button" class="btn" value="배송지 등록" onclick="insertAddress()">
				<input type="button" value="마이페이지" class="btn" onclick="location.href='./MemberInfo.me'">
			</div>
		</div>
	<jsp:include page="../inc/bottom.jsp" />
		
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


