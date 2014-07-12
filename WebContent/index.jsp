<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook Login Page</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<!-- row 1 -->
		<header class="row">
		<div class="col-md-12">
			<p class="little-head">Welcome to the</p>
			<h1>Book Club App</h1>
		</div>
		<!-- internal row -->
		<div class="row">
			<ul class="nav nav-tabs" role="tablist">
				<li class="active"><a href="index.jsp"></a>Login</li>
				<li><a href="home.jsp"></a>Home Feed</li>
				<li><a href="group.jsp"></a>Group View</li>
			</ul>
		</div>
		</header>
		<!-- row 2 -->
		<div class="row">
			<div class="col-md-6">
				<h2>Login</h2>
				<form role="form" action="Login" method="POST">
					<div class="form-group">
						<label for="email">Email:</label> 
						<input class="form-control"
							type="email" name="email" />
					</div>
					<div class="form-group">
					<label for="password">Password:</label> 
					<input class="form-control" type="password"
						name="password" />
						</div>
					<button type="submit">Login</button>
				</form>
			</div>
			<div class="col-md-6">
				<a href="SignIn">Or click here to login with Facebook</a> After
				logging in, <a href="ShowFriends">Click here to see your friends</a>
			</div>
		</div>
	</div>
	<!-- end container -->
</body>
</html>