<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ProductBoardDTO"%>
<%@page import="dao.AddressDAO"%>
<%@page import="vo.CartDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
request.setCharacterEncoding("UTF-8");
if(session.getAttribute("sessionId")==null){
	%>
	<script type="text/javascript">
	alert('회원 전용 서비스 입니다\n로그인이 필요합니다.')
	location.href="../member/MemberLoginForm.me";
	</script>
	<%
}

ArrayList<CartDTO> cartList = (ArrayList<CartDTO>) request.getAttribute("cartList"); // 세션 객체에서 cart 값을 가져오기
int total_price = (int)request.getAttribute("cartTotalPrice");
// int discounted_amt = (int)request.getAttribute("discounted_amt"); // 할인
int listCount = cartList.size();
DecimalFormat formatter = new DecimalFormat("###,###");
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

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css"type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">

<script src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#all_clear_btn').click(function() {
		$.ajax({
			type: 'post',
			url: '../payment/CartAllDelete.pa',
			data: {
				sessionId: $('#sessionId').val(),
			},
			dataType: 'text',
			success: function(response) {
				alert('전체삭제 완료!');
				location.reload();
			},
			error: function(xhr, textStatus, errorThrown) {
				alert(xhr + "" + textStatus + "" + errorThrown);
			}
		});
	});
	
	// 체크 박스 전체 선택 기능
	$('#all_check').on('change', function() {
		// 전체선택 체크박스 체크, 체크해제에 따라 동작을 다르게 수행(모든 항목 체크, 해제)
		if($('#all_check').is(':checked')) { // if('#allCheck').prop('checked') == true) 동일
			// each() 함수를 활용하여 동작 반복 수행
			// => each() 함수 내의 익명함수 구현 시
			//    지정된 대상에 해당하는 요소에 차례대로 접근하여 인덱스와 요소를 파라미터로 전달
			$(':checkbox').each(function(index, item) { // 전달되는 요소 갯수만큼 자동 반복됨
				// 첫번째 체크박스를 제외한 나머지 항목 체크
				if(index > 0) {
					// 파라미터로 전달받은 item 변수를 활용하여 체크박스에 접근 가능
					// => prop() 함수 호출하여 체크상태 변경 가능
					$(item).prop('checked', true); // item 요소의 checked 속성을 체크(true)
				}
			});
		} else {
			$(':checkbox').each(function(index, item) { // 전달되는 요소 갯수만큼 자동 반복됨
				// 첫번째 체크박스를 제외한 나머지 항목 체크해제
				if(index > 0) {
					$(item).prop('checked', false); // item 요소의 checked 속성을 체크해제(false)
				}
			});
		}
	});
	
	
});

function deleteAction() {
	var checkRow = "";
	// 선택된 체크박스의 value값을 checkRow 변수에 구분자 "/"와 함께 누적저장한다.
	$('.checkRow:checked').each(function() {
		checkRow += $(this).val() + "/";
	});
	
	// 선택된 체크박스가 없을 경우(checkRow의 길이가 0일 경우) 경고창을 띄우고 작업 취소된다.
	if(checkRow == '') {
		alert('삭제할 상품을 선택해주세요.');
		return false;
	} else if(confirm("선택한 상품을 삭제하시겠습니까?")) {
		// 선택된 제품이 있을 경우 확인창이 뜨고 확인을 누를 시 선택 삭제 작업이 진행된다.
		location.href = "../payment/CartDelete.pa?product_code=" + checkRow;
	}
}
	
function selectOrderAction() {
	var checkRow = "";
	// 선택된 체크박스의 value값을 checkRow 변수에 구분자 "/"와 함께 누적저장한다.
	$('.checkRow:checked').each(function() {
		checkRow += $(this).val() + "/";
	});
	
	// 선택된 체크박스가 없을 경우(checkRow의 길이가 0일 경우) 경고창을 띄우고 작업 취소된다.
	if(checkRow == '') {
		alert('주문할 상품을 선택해주세요.');
		return false;
	} else if(confirm("선택한 상품을 주문하시겠습니까?")) {
		// 선택된 제품이 있을 경우 확인창이 뜨고 확인을 누를 시 선택 삭제 작업이 진행된다.
		location.href = "../payment/CheckoutForm.pa?product_code=" + checkRow;
	}
}

function allOrderAction() {
	var checkRow = "";
	// 모든 체크박스의 value값을 checkRow 변수에 구분자 "/"와 함께 누적저장한다.
	$('.checkRow').each(function() {
		checkRow += $(this).val() + "/";
	});
	
	// 선택된 체크박스가 없을 경우(checkRow의 길이가 0일 경우) 경고창을 띄우고 작업 취소된다.
	if(checkRow == '') {
		alert('주문할 상품을 선택해주세요.');
		return false;
	} else if(confirm("전체 상품을 주문하시겠습니까?")) {
		// 선택된 제품이 있을 경우 확인창이 뜨고 확인을 누를 시 선택 삭제 작업이 진행된다.
		location.href = "../payment/CheckoutForm.pa?product_code=" + checkRow;
	}
}

function qtyDown(index) {
	var qty = Number($('.qty'+index).val()); // cart에서 넘어온 product_amount
	var totalPrice = Number($('.shoping__cart__total'+index).text()); // 해당 상품 총가격(갯수*개당가격)
	
	if($('.stock'+index).val()==0){
		return;
	}
	
	if(qty > 0){
		qty--; // 마이너스 버튼 누르면 -1
	}
	
	if(qty == 0){
		
		alert('최소 1개이상 주문 가능합니다!')
		$('.qty'+index).val(1);
		return;
		
	} else {
		
		var price = totalPrice/(qty+1) // 상품 1개 가격
		$('.qty'+index).val(qty);
		$('.shoping__cart__total'+index).text(totalPrice-price); // 1개의 상품 총가격
	}
}

function qtyUp(index) {
	
	var qty = Number($('.qty'+index).val()); // 상품 갯수
	var totalPrice = Number($('.shoping__cart__total'+index).text()); // 해당 상품 총가격(갯수*개당가격)
	var stock = $('.stock'+index).val(); // 재고량
	qty++; // 플러스 버튼 누르면 +1
	
	if(qty > stock){
		
		alert('재고가 부족합니다\n재고량:'+stock)
		
		return;
		
	} else {
		var price = totalPrice/(qty-1); // 상품 1개 가격
		var total_product_price = totalPrice+price // 1개의 상품 총가격
		$('.qty'+index).val(qty);
		$('.shoping__cart__total'+index).text(total_product_price);
		
	}
}

$(function() {
	$('#update_btn').on('click', function() { // 변경사항저장 버튼 클릭 시 수량 및 금액 업데이트
		
			var product_codes = "";
			var product_amounts = "";
			
		$('.checkRow').each(function(index) {
			
			// 선택된 체크박스의 value값을 product_codes,product_amounts 변수에 구분자 "/"와 함께 누적저장한다.
			product_codes += $('.checkRow').eq(index).val() + "/"; // 상품 코드
			product_amounts += $('.qty'+index).val() + "/"; // 상품 수량
			
		});
		
		$.ajax({
			type: 'post',
			url: '../payment/CartUpdate.pa',
			data: {
				product_codes: product_codes,
				product_amounts: product_amounts,
			},
			dataType: 'text',
			success: function(response) {
				alert('변경사항 저장 완료!');
				location.reload();
			},
			error: function(xhr, textStatus, errorThrown) {
				alert(xhr + "" + textStatus + "" + errorThrown);
			}
		});
	});
});

</script>

</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<!-- TOP -->
	<jsp:include page="../inc/top.jsp"></jsp:include>
	<!-- TOP -->

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="../img/breadcrumb.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Cart</h2>
						<div class="breadcrumb__option">
							<a href="./index.jsp">Home</a> <span>Shopping Cart</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Shoping Cart Section Begin -->
	<section class="shoping-cart spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__table">
						<table>
							<thead>
								<tr>
									<td><input type="checkbox" id="all_check"></td>
									<th class="shoping__product" >상품명</th>
									<th>가격</th>
									<th>수량</th>
									<th>총가격</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (listCount < 1) {
								%>
								<tr align="center">
									<td colspan="5">장바구니에 담긴 상품이 없습니다.
									<a href="../main/Main.ma">주문하기</a>
									</td>
								</tr>

								<%} else {%>
									
									<%for (int i=0; i<cartList.size(); i++) {%>
									
									<tr>
										<td>
											<input type="checkbox" class="checkRow" value="<%=cartList.get(i).getProduct_code()%>">
										</td>
										<td class="shoping__cart__item">
											<img src="<%=request.getContextPath() %>/upload/<%=cartList.get(i).getProduct_img()%>" alt="<%=cartList.get(i).getProduct_name()%>" width="200px" height="150px"> 
											<h5><%=cartList.get(i).getProduct_name()%></h5>
										</td>
										<td>
											<%if(cartList.get(i).getProduct_stock()==0){ %>
												<input type="text" style="border: none; text-align: right;" class="shoping__cart__price<%=i %>" value="0">
											<%} else { %>
												<input type="text" style="border: none; text-align: right;" class="shoping__cart__price<%=i %>" value="<%=formatter.format(cartList.get(i).getProduct_price())%>">
											<%} %> 
										</td>
										<td class="shoping__cart__quantity">
											<button name="btn-down" onclick="qtyDown(this.value)" value="<%=i%>">-</button>
											<%if(cartList.get(i).getProduct_stock()==0){ %>
												<input style="width: 50px; text-align: right;" type="text" class="qty<%=i %>" value="0">
											<%} else { %>
												<input style="width: 50px; text-align: right;" type="text" class="qty<%=i %>" value="<%=cartList.get(i).getProduct_amount()%>">
											<%} %>
											<button name="btn-up" onclick="qtyUp(this.value)" value="<%=i%>">+</button>
											<input type="hidden" class="stock<%=i %>" value="<%=cartList.get(i).getProduct_stock()%>">
										</td>
										<%if(cartList.get(i).getProduct_stock()==0){ %>
											<td class="shoping__cart__total<%=i%>">0</td>
										<%} else { %>
											<td class="shoping__cart__total<%=i%>"><%=cartList.get(i).getProduct_price() * cartList.get(i).getProduct_amount()%></td>
										<%} %>
										<td class="shoping__cart__item__close">
                                        	<a href="../payment/CartDelete.pa?product_code=<%=cartList.get(i).getProduct_code()%>"><span class="icon_close"></span></a>
                                   	    </td>
									</tr>
									<%
									}
									}
									%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="shoping__cart__btns">
						<input type="button" id="select_clear_btn"class="primary-btn cart-btn" onclick="deleteAction()" value="선택삭제">
						<input type="button" id="all_clear_btn" class="primary-btn cart-btn" value="전체삭제">
						<input type="hidden" id="sessionId" value="<%=session.getAttribute("sessionId")%>">
<!-- 						<a	href="#" class="primary-btn cart-btn cart-btn-right"> -->
<!-- 							<span class="icon_loading"></span> 변경사항저장</a> -->
						<input type="button" id="update_btn" class="primary-btn cart-btn-right" value="변경사항저장" style="margin-left: 700px">
					</div>
				</div>
				<div class="col-lg-6">
					<div class="shoping__continue">
						<div class="shoping__discount">
							<h5>Discount Codes</h5>
							<form action="#">
								<input type="text" placeholder="Enter your coupon code">
								<button type="submit" class="site-btn">APPLY COUPON</button>
							</form>
						</div>
					</div>
				</div>
				<div class="col-lg-12">
					<div class="shoping__checkout">
						<h5>장바구니 총금액</h5>
						<ul>
<!-- 							<li>Subtotal <span>$454.98</span></li> -->
							<li> <span id="total_price"><%=total_price %>원</span></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<a href="./main/main.jsp" class="primary-btn cart-btn-center">쇼핑계속하기</a>
					<a href="#" class="primary-btn cart-btn-right" onclick="selectOrderAction()">선택 상품 주문하기</a>
					<a href="#" class="primary-btn cart-btn-right" onclick="allOrderAction()">전체 상품 주문하기</a>
				</div>
			</div>
		</div>
	</section>
	<!-- Shoping Cart Section End -->
	<jsp:include page="../inc/bottom.jsp"></jsp:include>
	<!-- Footer Section End -->

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