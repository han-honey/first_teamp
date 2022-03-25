<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="../css/s.css" type="text/css">
 <script src="../js/jquery-3.6.0.js"></script>
</head>

<body>
	<h1>리뷰 등록</h1>
	<form action="./ReviewWritePro.bo" method="post" enctype="multipart/form-data">
	<input type="hidden" name="product_code" value="<%=request.getParameter("product_code") %>"/>
	<input type="hidden" name="member_id" value="<%=session.getAttribute("sessionId") %>"/>
<!-- 	&#9733; -->
	<script type="text/javascript">
  	$(document).ready(function() {  
	
  	$("input[name='star_scope']:checked").val(); 
  	}); 
  	</script>
		 <div class="star-rating">
		  <input type="radio" id="5-stars" name="star_scope" value="5" />
		  <label for="5-stars" class="star">&#9733;</label>
		  <input type="radio" id="4-stars" name="star_scope" value="4" />
		  <label for="4-stars" class="star">&#9733;</label>
		  <input type="radio" id="3-stars" name="star_scope" value="3" />
		  <label for="3-stars" class="star">&#9733;</label>
		  <input type="radio" id="2-stars" name="star_scope" value="2" />
		  <label for="2-stars" class="star">&#9733;</label>
		  <input type="radio" id="1-star" name="star_scope" value="1" />
		  <label for="1-star" class="star">&#9733;</label>
		</div>
		
				<table>
<!-- 					<tr> -->
<!-- 						<td>작성자</td> -->
<!-- 						<td><input type="text" name="member_id" required="required"></td> -->
<!-- 					</tr> -->
						
					<tr>
						<td>제목</td>
						<td><input type="text" name="review_subject" required="required"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea rows="10" cols="20" name="review_content" required="required"></textarea></td>
					</tr>
					<tr>
						<td>이미지</td>
						<td><input type="file" name="review_img" ></td>
					</tr>
				</table>
	
				<div>
					<input type="submit" value="리뷰 쓰기" class="btn">
				</div>
			</form>

</body>
</html>