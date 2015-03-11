<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="UTF-8">

	<title>The Good Life</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/GLO_logo_grey.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

	<form class="wrap" action="<c:url value='j_spring_security_check' />" method='POST' >
  		
  		<div class="logo">
  			<img src="${pageContext.request.contextPath}/img/goodlifelogo.png">
  		</div>
		
		<c:if test="${not empty error}">
			<div class="errorblock">
				Your login attempt was not successful, try again.<br /> Cause:
-				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
		
		<div class="avatar">
      		<img src="${pageContext.request.contextPath}/img/GLO_logo_grey.jpg">
		</div>
		
		<input type="text" name="j_username" placeholder="Username" required>
		
		<div class="bar">
			<i></i>
		</div>
		
		<input type="password" name="j_password" placeholder="Password" required>
		
		<a href="resetPwdStepOne" class="forgot_link">Forgot?</a>
		<button type="submit">Sign in</button>
    	<button onclick="location.href='signup.jsp'">Register</button>
    	
	</form>
	
</body>

</html>