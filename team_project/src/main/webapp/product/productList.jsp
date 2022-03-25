<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ProductBoardDTO"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<ProductBoardDTO> list = (ArrayList<ProductBoardDTO>)request.getAttribute("list");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
String member_id = (String)session.getAttribute("sessionId");
DecimalFormat formatter = new DecimalFormat("###,###");


// if(list == null){
// 	System.out.println("list - null");
// }

int pageNum = pageInfo.getPageNum(); 
int maxPage = pageInfo.getMaxPage(); 
int startPage = pageInfo.getStartPage(); 
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount(); 

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
function addShop(index) {
	if(<%=member_id == null%>){
		alert('로그인해 주세요!')
		location.href = '../member/MemberLoginForm.me';
	} else {
		$.ajax({
			type: 'post',
			url: '../payment/CartInsert.pa',
			data: {
				product_code: $('.product_code').eq(index).val(),
				product_price: $('.product_price').eq(index).val(),
				product_amount: 1,
			},
			dataType: 'text',
			success: function(response) {
				var isTrue = confirm('장바구니 추가 완료!\n장바구니로 이동하시겠습니까?');
				if(isTrue){
					location.href='../payment/CartList.pa';
				}
				
			},
			error: function(xhr, textStatus, errorThrown) {
				alert(xhr + "" + textStatus + "" + errorThrown);
			}
		});
	}
}
		


function addWishlist(index) {
		if(<%=member_id == null%>){
			alert('로그인해 주세요!')
			location.href = '../member/MemberLoginForm.me';
		} else {
			$.ajax({
				type: 'post',
				url: '../payment/WishlistAdd.pa',
				data: {
					product_code: $('.product_code').eq(index).val(),
				},
				dataType: 'text',
				success: function(response) {
					alert('위시리스트에 추가되었습니다!')
				},
				error: function(xhr, textStatus, errorThrown) { 
					alert(xhr + "" + textStatus + "" + errorThrown);
					
				}
			});
		}
};
</script>
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
	<jsp:include page="../inc/top.jsp"></jsp:include>
    <!-- Hero Section Begin -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="../img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Organi Shop</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.jsp">Home</a>
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-5"	>
                    <div class="sidebar">
                        <div class="sidebar__item">
                            <h4>Department</h4>
                            <ul>
                                <li><a href="../product/BoardProductList.bo?product_category1=과일">과일</a></li>
                                <li><a href="../product/BoardProductList.bo?product_category1=채소">채소</a></li>
                                <li><a href="../product/BoardProductList.bo?product_category1=곡류">쌀 & 잡곡</a></li>
                                <li><a href="../product/BoardProductList.bo?product_category1=버섯">버섯</a></li>
                            </ul>
                        </div>
                        <div class="sidebar__item">
                        </div>
                        <div class="sidebar__item sidebar__item__color--option">
                        </div>
                        <div class="sidebar__item">
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                               
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-7">
                    <div class="filter__item">
                        <div class="row">
                            <div class="col-lg-4 col-md-5">
                                <div class="filter__sort">
                                    <span>Sort By</span>
                                    <select>
                                        <option value="0">Default</option>
                                        <option value="0">Default</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="filter__found">
                                    <h6><span>16</span> Products found</h6>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3">
                                <div class="filter__option">
                                    <span class="icon_grid-2x2"></span>
                                    <span class="icon_ul"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                       <%for(int i=0; i<list.size(); i++) {%>
                        <div onclick ="#" class="col-lg-4 col-md-6 col-sm-6" style="margin: 30px 0;">
                        	<div class="product__item">
	                            <a href="../product/BoardProductContent.bo?product_code=<%=list.get(i).getProduct_code()%>&page=1">
	                            	<img src="<%=request.getContextPath() %>/upload/<%=list.get(i).getProduct_img()%>" width="300px" height="250px">
	                            </a>
                                <ul class="product__item__pic__hover">
                                	<%if(list.get(i).getProduct_stock() == 0){ %>
	                                    <li onclick="alert('재고가 없습니다')" value="<%=i%>" class="heart"><a><i class="fa fa-heart"></i></a></li>
	                                    <li><a onclick="location.reload()"><i class="fa fa-retweet"></i></a></li>
	                                    <li onclick="alert('재고가 없습니다')" value="<%=i%>" class="shopping_cart"><a><i class="fa fa-shopping-cart"></i></a></li>
                                    <%} else { %>
                                    	<li onclick="addWishlist(this.value)" value="<%=i%>" class="heart"><a><i class="fa fa-heart"></i></a></li>
	                                    <li><a onclick="location.reload()"><i class="fa fa-retweet"></i></a></li>
	                                    <li onclick="addShop(this.value)" value="<%=i%>" class="shopping_cart"><a><i class="fa fa-shopping-cart"></i></a></li>
                                    <%} %>
                                </ul>
                            </div>
                            <div class="product__item__text">
					 			<h6><a href="../product/BoardProductContent.bo?product_code=<%=list.get(i).getProduct_code()%>"><%=list.get(i).getProduct_name() %></a></h6>
                                <h5><%= formatter.format(list.get(i).getProduct_price()) %>원</h5>
                                <input type="hidden" class="product_price" value="<%=list.get(i).getProduct_price()%>"> <!-- 장바구니 이동을 위한 hidden -->
                       			<input type="hidden" class="product_code" value="<%=list.get(i).getProduct_code()%>"> <!-- 장바구니 이동을 위한 hidden -->
                            </div>
                        </div>
                        <%} %>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

    <!-- Footer Section Begin -->
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