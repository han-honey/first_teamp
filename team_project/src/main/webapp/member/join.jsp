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

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script type="text/javascript">
	var isCheckId;
	var isCheckPass;
	var emailStrCheck;
	var passStrCheck;
	var emailCertCheck;
	
	var checkCount = 0;
	for(var i = 0; i<document.fr.marketing_agree.length; i++){
		if(document.fr.marketing_agree[i].checked == true){
			checkCount++;
		}
	}
	
	function idCheck() {
		window.open("./IdCheckForm.me", "아이디 중복확인 창",
				"width=500px, height=300px");
		isCheckId = true;
	}

	function sendMail() {
		var email = document.fr.email.value;
		window.open("./SendEmail.me?email=" + email, "메일 인증",
				"width=500px, height=300px");
		emailCertCheck = true;
	}

	function pwCheck(inputPass) {
		var colorRG = document.getElementById("retypePassCheckResult");
		if (document.fr.pass_check.value != document.fr.pass.value) {
			colorRG.style.color = "red";
			document.getElementById("retypePassCheckResult").innerHTML = "비밀번호 불일치";
			idCheckPass = true;
		} else {
			colorRG.style.color = "green";
			document.getElementById("retypePassCheckResult").innerHTML = "비밀번호 일치";
			isCheckPass = true;
// 		document.fr.sub.disabled = false;
		}
		
	}

	function phoneTotal(bodyValue) {
		document.fr.phone.value = document.fr.phone_head.value + bodyValue;
	}

	function strCheck(str, type) {
		var REGEX = {        
		    email: /\S+@\S+\.\S+/,        //이메일 체크 정규식
		    password: /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/,     //8~16자 영문, 숫자 조합   
		};

		if (type == "email") {        
		    emailStrCheck =  REGEX.email.test(str);    //유효성 검사 결과 통과면 true반환
		}  else if(type == "pwd") {
			passStrCheck = REGEX.password.test(str); 
		}
		else {        
		    return false;    
		}
	}

	function checkForm() {
		
		if(!isCheckId){ //아이디 중복확인을 수행하지 않았을 경우
			alert("아이디를 입력해주세요.");
			return false;  
		} else if(!isCheckPass) { //비밀번호 두 값이 일치하지 않을 경우
			alert("비밀번호를 다시 입력해주세요.");
			document.fr.pass_check.focus();
			return false;
		}
		else if(!emailStrCheck){
			alert("유효한 이메일이 아닙니다.");
			document.fr.email.focus();
			return false;
		} else if(!passStrCheck){
			alert("비밀번호는 영문/숫자/특수문자(!@#$%^&*)를 포함하여 8~16자로 입력해야합니다.");
			document.fr.pass.focus();
			return false;
		} else if(!emailCertCheck) {
			alert("이메일 인증을 해주세요.");
			return false;
		} else {
			return true;
		}
	}
	
</script>

<style>
.input-form {
	max-width: 680px;
	margin-top: 20px;
	margin-bottom: 40px;
	padding: 32px;
	background: #ffffff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
</style>
</head>

<body>
	<jsp:include page="../inc/top.jsp" />

	<!-- 본문 내용 -->

		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">회원가입</h4>
				<form action="./MemberJoinPro.me" method="post" id="join" name="fr"
					onsubmit="return checkForm()">
					<div class="row">

						<div class="col-md-6 mb-3">
							<label>아이디 <span style="color:red;">*</span></label> <input
								style="background: white;" type="text" class="form-control"
								name="id" placeholder="" readonly="readonly" required="required">
						</div>

						<div class="col-md-6 mb-3">
							 <br> <input type="button" value="아이디 입력"
								id="id_check" onclick="idCheck()">
						</div>
						<div class="col-md-6 mb-3">
							<label>비밀번호 <span style="color:red;">*</span></label> <input type="password" class="form-control"
								name="pass" required="required"
								onkeyup="strCheck(this.value, 'pwd')"> <span
								id="retypePassCheckResult"> <!--패스워드 일치 여부 결과 표시하는 영역-->
							</span>
						</div>
						<div class="col-md-6 mb-3">
							<label>비밀번호확인 <span style="color:red;">*</span></label> <input type="password" class="form-control"
								name="pass_check" onkeyup="pwCheck(this.value)">
						</div>

						<div class="col-md-6 mb-3">
							<label>이름 <span style="color:red;">*</span></label> <input type="text" class="form-control"
								name="name" required="required">
						</div>

						<div class="col-md-6 mb-3">
							<label>휴대전화 <span style="color:red;">*</span></label><br> <select name="phone_head">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="018">018</option>
							</select> <input type="text" name="phone_body" required="required"
								onkeydown="phoneTotal(this.value)" class="form-control"
								style="width: 220px;"> <input type="hidden" name="phone">
						</div>
						<div class="col-md-6 mb-3">
							<label>이메일 <span style="color:red;">*</span></label> <input type="text" class="form-control"
								name="email" required="required"
								onkeyup="strCheck(this.value, 'email')">
						</div>
						<div class="col-md-6 mb-3">
							<label> </label> <br> <input type="button" value="인증번호전송"
							name="sendMailBtn" 	onclick="sendMail()"> 
						</div>
					</div>

					<label>생년월일</label> 
					<input type="date" name="birth">


					<hr class="mb-4">

					마케팅 수신 &nbsp;&nbsp; <input type="radio" name="marketing_agree"
						onchange="marketingCheck()" value="동의" checked="checked">동의 &nbsp;&nbsp; <input
						type="radio" name="marketing_agree" onchange="marketingCheck()"
						value="비동의">동의 안함
					<div class="mb-4"></div>
					<button class="btn btn-primary btn-lg btn-block" type="submit" >가입
						완료</button>
				</form>
			</div>
		</div>

<jsp:include page="../inc/bottom.jsp" />
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



