<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Survey Home</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style><%@include file="./css/login.css"%></style>
<style><%@include file="./css/table.css"%></style>
<style><%@include file="./css/link.css"%></style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<title>Survey Home</title>
</head>
<body class="wrapper">
	<div style="max-width:70%; text-align: center;">
	<div id="tabs" style="min-width: 37%; min-height: 37%;">
		<ul>
			<li><a href="#tabs-1">My Survey</a></li>
			<li><a href="#tabs-2">My Answered Survey</a></li>
		</ul>
		<div id="tabs-1">
			<div class="tbl-header">
			    <table cellpadding="0" cellspacing="1" border="0">
			      <thead>
			        <tr>
			          <th>Id</th>
			          <th>Title</th>
			          <th>Edit Survey</th>
			          <th>Delete Survey</th>
			          <th>Deliver Survey</th>
			        </tr>
			      </thead>
			    </table>
			</div>
			<table cellpadding="0" cellspacing="1">
				<c:forEach items="${mycreation}" var="mc">
				    <tr>
				        <td style="color : #333;">${mc.id}</td>
				        <td><a class="r-link link text-underlined" href="./survey/${mc.id}">${mc.title}</a></td>
				        <td><a class="r-link link text-underlined" href="./survey/editSurvey/${mc.id}">Edit</a></td>
				        <td><a class="r-link link text-underlined" href="./survey/deleteSurvey/${mc.id}">Delete</a></td>
				        <td><a class="r-link link text-underlined" href="./survey/sendEmail/${mc.id}">Email</a></td>
				    </tr>
				</c:forEach>
			</table>
		</div>
		<div id="tabs-2">
			<div class="tbl-header">
			    <table cellpadding="0" cellspacing="1" border="0">
			      <thead>
			        <tr>
			          <th>Id</th>
			          <th>Title</th>
			        </tr>
			      </thead>
			  	</table>
			</div>
			<table>
				<c:forEach items="${answered}" var="as">
				    <tr>
				        <td>${as.id}</td>
				        <td><a class="r-link link text-underlined" href="answer/${as.id}">${as.title}</a></td>
				    </tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div style="text-align: center">
		<a class="r-link link text-underlined" href="./survey/newSurvey" class="btn btn-primary">New Survey</a>
	</div>
	</div>
</body>
</html>
