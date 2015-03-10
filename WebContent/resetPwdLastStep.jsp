<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
	function checkPass() {
		//Store the password field objects into variables ...
		var pass1 = document.getElementById('pass1');
		var pass2 = document.getElementById('pass2');
		//Store the Confimation Message Object ...
		var message = document.getElementById('confirmMessage');
		//Set the colors we will be using ...
		var goodColor = "#66cc66";
		var badColor = "#ff6666";
		//Compare the values in the password field 
		//and the confirmation field
		if (pass1.value == pass2.value) {
			//The passwords match. 
			//Set the color to the good color and inform
			//the user that they have entered the correct password 
			pass2.style.backgroundColor = goodColor;
			message.style.color = goodColor;
			message.innerHTML = "Passwords Match!"
		} else {
			//The passwords do not match.
			//Set the color to the bad color and
			//notify the user.
			pass2.style.backgroundColor = badColor;
			message.style.color = badColor;
			message.innerHTML = "Passwords Do Not Match!"
		}
	}
</script>
<head>

  <meta charset="UTF-8">

	<title>The Good Life</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body onload='document.f.j_username.focus();'>
	
	<form class="wrap" action="<c:url value='resetPasswdComplete' />" method='POST'>
  		<div class="logo">
  			<img src="${pageContext.request.contextPath}/img/goodlifelogo.png">
  		</div>
  		<c:if test="${not empty error}">
			<div class="errorblock">
				Your signup attempt was not successful, try again.<br /> Cause:
				${exceptionMessage}
			</div>
		</c:if>
		<div class="avatar">
      		<img src="${pageContext.request.contextPath}/img/GLO_logo_grey.jpg">
		</div>
			<input type="text" name="pass1" id="pass1" placeholder="Password" required >
        <div class="bar">
			<i></i>
		</div>
        <input type="password" name="pass2" id="pass2" placeholder="Confirm Password" required onkeyup="checkPass(); return false;"> 
					    <span id="confirmMessage" class="confirmMessage"></span>
		<button type="submit">Submit</button>
	</form>
				
</body>
</html>