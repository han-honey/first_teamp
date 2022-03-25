<%@page import="vo.AddressDTO"%>
<%@page import="vo.CartDTO"%>
<%@page import="dao.MemberDAO"%>
<%@page import="vo.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
if (session.getAttribute("sessionId") == null) {
%>
<script type="text/javascript">
		alert('회원 전용 서비스입니다.\n로그인 후 이용해 주세요.');
		history.back();
</script>
<%
}
ArrayList<CartDTO> cartList = (ArrayList<CartDTO>)request.getAttribute("cartList");
MemberDTO memberDTO = (MemberDTO) request.getAttribute("memberDTO");
ArrayList<AddressDTO> addressList = (ArrayList<AddressDTO>)request.getAttribute("addressList");
int i = 1; // 주소록 선택을 위한 변수
System.out.println("checkout.jsp");
%>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="ie=edge">
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
<link rel="stylesheet" href="../css/cart.css" type="text/css">
<script src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
// -------------주소 API 시작-------------
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.form.roadAddrPart1.value = roadAddrPart1;
	document.form.addrDetail.value = addrDetail;
	document.form.zipNo.value = zipNo;
	
}

// -------------주소 API 끝-------------

$(function () { // 주소 선택시 입력창 숨기기
	$('select[name=sel_add]').on('change', function() {
		if($('select[name=sel_add] > option:selected').val() == '직접입력'){
			$('#roadAddrPart1').val(""); // 주소
			$('#addrDetail').val(""); // 상세주소
			$('#zipNo').val(""); // 우편번호
		} else {
			var str = $('select[name=sel_add] > option:selected').text();
			const str1 = str.split("|")
			$('#roadAddrPart1').val(str1[1].split(":")[1]); // 주소
			$('#addrDetail').val(str1[2].split(":")[1]); // 상세주소
			$('#zipNo').val(str1[0].split(":")[1]); // 우편번호
		}
	});
});

$(function () { // 주소 선택시 입력창 숨기기
	$('select[name=sel_add]').on('change', function() {
			var str = $('select[name=sel_add] > option:selected').text();
			
	});
});

</script>
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header-->
	<jsp:include page="../inc/top.jsp" />
	<!-- Header-->
	<!-- Hero Section Begin -->
	<!-- Hero Section End -->

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="../img/breadcrumb.jpg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="breadcrumb__text">
					<h2>주문하기</h2>
					<div class="breadcrumb__option">
						<a href="../main/Main.ma">홈</a> <span>주문하기</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<section class="checkout spad">
	<div class="container">
		<div class="checkout__form">
			<h4>주문상품</h4>
			<form action="CheckoutPro.pa" name="form" id="form" method="post">
				<table>
					<tr>
						<td></td>
						<th class="shoping__product">상품명</th>
						<td>상품수량</td>
						<td>상품금액</td>
						<td>상품총금액</td>
					</tr>
					<%
					int priceSum = 0;
					int amountSum = 0;
					for (int j=0; j<cartList.size(); j++) {
						priceSum += (cartList.get(j).getProduct_amount() * cartList.get(j).getProduct_price());
					%>
					<tr>
						<td><img style="width: 60px; height: 78px;" alt="fruit"
							src="<%=request.getContextPath() %>/upload/<%=cartList.get(j).getProduct_img()%>"></td>
						<td><input type="text" style="width: 700px; border: none;"
							id="product_name" name="product_name" value="<%=cartList.get(j).getProduct_name()%>"></td>
						<td><input type="text" style="width: 50px; border: none;"
							id="product_amount" name="product_amount" value="<%=cartList.get(j).getProduct_amount()%>">개</td>
						<td><input type="text" style="width: 60px; border: none;"
							id="product_price" name="product_price" value="<%=cartList.get(j).getProduct_price()%>">원</td>
						<td><input type="text" style="width: 60px; border: none;"
							id="product_all_price" name="product_all_price"
							value="<%=cartList.get(j).getProduct_amount() * cartList.get(j).getProduct_price()%>">원
							
							<input type="hidden" id="product_code" name="product_code" value="<%=cartList.get(j).getProduct_code()%>"></td>
					</tr>
					<%
					}
					%>
<!-- 					<tr> -->
<!-- 						<td colspan="5" style="text-align: center;">합계</td> -->
<!-- 					</tr> -->
					<tr>
						<td colspan="5" style="text-align: center;">
						합계<input type="text" name="price_sum" style="text-align: right; border:none;" value="<%=priceSum%>">원</td>
					</tr>
				</table>
			<!-- ========================================== -->
			<h4>주문자 정보</h4>
			<table>
				<tr>
					<th>주문자</th>
					<td><%=memberDTO.getMember_name()%>
					<input type="hidden" name="orderer_name" value="<%=memberDTO.getMember_name()%>">
					</td>
				</tr>
			</table>
			<!-- ========================================== -->
			<h4>배송정보</h4>
				<table class="tb">
					<tr>
						<th>주소선택</th>
						<td><select name="sel_add">
								<option value="직접입력">직접입력</option>
								<%
								for (AddressDTO addressDTO : addressList) {
								%>
								<option value="<%=i%>">우편번호:<%=addressDTO.getAddress_post()%>|주소:<%=addressDTO.getAddress()%>|상세주소:<%=addressDTO.getExtra_address()%></option>
								<%
								i++;
								}
								%>
						</select>&nbsp;
						<input style="height: 40px; margin-top: 1px;" type="button"
							   onClick="goPopup();" value="주소검색" /></td>
					</tr>
					<tr>
						<th>도로명주소</th>
						<td><input type="text" id="roadAddrPart1"
							name="roadAddrPart1" required="required"/></td>
					</tr>
					<tr>
						<th>상세주소</th>
						<td><input type="text" id="addrDetail" name="addrDetail" required="required"/></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td><input type="text" id="zipNo" name="zipNo" required="required"/></td>
					</tr>
					<tr>
						<th>받으시는분 이름</th>
						<td><input type="text" id="receiver_name"
							name="receiver_name" required="required" /></td>
					</tr>
					<tr>
						<th>받으시는분 전화번호</th>
						<td><input type="text" id="receiver_phone"
							name="receiver_phone" required="required" /></td>
					</tr>
					<tr>
						<th>배송시 요청사항</th>
						<td><input type="text" id="order_request_message"
							name="order_request_message" /></td>
					</tr>
				</table>
			<hr>
			<!-- ========================================== -->
				<h4>쿠폰 / 적립금</h4>
				<table class="tb">
					<tr>
						<th>쿠폰 적용</th>
						<td><select>
								<option>사용 가능 쿠폰0개/전체0개</option>
						</select></td>
					</tr>
					<tr>
						<th>적립금 적용</th>
						<td><input type="text" name="earning_point" value="500"
							style="width: 150px; text-align: right;">사용포인트
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>500 보유포인트</span></td>
					</tr>
				</table>
				<!-- ========================================== -->
				<h4>결제 수단</h4>
				<table class="tb">
					<tr>
						<th>결제수단 선택</th>
						<td><input type="button" style="width: 300px; border: none;"
							name="receiver_post" value="무통장입금"></td>
					</tr>
				</table>
				<!-- ========================================== -->
				<h4>개인정보 수집/제공</h4>
				<table>
					<tr>
						<td><input type="checkbox" name="isAgree" required="required">결제
							진행 필수 동의</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="isAgress"
							required="required"> 개인정보 수집.이용 및 처리 동의(필수)<input
							type="button" name="privacy"
							onclick="window.open('./paymentAgree1.jsp','pop','width=580,height=430, scrollbars=yes, resizable=yes');"
							value="보기→"></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="isAgress"
							required="required">전자지급 결제대행 서비스 이용약관 동의(필수)<input
							type="button" name="privacy"
							onclick="window.open('./paymentAgree2.jsp','pop','width=580,height=430, scrollbars=yes, resizable=yes');"
							value="보기→"></td>
					</tr>
				</table>
				<!-- ========================================== -->
				<table>
					<tr>
						<td>[배송준비중] 이전까지 주문 취소 가능합니다</td>
					</tr>
					<tr>
						<td>미성년자가 결제 시 법정대리인이 그 거래를 취소할 수 있습니다.</td>
					</tr>
					<tr>
						<td>상품 미배송 시, 결제수단으로 환불됩니다.</td>
					</tr>
				</table>
			<!-- ========================================== -->
			<div align="center">
				<input style="width: 350px; height: 50px;" type="submit"
						name="payment_button" class="site-btn" value="<%=priceSum%>원 결제하기">
			</div>
			</form>
			</div>
		</div>
	</section>
	
	<jsp:include page="../inc/bottom.jsp" />
	
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