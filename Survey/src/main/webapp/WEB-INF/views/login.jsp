<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome</title>
<style><%@include file="./css/login.css"%></style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
	$("#login-button").click(function(event) {
		
		event.preventDefault();

		$('form').fadeOut(500);
		$('.wrapper').addClass('form-success');
	});
	
	function validateForm() {
		if ($("input[name=name]").val() === "" || $("input[name=email]").val() === "") {
			alert("Both name and email are required.");
			return false;
		}
	}
</script>
<title>Survey Home</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<h1>Welcome</h1>

			<form class="form" method="GET" onsubmit="return validateForm()" action="./login">
				<input name="name" type="text" placeholder="Name">
				<input name="email" type="text" placeholder="Email">
				<button type="submit" id="login-button">Login</button>
			</form>
		</div>

		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
</body>
</html>
