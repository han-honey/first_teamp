<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>교환및환불</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
         <jsp:include page="../inc/adminTop.jsp"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">교환 및 환불</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-ambulance"></i>
                                교환 및 환불
                            </div>
                       
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>구매일자</th>
                                            <th>아이디</th>
                                            <th>구매코드</th>
                                            <th>결제금액</th>
                                            <th>요청날짜</th>
                                            <th>결제방식</th>
                                            <th>연락처</th>
                                            <th>사유조회</th>
                                            <th>진행사항</th>      
                                        </tr>
                                   </thead>
                                    <tbody>
                                        <tr>
                                            <td>2022-03-00</td>
                                            <td>몰?루</td>
                                            <td>abcdefg</td>
                                            <td>50000원</td>
                                            <td>2022-03-00</td>
                                            <td>카드결제</td>
                                            <td>010-1234-5678</td>
                                            <td><button>사유조회</button></td>
                                            <td>확인중</td>
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
