<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String product_code = request.getParameter("product_code");
%>
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

<title>재고량 수정</title>
<script type="text/javascript">
$(function() {
	$('#update').click(function() {
		$.ajax({
			type: 'post',
			url: 'AdminProductModifyPro.ad',
			data: $('#form').serialize(),
			enctype: 'text',
			success: function(response) {
				alert('재고수정이 완료되었습니다!');
				opener.location.reload();
				window.close();
			}
		});
	});
});
</script>
</head>

<body>
	<form method="post" id="form" enctype="multipart/form-data">
		<div class="container">
			<h2>재고량수정</h2>
			<table class="table table-hover">
					<tr>
						<td>
							<input type="text" class="form-control"	placeholder="재고량" name="product_stock" maxlength="40">
							<input type="hidden" name="product_code" value="<%=product_code%>">
						</td>
					</tr>
			</table>
			<input type="button" id="update" class="btn btn-primary pull-right" value="재고수정">
		</div>
	</form>
</body>

</html>