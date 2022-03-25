<%@page import="java.util.ArrayList"%>
<%@page import="dao.WishlistDAO"%>
<%@page import="vo.WishlistDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
if(session.getAttribute("sessionId")==null){
	%>
	<script type="text/javascript">
	alert('회원 전용 서비스 입니다\n로그인이 필요합니다.')
	location.href="../member/MemberLoginForm.me";
	</script>
	<%
}
ArrayList<WishlistDTO> wishlist = (ArrayList<WishlistDTO>)request.getAttribute("wishlist");
System.out.println("wishlist.jsp - action에서 가져온 wishlist : " + wishlist);
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
<script src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	var code = $('#product_code').val();
	$('#addCart').click(function() {
			$.ajax({
				type: 'post',
				url: '../payment/CartInsert.pa',
				data: {
					product_code: $('#product_code').val(),
					product_price: $('#product_price').val(),
					product_amount: 1,
				},
				dataType: 'text',
				success: function(response) {
					alert('장바구니 추가 완료!');
					location.href='../payment/WishlistDelete.pa?product_code=' + code;
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

    <!-- Humberger Begin -->
    <jsp:include page="../inc/top.jsp" />
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="../img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>WISHLIST</h2>
                        <div class="breadcrumb__option">
                            <a href="../index.jsp">HOME</a>
                            <span>WISHLIST</span>
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
                                    <th colspan="2" class="shoping__product">Products</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            if(wishlist != null && wishlist.size() > 0) {
                            	
                               	for(WishlistDTO wishlistDTO : wishlist) {
                            %>
                                <tr id="wishList">
                                    <td class="shoping__cart__img">
<!--                                         <img src="../img/cart/cart-1.jpg" alt=""> -->
                                        <img src="<%=request.getContextPath() %>/upload/<%=wishlistDTO.getProduct_img() %>" >
                                    </td>
                                    <td class="shoping__cart__item">
                                        <h5><%=wishlistDTO.getProduct_name() %></h5>
                                    </td>
                                    <td class="shoping__cart__price">
                                    	<h5><%=wishlistDTO.getProduct_price() %></h5>
                                    </td>
                                    <td>
										<div class="shoping__cart__btns">
	                   						<a href="../payment/WishlistDelete.pa?product_code=<%=wishlistDTO.getProduct_code()%>" 
	                   						class="primary-btn cart-btn cart-btn-right"> DELETE </a>
                   						</div>
                   					</td>
                   					<td>
                   						<div class="shoping__cart__btns">
	                   						<a class="primary-btn cart-btn cart-btn-right" id="addCart" >ADD CART</a>
                   						</div>
								    	<input type="hidden" id="product_code" value="<%=wishlistDTO.getProduct_code() %>"> <!-- cart에 저장하기 위한 데이터 -->
								    	<input type="hidden" id="product_price" value="<%=wishlistDTO.getProduct_price() %>"> <!-- cart에 저장하기 위한 데이터 -->
                                    </td>
                                </tr>
                            	<%
                            	}
                            } else {
                            	%>
                				<tr>
                					<td colspan="5">위시리스트가 비어있습니다.</td>
                				</tr>
                				<%} %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
<!--                         <a href="#" class="primary-btn cart-btn">CONTINUE SHOPPING</a> -->
	                    <a href="../payment/WishlistDelete.pa?product_code=ALLDELETE" class="primary-btn cart-btn cart-btn-right">ALL DELETE</a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__continue">
                        <div class="shoping__discount">
<!--                             <h5>Discount Codes</h5> -->
<!--                             <form action="#"> -->
<!--                                 <input type="text" placeholder="Enter your coupon code"> -->
<!--                                 <button type="submit" class="site-btn">APPLY COUPON</button> -->
<!--                             </form> -->
                        </div>
                    </div>
                </div>
<!--                 <div class="col-lg-6"> -->
<!--                     <div class="shoping__checkout"> -->
<!--                         <h5>Cart Total</h5> -->
<!--                         <ul> -->
<!--                             <li>Subtotal <span>$454.98</span></li> -->
<!--                             <li>Total <span>$454.98</span></li> -->
<!--                         </ul> -->
<!--                         <a href="#" class="primary-btn">PROCEED TO CHECKOUT</a> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->

    <!-- Footer Section Begin -->
    <jsp:include page="../inc/bottom.jsp" />
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