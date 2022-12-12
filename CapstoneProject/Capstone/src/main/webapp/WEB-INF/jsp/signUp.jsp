<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/header.jsp" />
    
    <br><br>
    
    <!-- User sign up -->
	<div class="container">
	
		<h1>Account Sign Up</h1>
	
		<div class="card my-3" style="width: 100%;">
		  <div class="card-body">
			<h5 class="card-title">Account Creation Form</h5>
			<form action="/createuser" method="POST">
				First Name<input type="text" value="${form.firstName}"  name="firstName" id="firstNameId" required>
				<br><br>
				Last Name <input type="text" value="${form.lastName}"  name="lastName" id="lastNameId" required>
				<br><br>
				Email <input type="text" value="${form.email}"  name="email" id="emailId" required>
				<br><br>
				Password <input type="text" value="${form.password}"  name="password" id="inputPass" required>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		  </div>
		</div>
			
	</div>

<jsp:include page="include/footer.jsp" />