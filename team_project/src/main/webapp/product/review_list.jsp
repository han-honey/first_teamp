<%@page import="vo.ProductBoardDTO"%>
<%@page import="vo.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ProductBoardDTO dto = (ProductBoardDTO)request.getAttribute("dto");
int reviewCount = (int)request.getAttribute("reviewCount");
double starScope = (double)request.getAttribute("starScope");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
ArrayList<ReviewDTO> list = (ArrayList<ReviewDTO>)request.getAttribute("articleList");
String member_id = (String)session.getAttribute("sessionId");
String product_code = request.getParameter("product_code");

int pageNum = pageInfo.getPageNum();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
// function previousPage() {
	
<%-- 	var startPage = <%=startPage%> --%>
<%-- 	var pageNum = <%=pageNum%> --%>
<%-- 	var endPage = <%=endPage%> --%>
// 	var product_code = $('#prod_code').val();
// 	alert(startPage);
// 	alert(pageNum);
// 	alert(endPage);
// 	alert(product_code);
	
// 		if(pageNum != startPage){
// 			pageNum--;
// 			alert(pageNum);
// 			$.ajax({
// 		      type : 'get',
// 		      url : 'ReviewList.bo',
// 		      data : {
// 		    	  page: pageNum,
// 		    	  product_code: product_code,
// 		      },
// 		      dataType : 'text',
// 		      success : function(result){
// 		        alert('이전페이지 이동 성공!');
// 		      },
// 		      error : function(xtr,status,error){
// 	    	    alert('이전페이지 이동 실패!');
// 		      }
// 		    });
// 		}
// }
</script>
</head>
<body>
	<div class="col-lg-12">
		<div class="product__details__tab">
			<ul class="nav nav-tabs" role="tablist">
				<li class="nav-item"><a class="nav-link active"
					data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">상품설명</a>
				</li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#tabs-2" role="tab" aria-selected="false">상품정보</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#tabs-3" role="tab" aria-selected="false">리뷰 <span>
					</span></a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tabs-1" role="tabpanel">
					<div class="product__details__tab__desc">
						<p>상품설명</p>
					</div>
				</div>
				<div class="tab-pane" id="tabs-2" role="tabpanel">
					<div class="product__details__tab__desc">
						<h6>상품정보</h6>
						<p>본 상품정보(상품상세정보, 상품기본정보 등)의 내용은 생산자, 공급업체에서 제공한 정보입니다. 만약 해당
							상품정보에 오류가 있을 경우, 우체국쇼핑 우편고객센터(1588-1300)로 연락주시면 즉시 조치하도록 하겠습니다.

							포장단위별 내용물의 용량(중량), 수량, 크기 무안 양파 5kg, 10kg 생산자, 수입품의 경우 수입자를 함께 표기
							영흥농산영농조합법인 농수산물의 원산지 표시에 관한 법률에 따른 원산지 국산 제조연월일(포장일 또는 생산연도),
							유통기한 2021년 상시수확으로 자세한 수확일자는 업체로 문의주세요. / 신선식품으로 별도의 유통기한은 없으나 가급적
							빠른 섭취 권장 관련법상 표시사항 해당없음 농산물 - 농수산물품질관리법상 유전자변형농산물 표시, 지리적 표시 해당없음
							축산물 - 축산법에 따른 등급 표시, 축산물이력법에 따른 이력관리대상축산물 유무 해당없음 수산물 -
							농수산물품질관리법상 유전자변형수산물 표시, 지리적 표시 해당없음 수입식품에 해당하는 경우 “식품위생법에 따른
							수입신고를 필함”의 문구 해당없음 상품구성 무안양파 5kg(소) 보관방법 또는 취급방법 서늘하고 건조한곳에 보관하여
							주세요 소비자상담 관련 전화번호 1588-1300 품목 또는 명칭 무안양파 식품등의표시·광고에관한법률에 따른
							소비자안전을 위한 주의사항 해당없음</p>
					</div>
				</div>
				<div class="tab-pane" id="tabs-3" role="tabpanel">
					<div class="product__details__tab__desc">
						<article>
							<div class="container">
								<h6>리뷰</h6>
								<div>
									<ul>
										<li><span class="ico"></span>
										<p class="txt">상품에 대한 문의를 남기는 공간입니다. 해당 게시판의 성격과 다른 글은
												사전동의 없이 담당 게시판으로 이동될 수 있습니다.</p></li>
										<li><span class="ico"></span>
										<p class="txt">
												배송관련, 주문(취소/교환/환불)관련 문의 및 요청사항은 푸릇푸릇 내 <a href="#none"
													onclick="window.parent.location.href = '../center/QnAList.bo'">1:1
													문의</a>에 남겨주세요.
											</p></li>
									</ul>
								</div>
								<table class="table table-hover">
									<tr>
										<th>번호</th>
										<th>작성자</th>
										<th>제목</th>
										<th>별점</th>
										<th>작성일</th>
									</tr>
									<%
									for (ReviewDTO review : list) {
									%>
									<tr>
										<td><%=review.getReview_idx()%></td>
										<td><%=review.getMember_id()%></td>
										<td><a
											href="../product/ReviewContent.bo?review_idx=<%=review.getReview_idx()%>"><%=review.getReview_subject()%></a></td>
										<td>
											<%
											if (review.getStar_scope() == 1) {
											%> <span>★</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
											<%
											} else if (review.getStar_scope() == 2) {
											%> <span>★</span><span>★</span><span>☆</span><span>☆</span><span>☆</span>
											<%
											} else if (review.getStar_scope() == 3) {
											%> <span>★</span><span>★</span><span>★</span><span>☆</span><span>☆</span>
											<%
											} else if (review.getStar_scope() == 4) {
											%> <span>★</span><span>★</span><span>★</span><span>★</span><span>☆</span>
											<%
											} else if (review.getStar_scope() == 5) {
											%> <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
											<%
											}
											%>
										</td>
										<td><%=review.getReview_date()%></td>
										<%
										}
										%>
									</tr>
								</table>
								<div class="clear"></div>
								<div class="container">
									<div id="page_control"
										style="text-align: center; font-size: 16px;">
										
										<%if(pageNum == startPage) { %>
										
												<input type="button" id="next" value="&lt;">
												
										<%} else { %>
											
											<input type="button" id="next" value="&lt;" 
											onclick="location.href='./BoardProductContent.bo?page=<%=pageNum - 1%>&product_code=<%=product_code%>'">

									    <%} %>
									    
										<%for (int i = startPage; i <= endPage; i++) {%>
										
											<%if (i == pageNum) {%>
											
												<input type="button" id="current" value="<%=i%>">
												<a href="./BoardProductContent.bo?page=<%=i%>&product_code=<%=product_code%>"><%=i%></a>
													
											<%}%>
										<%}%>
										
										<%if (pageNum == endPage) {%>
										
											<input type="button" id="next" value=">">
										
										<%} else {%>
										
											<input type="button" id="next" value=">" 
											onclick="location.href='./BoardProductContent.bo?page=<%=pageNum + 1%>&product_code=<%=product_code%>'"> 
												
										<%}%>
									</div>
								</div>
							</div>
						</article>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>