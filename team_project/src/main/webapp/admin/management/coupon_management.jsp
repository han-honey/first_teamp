<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>쿠폰</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="../inc/adminTop.jsp"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">쿠폰</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-gift"></i>
                                쿠폰
                            </div>
                       
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>쿠폰번호</th>
                                            <th width="300" >쿠폰코드</th>
                                            <th>쿠폰명</th>
                                            <th>할인정보</th>
                                            <th>발급일</th>
                                            <th>만료일</th>	
                                            <th>유효기간</th>
                                            <th>쿠폰상태</th>
                                            <th width="300" >쿠폰관리 <button class="btn btn-primary pull-right">자동관리</button></th>      
                                        </tr>
                                   </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>몰?루</td>
                                            <td>Hello</td>
                                            <td>2,000원</td>
                                            <td>2022-03-00</td>
                                            <td>2022-04-00</td>
                                            <td>7일</td>
	                                        <td>true</td>
                                            <td><button>활성화</button>
                                            	<button>비활성화</button>
                                            </td>
                                        </tr> 
                                        <tr>
                                        	<td colspan="9" align="right"><button 
                                        	class="btn btn-primary pull-right"
                                        	onclick="window.open('Popup/coupon_popup.jsp', 'coupon', 'width=800, height=500')">쿠폰생성</button></td>
                                        </tr>       
                                    </tbody>
                                    
                                </table>
                               
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
