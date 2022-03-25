<%@page import="vo.ProductBoardDTO"%>
<%@page import="vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<ProductBoardDTO> list = (ArrayList<ProductBoardDTO>)request.getAttribute("list");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
String member_id = (String)session.getAttribute("sessionId");
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
$(document).ready(function () {
	$('#shopping_cart').click(function() {
		if(<%=member_id == null%>){
			alert('로그인해 주세요!')
			location.href = '../member/MemberLoginForm.me';
		} else {
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
					alert('장바구니에 추가되었습니다!')
				},
				error: function(xhr, textStatus, errorThrown) {
					alert(xhr + "" + textStatus + "" + errorThrown);
				}
			});
		}
	});
		
	$('#heart').click(function() {
		if(<%=member_id == null%>){
			alert('로그인해 주세요!')
			location.href = '../member/MemberLoginForm.me';
		} else {
			$.ajax({
				type: 'post',
				url: '../payment/WishlistAdd.pa',
				data: {
					product_code: $('#product_code').val(),
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
	})
});
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
                <div class="col-lg-3 col-md-5">
                    <div class="sidebar">
                        <div class="sidebar__item">
                            <h4>Department</h4>
                            <ul>
                                <li><a href="#">Fresh Meat</a></li>
                                <li><a href="#">Vegetables</a></li>
                                <li><a href="#">Fruit & Nut Gifts</a></li>
                                <li><a href="#">Fresh Berries</a></li>
                                <li><a href="#">Ocean Foods</a></li>
                                <li><a href="#">Butter & Eggs</a></li>
                                <li><a href="#">Fastfood</a></li>
                                <li><a href="#">Fresh Onion</a></li>
                                <li><a href="#">Papayaya & Crisps</a></li>
                                <li><a href="#">Oatmeal</a></li>
                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Price</h4>
                            <div class="price-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                    data-min="10" data-max="540">
                                    <div class="ui-slider-range ui-corner-all ui-widget-header"></div>
                                    <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                                    <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                                </div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <input type="text" id="minamount">
                                        <input type="text" id="maxamount">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="sidebar__item sidebar__item__color--option">
                            <h4>Colors</h4>
                            <div class="sidebar__item__color sidebar__item__color--white">
                                <label for="white">
                                    White
                                    <input type="radio" id="white">
                                </label>
                            </div>
                            <div class="sidebar__item__color sidebar__item__color--gray">
                                <label for="gray">
                                    Gray
                                    <input type="radio" id="gray">
                                </label>
                            </div>
                            <div class="sidebar__item__color sidebar__item__color--red">
                                <label for="red">
                                    Red
                                    <input type="radio" id="red">
                                </label>
                            </div>
                            <div class="sidebar__item__color sidebar__item__color--black">
                                <label for="black">
                                    Black
                                    <input type="radio" id="black">
                                </label>
                            </div>
                            <div class="sidebar__item__color sidebar__item__color--blue">
                                <label for="blue">
                                    Blue
                                    <input type="radio" id="blue">
                                </label>
                            </div>
                            <div class="sidebar__item__color sidebar__item__color--green">
                                <label for="green">
                                    Green
                                    <input type="radio" id="green">
                                </label>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <h4>Popular Size</h4>
                            <div class="sidebar__item__size">
                                <label for="large">
                                    Large
                                    <input type="radio" id="large">
                                </label>
                            </div>
                            <div class="sidebar__item__size">
                                <label for="medium">
                                    Medium
                                    <input type="radio" id="medium">
                                </label>
                            </div>
                            <div class="sidebar__item__size">
                                <label for="small">
                                    Small
                                    <input type="radio" id="small">
                                </label>
                            </div>
                            <div class="sidebar__item__size">
                                <label for="tiny">
                                    Tiny
                                    <input type="radio" id="tiny">
                                </label>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                                <h4>Latest Products</h4>
                                <div class="latest-product__slider owl-carousel">
                                    <div class="latest-prdouct__slider__item">
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="../img/latest-product/lp-1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Crab Pool Security</h6>
                                                <span>$30.00</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="../img/latest-product/lp-2.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Crab Pool Security</h6>
                                                <span>$30.00</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="../img/latest-product/lp-3.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Crab Pool Security</h6>
                                                <span>$30.00</span>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="latest-prdouct__slider__item">
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="../img/latest-product/lp-1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Crab Pool Security</h6>
                                                <span>$30.00</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="../img/latest-product/lp-2.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Crab Pool Security</h6>
                                                <span>$30.00</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="../img/latest-product/lp-3.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Crab Pool Security</h6>
                                                <span>$30.00</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-7">
                    <div class="product__discount">
                        <div class="section-title product__discount__title">
                            <h2>Sale Off</h2>
                        </div>
                        <div class="row">
                            <div class="product__discount__slider owl-carousel">
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="../img/product/discount/pd-1.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Dried Fruit</span>
                                            <h5><a href="#">Raisin’n’nuts</a></h5>
                                            <div class="product__item__price">$30.00 <span>$36.00</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="../img/product/discount/pd-2.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Vegetables</span>
                                            <h5><a href="#">Vegetables’package</a></h5>
                                            <div class="product__item__price">$30.00 <span>$36.00</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="../img/product/discount/pd-3.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Dried Fruit</span>
                                            <h5><a href="#">Mixed Fruitss</a></h5>
                                            <div class="product__item__price">$30.00 <span>$36.00</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="../img/product/discount/pd-4.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Dried Fruit</span>
                                            <h5><a href="#">Raisin’n’nuts</a></h5>
                                            <div class="product__item__price">$30.00 <span>$36.00</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="../img/product/discount/pd-5.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Dried Fruit</span>
                                            <h5><a href="#">Raisin’n’nuts</a></h5>
                                            <div class="product__item__price">$30.00 <span>$36.00</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="../img/product/discount/pd-6.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Dried Fruit</span>
                                            <h5><a href="#">Raisin’n’nuts</a></h5>
                                            <div class="product__item__price">$30.00 <span>$36.00</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                       <%for(ProductBoardDTO dto :list) {%>
                        <div onclick ="#" class="col-lg-4 col-md-6 col-sm-6" style="margin: 30px 0; border: 1px solid;">
                        	<div class="product__item">
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-8.jpg"> -->
                              
                            <a href="../product/BoardProductContent.bo?product_code=<%=dto.getProduct_code()%>">
                            	<img src="../<%=dto.getProduct_img()%>" >
                            </a>
                                <ul class="product__item__pic__hover">
                                    <li><a><i class="fa fa-heart" id="heart"></i></a></li>
                                    <li><a onclick="location.reload()"><i class="fa fa-retweet"></i></a></li>
                                    <li><a><i class="fa fa-shopping-cart" id="shopping_cart"></i></a></li>
                                </ul>
                            </div>
                            <div class="product__item__text">
					 			<h6><a href="../product/BoardProductContent.bo?product_code=<%=dto.getProduct_code()%>"><%=dto.getProduct_name() %></a></h6>
                                <h5><%=dto.getProduct_price() %>원</h5>
                                <input type="hidden" id="product_price" value="<%=dto.getProduct_price()%>"> <!-- 장바구니 이동을 위한 hidden -->
                       			<input type="hidden" id="product_code" value="<%=dto.getProduct_code()%>"> <!-- 장바구니 이동을 위한 hidden -->
                            </div>
                       </div>
                       
                        <%} %>

<!--                         <input type="button" value="글쓰기" onclick="location.href='../shop/BoardProductForm.ma'"> -->
                        <!-- ==================================================================== -->
                        
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-2.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-3.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-4.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-5.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-6.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-7.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-8.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-9.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-10.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-11.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="col-lg-4 col-md-6 col-sm-6"> -->
<!--                             <div class="product__item"> -->
<!--                                 <div class="product__item__pic set-bg" data-setbg="../img/product/product-12.jpg"> -->
<!--                                     <ul class="product__item__pic__hover"> -->
<!--                                         <li><a href="#"><i class="fa fa-heart"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-retweet"></i></a></li> -->
<!--                                         <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li> -->
<!--                                     </ul> -->
<!--                                 </div> -->
<!--                                 <div class="product__item__text"> -->
<!--                                     <h6><a href="#">Crab Pool Security</a></h6> -->
<!--                                     <h5>$30.00</h5> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
                  <!--   <div class="product__pagination">
                        <a href="#">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div> -->
<!--                       <div class="product__pagination"> -->
<%--                       	<%if(pageNum == startPage) { %> --%>
<!-- 							<a href="#"><i class="fa fa-long-arrow-right"></i></a> -->
<%-- 						<%} else { %> --%>
<%-- 							<a href="../shop/BoardProductList.ma?page=<%=pageNum - 1%>"></a> --%>
<%-- 						<%} %> --%>
<%--                  	 <%for(int i = startPage; i <= endPage; i++) { %> --%>
<%-- 						<%if(i == pageNum) { %> --%>
<%-- 							<a href="../shop/BoardProductList.ma?page=<%=i%>"><%=i %></a> --%>
<%-- 						<%} %> --%>
<%-- 					<%} %> --%>
<%-- 					<%if(pageNum == endPage) { %> --%>
<!-- 						<a href="#"><i class="fa fa-long-arrow-right"></i></a> -->
<%-- 					<%} else { %> --%>
<%-- 						<a href="../shop/BoardProductList.ma?page=<%=pageNum + 1%>"></a> --%>
<%-- 					<%} %> --%>
                        
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