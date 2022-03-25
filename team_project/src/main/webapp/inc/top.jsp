<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
</head>

<body>   	
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="../main/Main.ma"><img src="../img/logo1.jpg" width="119px" height="100px"></a>
		</div>
		<div class="humberger__menu__cart">
			<ul>
				<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
				<li><a href="#"><i class="fa fa-shopping-bag"></i><span>3</span></a></li>
			</ul>
	
		</div>
		<div class="humberger__menu__widget">
			<div class="header__top__right__language">
				<img src="../img/language.png" alt="">
				<div>English</div>
				<span class="arrow_carrot-down"></span>
<!-- 				<ul> -->
<!-- 					<li><a href="#">Spanis</a></li> -->
<!-- 					<li><a href="#">English</a></li> -->
<!-- 				</ul> -->
			</div>
			<%if(session.getAttribute("sessionId") == null)  { %>
			<div class="header__top__right__auth">
				<a href="../member/MemberLoginForm.me"><i class="fa fa-user"></i> 로그인</a>
			</div>
			<div class="header__top__right__auth">
				<a href="../member/MemberAgreeForm.me"><i class="fa fa-user"></i> 회원가입</a>
			</div>
			<%} else { %>
				<%if(session.getAttribute("sessionId") == "admin") {%>
					<div>
						<i class="fa fa-user"></i><%=session.getAttribute("sessionId")%>님
						<a href="../admin/AdminPage.ad">관리자페이지</a>
					</div>
				<%} else { %>
					<div>
						<i class="fa fa-user"></i><%=session.getAttribute("sessionId")%>님
							<ul class="user__menu__dropdown"> <!-- 드롭다운 해주세요 ㅠ_ㅠ -->
								<li><a href="#">My페이지</a></li>
								<li><a href="#">주문내역</a></li>
								<li><a href="#">찜리스트</a></li>
								<li><a href="#">장바구니</a></li>
								<li><a href="../member/MemberLogout.me">로그아웃</a></li>
							</ul>
					</div>
				<%} %>
			<%} %>
		</div>
		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<li class="active"><a href="../main/Main.ma">홈</a></li>
				<li><a href="./shop-grid.jsp">쇼핑하기</a></li>
				<li><a href="#">Pages</a>
					<ul class="header__menu__dropdown">
						<li><a href="./shop-details.jsp">Shop Details</a></li>
						<li><a href="./shoping-cart.jsp">Shoping Cart</a></li>
						<li><a href="../payment/CheckoutForm.pa">Check Out</a></li>
					</ul>
				</li>
				<li><a href="#">고객센터</a>
					<ul class="header__menu__dropdown">
						<li><a href="../center/Notice.bo">공지사항</a></li>
						<li><a href="../center/EventBoard.bo">이벤트</a></li>
						<li><a href="../center/QnAList.bo">1:1문의</a></li>
					</ul>
				</li>
				<li><a href="./contact.jsp">Contact</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="header__top__right__social">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
			<a href="#"><i class="fa fa-pinterest-p"></i></a>
		</div>
		<div class="humberger__menu__contact">
			<ul>
				<li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
				<li>Free Shipping for all Order of $99</li>
			</ul>
		</div>
	</div>
	
	
		<!----------------------------- 여기서부터 밑에줄들을 수정해야 수정 됨 ---------------------------->
		
		
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
<!-- 						<div class="header__top__left"> -->
<!-- 							<ul> -->
<!-- 								<li><i class="fa fa-envelope"></i> hello@colorlib.com</li> -->
<!-- 								<li>Free Shipping for all Order of $99</li> -->
<!-- 							</ul> -->
<!-- 						</div> -->
					</div>
					<div class="col-lg-6">
						<div class="header__top__right">
<!-- 							<div class="header__top__right__social"> -->
<!-- 								<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i -->
<!-- 									class="fa fa-twitter"></i></a> <a href="#"><i -->
<!-- 									class="fa fa-linkedin"></i></a> <a href="#"><i -->
<!-- 									class="fa fa-pinterest-p"></i></a> -->
<!-- 							</div> -->
							<div class="header__top__right__language">
								<img src="../img/korea.png" alt=""> <!-- 이거 누가 태극기로 쫌 바꿔줘요..ㅎㅎ --> 
								<div>한국어</div>
							</div>
						<%if(session.getAttribute("sessionId") == null)  { %>
							<div class="header__top__right__auth">
								<a href="../member/MemberLoginForm.me"><i class="fa fa-user"></i> 로그인</a>
							</div>
							<div class="header__top__right__auth">
								<a href="../member/MemberAgreeForm.me"><i class="fa fa-user"></i> 회원가입</a>
							</div>
						<%} else { %>
							<%if(session.getAttribute("sessionId").equals("admin")) {%>
								<div class="header__top__right__language">
									<i class="fa fa-user"></i>&nbsp;<%=session.getAttribute("sessionId")%>님&nbsp;
									<span class="arrow_carrot-down"></span>
									<ul>
										<li><a href="../admin/AdminMain.ad">관리자페이지</a></li>
										<li><a href="../member/MemberLogout.me">로그아웃</a></li>
									</ul>
									
								</div>
							<%} else { %>
								<div class="header__top__right__language">
									<div><%=session.getAttribute("sessionId")%>&nbsp;님&nbsp;</div>
									<span class="arrow_carrot-down"></span>
									<ul>
										<li><a href="../member/MemberInfo.me">My페이지</a></li>
									<li><a href="../payment/OrderList.pa">주문내역</a></li>
									<li><a href="../payment/Wishlist.pa">위시리스트</a></li>
									<li><a href="../payment/CartList.pa">장바구니</a></li>
									<li><a href="../member/MemberLogout.me">로그아웃</a></li>
									</ul>
								</div>
							<%} %>
						<%} %>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="../main/Main.ma"><img src="../img/logo1.jpg" width="119px" height="100px" style="border-radius: 30%;"></a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li><a href="../main/Main.ma">홈</a></li>
							<li class="active"><a href="../product/BoardProductList.bo">쇼핑하기</a></li>
							<li><a href="#">계절상품</a>
<!-- 								<ul class="header__menu__dropdown"> -->
<!-- 									<li><a href="#">베스트 of 판매</a></li> -->
<!-- 									<li><a href="#">베스트 of 리뷰</a></li> -->
<!-- 									<li><a href="#">베스트 of 특가</a></li> -->
<!-- 									<li><a href="#">Blog Details</a></li> -->
<!-- 								</ul> -->
									</li>
							<li><a href="../center/NoticeList.bo">고객센터</a>
								<ul class="header__menu__dropdown">
									<li><a href="../center/NoticeList.bo">공지사항</a></li>
									<li><a href="../center/EventList.bo">이벤트</a></li>
									<li><a href="../center/QnAList.bo">1:1문의</a></li>
								</ul>
							</li>
							<li><a href="../center/Contact.bo">Contact</a></li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-3">
					<div class="header__cart">
						<ul>
							<li><a href="../payment/Wishlist.pa"><i class="fa fa-heart"></i></a></li>
							<li><a href="../payment/CartList.pa"><i class="fa fa-shopping-bag"></i></a></li>
						</ul>
<!-- 						<div class="header__cart__price"> -->
<!-- 							가격 : <span>50,000원</span> -->
<!-- 						</div> -->
					</div>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <span>카테고리</span>
                        </div>
                        <ul>
                            <li><a href="../product/BoardProductList.bo?product_category1=과일">과일</a></li>
                            <li><a href="../product/BoardProductList.bo?product_category1=채소">채소</a></li>
                            <li><a href="../product/BoardProductList.bo?product_category1=곡류">쌀, 잡곡</a></li>
                            <li><a href="../product/BoardProductList.bo?product_category1=버섯">버섯</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="../product/BoardProductList.bo">
<!--                                 <div class="hero__search__categories"> -->
<!--                                     전체 -->
<!--                                     <span class="arrow_carrot-down"></span> -->
<!--                                 </div> -->
                                <input type="text" name="searchProductName" placeholder="상품명을 검색하세요">
                                <button type="submit" class="site-btn">검색</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>1644-1885</h5>
                                <span>오전 7시 - 오후 7시(매일)</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>