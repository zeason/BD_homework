<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome</title>
<style><%@include file="./css/login.css"%></style>
<style><%@include file="./css/survey.css"%></style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>${survey.title}</title>
</head>
<body>
	<div class="wrapper">
		<div style="min-width: 40%; min-height: 50%;">
			<h1>${survey.title}</h1>

			<form class="form" method="POST" action="/survey/answer/save/${survey.id}">
				<p style="margin-botton: 4%; font-weight:bold">Name, Email and all questions are required.</p>
				<input name="name" type="text" placeholder="Name"> 
				<input name="email" type="text"placeholder="Email">
				<table class="styled-table">
					<c:forEach items="${survey.questions}" var="q">
					    <tr>
					        <td>${q.title}</td>
					    </tr>
					    <tr>
					    	<td>
				    		<c:forEach items="${q.options}" var="o">
				    			<c:choose>
				    				<c:when test="${o.checked == 'checked'}">
					    				<input type="radio" id="${o.id}" name="${q.id}" value="${o.id}" checked>
					    			</c:when>
					    			<c:otherwise>
					    				<input type="radio" id="${o.id}" name="${q.id}" value="${o.id}" class="option-input radio">
					    			</c:otherwise>
				    			</c:choose>
								<label for="${o.id}">${o.content}</label><br>
				    		</c:forEach>
					    	</td>
					    </tr>
					</c:forEach>
				</table>
					
					
				
				<button type="submit" id="login-button">Submit</button>
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
