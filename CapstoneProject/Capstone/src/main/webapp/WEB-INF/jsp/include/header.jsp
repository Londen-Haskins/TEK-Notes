<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Social Network</title>
    
    
    <c:url value="/social.css" var="cssURL" />
	<link rel="stylesheet" href="${cssURL}" />
</head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>


<!-- include bootstrap here -->
<!-- include global CSS files here -->


<body>

<!-- site navigation bar on top of page -->
	<div class="header-area">
		<div class="title-card" style="display: flex">
			<h1 style="font-size: 600%"><strong>Social Network</strong></h1>
		</div>
		<div class="navbar">
			<ul>	
				<li class="navbar-block"><a href="/">Home</a></li>
				<li class="navbar-block"><a href="/">Profile</a></li>
				<li class="navbar-block"><a href="/listing">User Listing</a></li>
				
				<sec:authorize access="!isAuthenticated()">
					<li class="navbar-block"><a href="/signup">Sign Up</a></li>
					<li class="navbar-block"><a href="/user/login">Log In</a></li>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
					<li class="navbar-block"><a href="/user/logout">Logout</a></li>
				</sec:authorize>
				
					
			</ul>
			<form class="form-inline" action="/search" method="GET">
				Search for User:
				<input type="text" placeholder="Username" aria-label="Search" name="name">
				<button type="submit">Search</button>
			</form>
		</div>
	</div>