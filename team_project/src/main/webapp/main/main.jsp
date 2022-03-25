<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.ProductBoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
ArrayList<ProductBoardDTO> newRankingList = (ArrayList<ProductBoardDTO>)request.getAttribute("newRankingList");
ArrayList<ProductBoardDTO> sellRankingList = (ArrayList<ProductBoardDTO>)request.getAttribute("sellRankingList");
ArrayList<ProductBoardDTO> starScoreRankingList = (ArrayList<ProductBoardDTO>)request.getAttribute("starScoreRankingList");
DecimalFormat formatter = new DecimalFormat("###,###");
%>
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
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    	<jsp:include page="../inc/top.jsp" />
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <!-- Hero Section End -->

    <!-- Categories Section Begin -->
    <section class="categories">
		<div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="../img/categories/fruit.png">
                            <h5><a href="../product/BoardProductList.bo?product_category1=과일">FRUIT</a></h5>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="../img/categories/vegetable.png">
                            <h5><a href="../product/BoardProductList.bo?product_category1=채소">VEGETABLE</a></h5>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="../img/categories/grain.png">
                            <h5><a href="../product/BoardProductList.bo?product_category1=곡류">GRAIN</a></h5>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="../img/categories/mushroom.png">
                            <h5><a href="#">mushroom</a></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <!-- Featured Section End -->

    <!-- Banner Begin -->
    <br><br><br>
    <div class="banner">
	    <div class="section-title">
			<h2>EVENT</h2>
	    </div>
	    <div class="container">
	        <div class="row">
	            <div class="col-lg-6 col-md-6 col-sm-6">
	                <div class="banner__pic">
	                    <a href="../center/EventContent.bo?idx=11&page=1"><img src="../img/banner/banner-1.jpg" alt="" ></a>
	                </div>
	            </div>
	            <div class="col-lg-6 col-md-6 col-sm-6">
	                <div class="banner__pic">
	                    <a href="../center/EventContent.bo?idx=8&page=1"><img src="../img/banner/banner-2.jpg" alt=""></a>
	                </div>
	            </div>
	        </div>
	    </div>
    </div>
    <!-- Banner End -->

    <!-- Latest Product Section Begin -->
    <br><br><br>
    <div class="section-title">
			<h2>RANKING</h2>
	</div>
    <section class="latest-product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>신규 품목</h4>
<!--                         <div class="latest-product__slider owl-carousel"> -->
							<div class="latest-prdouct__slider__item">
                            	<%for(ProductBoardDTO dto : newRankingList) {%>
	                                <a href="../product/BoardProductContent.bo?product_code=<%=dto.getProduct_code()%>" class="latest-product__item">
	                                    <div class="latest-product__item__pic">
	                                        <img src="<%=request.getContextPath() %>/upload/<%=dto.getProduct_img()%>" alt="">
	                                    </div>
	                                    <div class="latest-product__item__text">
	                                        <h6><%=dto.getProduct_name()%></h6>
	                                        <span><%=formatter.format(dto.getProduct_price())%> 원</span>
	                                    </div>
	                                </a>
                            	
                            	<%} %>
                            </div>	
<!--                         </div> -->
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>최다 판매 제품</h4>
<!--                         <div class="latest-product__slider owl-carousel"> -->
                            <div class="latest-prdouct__slider__item">
                            	<%for(ProductBoardDTO dto : sellRankingList) {%>
	                                <a href="../product/BoardProductContent.bo?product_code=<%=dto.getProduct_code()%>" class="latest-product__item">
	                                    <div class="latest-product__item__pic">
	                                        <img src="<%=request.getContextPath() %>/upload/<%=dto.getProduct_img()%>" alt="">
	                                    </div>
	                                    <div class="latest-product__item__text">
	                                        <h6><%=dto.getProduct_name()%></h6>
	                                        <span><%=formatter.format(dto.getProduct_price())%> 원</span>
	                                    </div>
	                                </a>
                            	
                            	<%} %>
                            	
                            </div>
<!--                         </div> -->
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>최고 평점 상품</h4>
<!--                         <div class="latest-product__slider owl-carousel"> -->
                            <div class="latest-prdouct__slider__item">
                                <%for(ProductBoardDTO dto : starScoreRankingList) {%>
	                                <a href="../product/BoardProductContent.bo?product_code=<%=dto.getProduct_code()%>" class="latest-product__item">
	                                    <div class="latest-product__item__pic">
	                                        <img src="<%=request.getContextPath() %>/upload/<%=dto.getProduct_img()%>" alt="">
	                                    </div>
	                                    <div class="latest-product__item__text">
	                                        <h6><%=dto.getProduct_name()%></h6>
	                                        <span><%=formatter.format(dto.getProduct_price())%> 원</span>
	                                    </div>
	                                </a>
                            	
                            	<%} %>
                            </div>
                            
<!--                         </div> -->
                    </div>
                </div>
            </div>
        </div>
        <br><br><br><br>
    </section>
    <!-- Latest Product Section End -->


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