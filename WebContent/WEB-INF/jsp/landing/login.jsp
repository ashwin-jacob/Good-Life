<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>The Good Life</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!--Font Awesome Stylesheet-->
    <link href="css/font-awesome.min.css"" rel="stylesheet">
    <!-- Custom styles for this page -->
    <link href="css/style.css" rel="stylesheet">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
<body style="background-color: #000000; background-repeat: no-repeat; background-attachment: fixed; background-position: center center; vertical-align: middle; -webkit-background-size: cover; -moz-background-size: cover; background-size: 100% 100%; max-width: none;">
	<div class="container" id="login">
		<div class="row">
		  <div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Welcome To The Good Life!</h1>
				<div class="account-wall">
					<img class="profile-img" src="${pageContext.request.contextPath}/img/GLO_logo_grey.jpg"
						alt="">
					<form name='f' action="<c:url value='j_spring_security_check' />" method='POST' class="form-signin">
					<input type="text" name="j_username" class="form-control" placeholder="Email" required autofocus>
					<input type="password" name="j_password" class="form-control" placeholder="Password" required>
					<button class="btn btn-lg btn-primary btn-block" type="submit">
						Sign in</button>
					<label class="checkbox pull-left">
						<input type="checkbox" value="remember-me">
						Remember me
					</label>
					<a href="resetPwdStepOne" class="pull-right need-help">Forgot your Password? </a>
                    <a href="userSignUp" class="pull-right need-help">Create an account </a><span class="clearfix"></span>
				  </form>
			  </div>
				
			</div>
		</div>
	</div>


    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
	
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



