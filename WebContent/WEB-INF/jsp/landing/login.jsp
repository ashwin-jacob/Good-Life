<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>The Good Life</title>

    <!-- Bootstrap -->
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!--Font Awesome Stylesheet-->
    <link type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom styles for this page -->
    <link type="text/css" href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

	

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
<body onload='document.f.j_username.focus();' style="background: #0c0c0c;">
	<div class="container" id="login">
		<c:if test="${not empty error}">
			<div class="errorblock">
				Your login attempt was not successful, try again.<br /> Cause:
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Welcome To The Good Life!</h1>
				<div class="account-wall">
					<img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
						alt="">
					<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
						<input type="text" name="j_username" class="form-control" placeholder="User Name" required autofocus>
						<input type="password" name="j_password" class="form-control" placeholder="Password" required>
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Sign in</button>
						<label class="checkbox pull-left">
							<input type="checkbox" value="remember-me">
							Remember me
						</label>
						<a href="resetPwdStepOne" class="pull-right need-help">Forgot your Password? </a><span class="clearfix"></span>
					</form>
				</div>
				<a href="userSignUp" class="text-center new-account">Create an account </a>
				<a href="requestInvitationCode" class="text-center new-account">Request a misplaced invite code</a>
			</div>
		</div>
	</div>


    <!-- jQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	
	<!-- AJAX -->
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"> </script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
	
	<!-- Load Content Script -->
	function loadContent(elementSelector, sourceURL)
	{
		$(elementSelector).load(sourceURL);
	}
	
    </script>

</body>
</html>



