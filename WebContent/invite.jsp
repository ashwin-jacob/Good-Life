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

	<form class="wrap" action="<c:url value='/secured/su/addUser' />" method='POST' >
  		
  		<div class="logo">
  			<img src="${pageContext.request.contextPath}/img/goodlifelogo.png">
  		</div>
		<c:if test="${not empty error}">
			<div class="errorblock">
			User invitation not successful.<br />Cause:
				${exceptionMessage}
			</div>
		</c:if>
		<div class="avatar">
      		<img src="${pageContext.request.contextPath}/img/GLO_logo_blue.png">
		</div>
		
		<input type="lookup" name="username" placeholder="username" required>
		<button type="submit">Submit</button>
    	
	</form>

</body>
</html>