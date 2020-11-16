<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Error</title>
<style><%@include file="./css/login.css"%></style>
<title>Error</title>
</head>
<body>
	<div class="wrapper">
		<div style="min-width: 40%; min-height: 50%; font-size: 200%">
			<h1>Oops... Something went wrong</h1>
			<h2>${msg}</h2>
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