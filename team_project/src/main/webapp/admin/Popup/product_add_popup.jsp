<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 부트스트랩 사용하기 위해 -->

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>상품등록 페이지</title>
<script type="text/javascript">

$(document).ready(function() {
	
	$('form').submit(function() {
		$.ajax({
			type: 'post',
			url: 'AdminProductWritePro.ad',
			data: $('#form').serialize(),
			enctype: 'multipart/form-data',
			contentType: false,
			processData: false,
			success: function(response) {
				alert('상품등록이 완료되었습니다!');
				opener.location.reload();
				window.close();
			}
		});
		
	});
});
</script>
</head>

<body>
	<form method="post" id="form" action="./AdminProductWritePro.ad" enctype="multipart/form-data">
		<div class="container">
			<h2>상품등록</h2>
			<table class="table table-hover">
					<tr>
						<td><input type="text" class="form-control"
							placeholder="제품명" name="product_name" maxlength="40"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control"
							placeholder="제품코드" name="product_code" maxlength="40"></td>
					</tr>
					
					<tr>
						<td>
						<input type="text" class="form-control"
							placeholder="카테고리" readonly="readonly" style="background: white;">
						<br>
						<select name = "product_category1" style="width: 150px; ">
								<option value="과일">과일</option>
								<option value="채소">채소</option>
								<option value="쌀,잡곡">쌀,잡곡</option>
								<option value="버섯">버섯</option>
								</select>
							<select name = "product_category2" style="width: 150px;">
								<option value="">----------</option>
								<option value="봄/여름과일">봄/여름과일</option>
								<option value="가을/겨울과일">가을/겨울과일</option>
								<option value="수입과일">수입과일</option>
								<option value="잎채소">잎채소</option>
								<option value="뿌리채소">뿌리채소</option>
								<option value="열매채소">열매채소</option>
							</select>
						</td>
							
					</tr>
					<tr>
						<td><input type="text" class="form-control"
							placeholder="재고량" name="product_stock" maxlength="40"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control"
							placeholder="가격" name="product_price" maxlength="40"></td>
					</tr>
					<tr>
						<td>상품이미지</td><td><input type="file" name="filename"></td>
					</tr>
					<tr>
						<td>상품설명이미지</td><td><input type="file" name="filename2"></td>
					</tr>
			</table>
			<input type="submit" class="btn btn-primary pull-right" value="상품등록">
		</div>
	</form>
</body>

</html>