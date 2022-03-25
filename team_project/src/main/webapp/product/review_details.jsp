<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ReviewDTO dto = (ReviewDTO)request.getAttribute("dto");
String sessionId =(String)session.getAttribute("sessionId");
SimpleDateFormat sd = new SimpleDateFormat("yyyy년MM월dd일");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(sessionId == null){ %>
<h6>리뷰 상세 페이지</h6>
<h3>글번호 :  <%=dto.getReview_idx() %></h3>
<h3>제목 : <%=dto.getReview_subject() %></h3>
<h3>아이디 : <%=dto.getMember_id()%></h3>
<h3>내용 : <%=dto.getReview_content() %></h3>
<h3>이미지 :<img src="<%=request.getContextPath() %>/upload/<%=dto.getReview_img() %>" width ="300"></h3>
<h3>평점 :<% if(dto.getStar_scope()==1){%>
			<span>★</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
   		<%}else if(dto.getStar_scope()==2){%>
	  		<span>★</span><span>★</span><span>☆</span><span>☆</span><span>☆</span>
         <%}else if(dto.getStar_scope()==3){%>
			<span>★</span><span>★</span><span>★</span><span>☆</span><span>☆</span>
	     <%}else if (dto.getStar_scope()==4){%>
			<span>★</span><span>★</span><span>★</span><span>★</span><span>☆</span>
	     <%}else if (dto.getStar_scope()==5){%>
			<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>								                               	  
		 <%} %></h3>
<h3>작성일 : <%=sd.format(dto.getReview_date())%></h3>
	
<% } else if(sessionId.equals(dto.getMember_id())||sessionId.equals("admin")){ %>
<h6>리뷰 상세 페이지</h6>
<h3>글번호 : <%=dto.getReview_idx() %></h3>
<h3>제목 : <%=dto.getReview_subject() %></h3>
<h3>아이디 : <%=dto.getMember_id()%></h3>
<h3>내용 : <%=dto.getReview_content() %></h3>
<h3>이미지 : <img src="<%=request.getContextPath() %>/upload/<%=dto.getReview_img()%>" width ="300"></h3>
<h3>평점 :<% if(dto.getStar_scope()==1){%>
			<span>★</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
   		<%}else if(dto.getStar_scope()==2){%>
	  		<span>★</span><span>★</span><span>☆</span><span>☆</span><span>☆</span>
         <%}else if(dto.getStar_scope()==3){%>
			<span>★</span><span>★</span><span>★</span><span>☆</span><span>☆</span>
	     <%}else if (dto.getStar_scope()==4){%>
			<span>★</span><span>★</span><span>★</span><span>★</span><span>☆</span>
	     <%}else if (dto.getStar_scope()==5){%>
			<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>								                               	  
		 <%} %></h3>
<h3>작성일 : <%=sd.format(dto.getReview_date())%></h3>
<input type="button" value="삭제" onclick="location.href='./ReviewDeletePro.bo?review_idx=<%=dto.getReview_idx()%>'">
<input type="button" value="수정" onclick="location.href='./ReviewModifyForm.bo?review_idx=<%=dto.getReview_idx()%>'">
<%} %>
	 
</body>
</html>