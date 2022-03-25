<%@page import="vo.PageInfo"%>
<%@page import="vo.ProductBoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
ArrayList<ProductBoardDTO> list = (ArrayList<ProductBoardDTO>)request.getAttribute("list");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");

int pageNum = pageInfo.getPageNum(); 
int maxPage = pageInfo.getMaxPage(); 
int startPage = pageInfo.getStartPage(); 
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount(); 
%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>상품관리</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
    <body class="sb-nav-fixed">
       <jsp:include page="./inc/adminTop.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">상품관리</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-cubes"></i>
                                상품관리
                            </div>
                       
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>제품명</th>
                                            <th>제품코드</th>
                                            <th>소속카테고리1</th>
                                            <th>소속카테고리2</th>
                                            <th>등록일</th>
                                            <th>재고량</th>
                                            <th>가격</th>
                                            <th>상품등록</th>      
                                        </tr>
                                   </thead>
                                    <tbody>
                                      <%for(ProductBoardDTO dto :list) {%>  
                                        <tr>
                                            <td><%=dto.getProduct_name() %></td>
                                            <td><%=dto.getProduct_code() %></td>
                                            <td><%=dto.getProduct_category1() %></td>
                                            <td><%=dto.getProduct_category2() %></td>
                                            <td><%=dto.getProduct_date() %></td>
                                            <td><%=dto.getProduct_stock() %></td>
                                            <td><%=dto.getProduct_price() %></td>
                                            <td>
                                               <button onclick="location.href='./AdminProductModifyForm.ad?product_code=<%=dto.getProduct_code()%>'">수정</button> 
                                               <button onclick="location.href='./AdminProductDeletePro.ad?product_idx=<%=dto.getProduct_idx()%>'">삭제</button>
                                            </td>
                                        </tr> 
                                        <%} %>
                                        <tr><td  colspan="8" align="right"><button
                                        class="btn btn-primary pull-right"
                                         onclick="window.open('Popup/AdminProductWriteForm.ad', 'product', 'width=600, height=500')">상품등록</button></td></tr>
                                    </tbody>
                                    
                                </table>
                                <%if(pageNum == startPage) { %>
												이전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<%} else { %>
												<a href="./AdminProductList.ad?page=<%=pageNum - 1%>">이전</a>

											 <%} %>
											 
					                 	    <%for(int i = startPage; i <= endPage; i++) { %>
											  <%if(i == pageNum) { %>
												<a href="./AdminProductList.ad?page=<%=i%>"><%=i %></a>
											   <%} %>
										    <%} %>
									     	<%if(pageNum == endPage) { %>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;다음							
										   <%} else { %>
											  <a href="./AdminProductList.ad?page=<%=pageNum + 1%>">다음</a>
											<%} %>	
                               
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
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
