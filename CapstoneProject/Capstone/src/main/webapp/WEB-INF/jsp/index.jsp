<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/header.jsp" />


	<h1>Home Page</h1>
    
    <br><br>

    <table border="1" cellpadding="5">
        <tr>
            <td><B>User ID</B></td>
            <td><b>Name</b></td>
            <td><b>Email</b></td>
        </tr>
        <u:forEach items="${users}" var="user">
            <tr>      
                <td>${user.id}</td>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.email}</td>
            </tr>
        </u:forEach>
    </table>
    
    <!-- User sign up -->
	<div class="container">
		<div class="card my-3" style="width: 100%;">
		  <div class="card-body">
			<h5 class="card-title">Account Creation Form</h5>
			<form action="/user/createuser" method="POST">
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