<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CafeDal</title>
<link type="text/css" rel="stylesheet"
	href="${path}/resource/css/login.css">
</head>

<body>
	<video autoplay loop
		poster="${path}/resource/images/login-bg-coffee.jpg">
		<source src="${path}/resource/images/login-bg-coffee.mp4"
			type="video/mp4">
	</video>
	<br /><br /><br /><br />
	<div class="wrapper">
		<div class="main">
			<div class="logo">
				<img src="${path}/resource/images/logo.png" alt="로고" />
			</div>

			<form action="${path}/login" method="post">
				<div class="login_box">
				
					<div class="input_login">
						<input type="id"  name="username" placeholder="아이디를 입력하세요">
					</div>
					
					<div class="input_login">
						<input type="password"  name="password" placeholder="비밀번호를 입력하세요">
					</div>
					
					<div class="login">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input type="submit" value="로그인" />
					</div>
					
					<div class="last">
						<div class="join">
							<a class="join" href="">회원가입</a>
						</div>
						<div class="id_pwd_search">
							<a class="id_pwd_search" href="">아이디/비밀번호 찾기</a>
						</div>
					</div>
					
				</div>
			</form>
		</div>
	</div>
</body>
</html>