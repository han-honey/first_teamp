<%@page import="java.text.DecimalFormat"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.ProductBoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ProductBoardDTO> list = (ArrayList<ProductBoardDTO>)request.getAttribute("list");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
DecimalFormat formatter = new DecimalFormat("###,###");

int pageNum = pageInfo.getPageNum(); 
int maxPage = pageInfo.getMaxPage(); 
int startPage = pageInfo.getStartPage(); 
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount(); 
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script type="text/javascript">
function goAddPopup(index) {
	var str = window.open('Popup/product_add_popup.jsp', 'product', 'width=600, height=500')
// 	if(str != null){
// 		str.close();
// 	}
}
function goUpdatePopup(index) {
	var product_code = $('.code').eq(index).text();
	const childOpenWindow = window.open('Popup/product_update_popup.jsp?product_code='+product_code, 'product', 'width=600, height=500');
}
</script>
<title>Insert title here</title>
</head>
<body class="sb-nav-fixed">
            <!-- 공통부분 -->
            <jsp:include page="../inc/adminTop.jsp"/>
            <!-- 공통부분 -->
            
              <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">상품관리</h1>
                        <ol class="breadcrumb mb-4">
                        </ol>
<!--                         <div class="card mb-4"> -->
<!--                             <div class="card-body"> -->
<!--                                 DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the -->
<!--                                 <a target="_blank" href="https://datatables.net/">official DataTables documentation</a> -->
<!--                                 . -->
<!--                             </div> -->
<!--                         </div> -->
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-cubes"></i>
                                상품 리스트
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
                                    <tfoot>
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
                                    </tfoot>
                                    <tbody>
                                      <%for(int i=0; i<list.size(); i++) {%>  
                                        <tr>
                                            <td class="name"><%=list.get(i).getProduct_name() %></td>
                                            <td class="code"><%=list.get(i).getProduct_code() %></td>
                                            <td class="category1"><%=list.get(i).getProduct_category1() %></td>
                                            <td class="category2"><%=list.get(i).getProduct_category2() %></td>
                                            <td class="date"><%=list.get(i).getProduct_date() %></td>
                                            <td class="stock"><%=list.get(i).getProduct_stock() %></td>
                                            <td class="price"><%=formatter.format(list.get(i).getProduct_price()) %></td>
                                            <td>
                                               <button onclick="goUpdatePopup(this.value)" value="<%=i%>">수정</button> 
                                               <button onclick="location.href='../admin/AdminProductDeletePro.ad?product_idx=<%=list.get(i).getProduct_idx()%>'">삭제</button>
                                            </td>
                                        </tr> 
                                        <%} %>
                                        <tr><td  colspan="8" align="right">
                                        <button class="btn btn-primary pull-right"
                                         onclick="goAddPopup()">상품등록</button></td></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2021</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        <script src="../js/jquery-3.6.0.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
