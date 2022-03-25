<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/nice-select.css" type="text/css">
<link rel="stylesheet" href="../css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script type="text/javascript">
var isCheckPass;
var passStrCheck;
function pwCheck(inputPass) {
 	var colorRG = document.getElementById("retypePassCheckResult");
 	if(document.fr.pass_check.value != document.fr.pass.value) {
 		colorRG.style.color = "red";
 		document.getElementById("retypePassCheckResult").innerHTML = "비밀번호 불일치";
 		idCheckPass = false;
 	} else {
 		colorRG.style.color = "green";
 		document.getElementById("retypePassCheckResult").innerHTML = "비밀번호 일치";
 		isCheckPass = true;
 	}
 }
function strCheck(str, type) {    
    /*var REGEX = {        
        email: /\S+@\S+\.\S+/,        //이메일 체크 정규식
        password: /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/,     //8~16자 영문, 숫자 조합   
    };

    if (type == "email") {        
        document.getElementById("result").innerHTML = REGEX.email.test(str);
        emailStrCheck =  REGEX.email.test(str);    //유효성 검사 결과 통과면 true반환
    }  else if(type == "pwd") {
    	passStrCheck = REGEX.password.test(str); 
    }
    else {        
        return false;    
    }*/
}

function checkForm() {
	/* if(!isCheckId){ //아이디 중복확인을 수행하지 않았을 경우
		alert("아이디를 입력해주세요.");
		return false;  
	} else if(!isCheckPass) { //비밀번호 두 값이 일치하지 않을 경우
		alert("비밀번호를 다시 입력해주세요.");
		document.fr.pass_check.focus();
		return false;
	}else if(!emailStrCheck){
		alert("유효한 이메일이 아닙니다.");
		document.fr.email.focus();
		return false;
	} else if(!passStrCheck){
		alert("비밀번호는 영문/숫자/특수문자(!@#$%^&*)를 포함하여 8~16자로 입력해야합니다.");
		document.fr.pass.focus();
		return false;
	} else {
		return true;
	}*/
	return true; //테스트하고나서 주석풀기
}
</script>
</head>

<body>
	<jsp:include page="../inc/top.jsp" />
	<div align="center">
	<h4>비밀번호 변경</h4>
		  	<form action="./ChangePasswordPro.me" method="post" onsubmit="return checkForm()">
		  		<table>
		  			<tr>
		  			<th>현재 비밀번호</th>
		  			<td>
		  			<input type="password" name="old_pass" required="required" >
		  			</td>
		  			</tr>
		  			<tr>
		  			<th>새 비밀번호</th>
		  			<td>
		  			<input type="password" name="new_pass" required="required" >
		  			</td>
		  			</tr>
		  			<tr>
		  			<th>새 비밀번호 확인</th>
		  			<td>
		  			<input type="password" name="new_pass_check" required="required" >
		  			<span id="retypePassCheckResult"><!--패스워드 일치 여부 결과 표시하는 영역--></span>
		  			</td>
		  			</tr>
		  		</table>
			<p style="display: inline-block; text-align: left;">	1. 8자~16자, 영문, 숫자, 특수문자 사용<br>
													2. 최소 1자리 이상 영문, 숫자, 특수문자 포함<br>
													3. 이전 비밀번호와 동일한 비밀번호 사용 불가능</p>
		  		<div class="clear"></div>
		  		<div id="buttons">
		  			<input type="submit" value="확인" class="btn" >
		  			<span>|</span>
		  			<input type="button" value="취소" class="btn"  onclick="history.back()">
		  		</div>
		  	</form>
		</div>
	<jsp:include page="../inc/bottom.jsp" />
		
	   <!-- Js Plugins -->
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


