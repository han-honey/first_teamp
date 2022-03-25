<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Main</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="MemberList.ad">관리자페이지(로고)</a>
          	<span style="color:white; padding-left: 70%">관리자 아이디</span>
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="../main/Main.ma">판매페이지</a></li>
<!--                         <li><a class="dropdown-item" href="#!">Activity Log</a></li> -->
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="../member/MemberLogout.me">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">관리자메뉴</div>
<!--                             <a class="nav-link" href="announcement.jsp"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-bullhorn"></i></div> -->
<!--                                	공지사항 -->
<!--                             </a> -->
<!--                             <a class="nav-link" href="event.jsp"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-gift"></i></div> -->
<!--                                	이벤트 -->
<!--                             </a> -->
                         	<a class="nav-link" href="MemberList.ad">
                                <div class="sb-nav-link-icon"><i class="fas fa-address-book"></i></div>
                               	회원관리
                            </a>
                      		<a class="nav-link" href="OrderList.ad">
                                <div class="sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></div>
                               	주문관리
                            </a>
<!--                             <a class="nav-link" href="Ex_and_Re.jsp"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-ambulance"></i></div> -->
<!--                                	교환 및 환불 내역 -->
<!--                             </a> -->
     
                            <a class="nav-link" href="AdminChart.ad">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                매출차트
                            </a>
                            <a class="nav-link" href="AdminProductList.ad">
                                <div class="sb-nav-link-icon"><i class="fas fa-cubes"></i></div>
                               	상품관리
                            </a>
                            <a class="nav-link" href="#">
                                <div class="sb-nav-link-icon"><i class="fas fa-ambulance"></i></div>
                               	배송관리(준비중)
                            </a>
                            <a class="nav-link" href="#">
                                <div class="sb-nav-link-icon"><i class="fas fa-gift"></i></div>
                               	쿠폰관리(준비중)
                            </a>
<!--                             <a class="nav-link" href="QnA.jsp"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-comment"></i></div> -->
<!--                                	1:1문의글 -->
<!--                             </a> -->
                        </div>
                    </div> 
                </nav>
            </div>
            
</body>
</html>