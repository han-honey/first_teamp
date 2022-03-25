<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%
	ArrayList<MemberDTO> memberList = (ArrayList<MemberDTO>)request.getAttribute("memberList");
	
	%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>회원관리</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
       <!-- TOP -->
       <jsp:include page="../inc/adminTop.jsp"></jsp:include>
       <!-- TOP -->
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">회원관리</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-address-book"></i>
                                회원관리
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th width="100">아이디</th>
                                            <th width="100">이름</th>
                                            <th width="120">마케팅동의</th>
                                            <th width="250">E-Mail</th>
                                            <th width="200">전화번호</th>
                                            <th width="120">총구매액</th>
                                            <th width="120">가입날짜</th>
                                            <th width="120">최근접속일</th>
                                            <th width="100">회원등급</th>
                                            <th width="100">적립금</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th width="100">아이디</th>
                                            <th width="100">이름</th>
                                            <th width="120">마케팅동의</th>
                                            <th width="250">E-Mail</th>
                                            <th width="200">전화번호</th>
                                            <th width="120">총구매액</th>
                                            <th width="120">가입날짜</th>
                                            <th width="120">최근접속일</th>
                                            <th width="100">회원등급</th>
                                            <th width="100">적립금</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<c:forEach var="MemberDTO" items="${memberList }">
	                                        <tr>
	                                            <td>${MemberDTO.getMember_id() }</td>
	                                            <td>${MemberDTO.getMember_name() }</td>
	                                            <td>${MemberDTO.getMember_marketing_agree() }</td>
	                                            <td>${MemberDTO.getMember_email() }</td>
	                                            <td>${MemberDTO.getMember_phone() }</td>
	                                            <td>
<%-- 	                                            <% --%>
<!-- 	                                            DecimalFormat formatter = new DecimalFormat("###,###");  -->
<%-- 	                                            %> --%>
											<fmt:formatNumber value="${MemberDTO.getMember_total_purchase() }" type="number" />
<%-- 	                                            ${formatter.format(MemberDTO.getMember_total_purchase()) } --%>
	                                            </td>
	                                            <td>${MemberDTO.getMember_joinDate()}</td>
	                                            <td>${MemberDTO.getRecent_login_date() }</td>
	                                            <td>${MemberDTO.getMembership_grade() }</td>
                                            	<td>
                                            		<fmt:formatNumber value="${MemberDTO.getEarning_point() }" type="number" />
                                            	</td>
												
	                                        </tr> 
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>