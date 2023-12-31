<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<!-- 내가 설정한 CSS -->
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>

<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />		
		
		<section class="contents d-flex justify-content-center">
			<div class="input-box my-5">
				<h1 class="text-center">회원 가입</h1>
				<input type="text" placeholder="아이디" class="form-control mt-4" id="loginIdInput">
				<input type="password" placeholder="비밀번호" class="form-control mt-2" id="passwordInput">
				<input type="password" placeholder="비밀번호 확인" class="form-control mt-2" id="passwordConfirmInput">
				<input type="text" placeholder="이름" class="form-control mt-2" id="nameInput">
				<input type="text" placeholder="이메일" class="form-control mt-2" id="emailInput">
				
				<button type="button" class="btn btn-secondary btn-block mt-3" id="joinBtn">가입</button>
			</div>
		</section>
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />	
	</div>

<!-- JavaScript -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script>
	$(document).ready(function(){
		
		$("#joinBtn").on("click", function(){
			let loginId	= $("#loginIdInput").val();
			let password = $("#passwordInput").val();
			let passwordConfirm = $("#passwordConfirmInput").val();
			let name = $("#nameInput").val();
			let email = $("#emailInput").val();
			
			if (loginId == "") {
				alert("아이디를 입력하세요");
				return;
			}
			
			if (password == "") {
				alert("비밀번호를 입력하세요");
				return;
			}
			
			if (password != passwordConfirm) {
				alert("비밀번호가 일치하지 않습니다");
				return;
			}
			
			if (name == "") {
				alert("이름을 입력하세요");
				return;
			}
			
			if (email == "") {
				alert("이메일을 입력하세요");
				return;
			}
			
			$.ajax({
				type:"post"
				, url:"/user/join"
				, data:{"loginId":loginId, "password":password, "name":name, "email":email}
				, success:function(data){
					
					if (data.result == "success") {
						location.href = "/user/login-view";
					} else {
						alert("회원가입 실패!!");
					}
				}
				
				, error:function(){
					alert("회원가입 에러!!");
				}
				
			});
		});
	});
</script>
</body>
</html>