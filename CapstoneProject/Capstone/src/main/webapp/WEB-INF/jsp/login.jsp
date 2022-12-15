<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/header.jsp" />

<div class="container">

	<div class="card my-3" style="width: 100%;">
		<div class="card-body">
			<form action="/user/loginuser" method="POST">
		            <div class="row justify-content-center">
			            <div class="mt-2 mb-3 col-4">
			                <strong><label for="username" class="form-label">Username</label></strong>
			                <input type="username" name="username" class="form-control" id="username"
			                    aria-describedby="usernameHelp">
			                <div id="usernameHelp" class="form-text">Enter username</div>
			            </div>
		            </div>
		            <div class="row justify-content-center">
			            <div class="mt-2 mb-3 col-4">
			                <strong><label for="password" class="form-label">Password</label></strong>
			                <input type="password" name="password" class="form-control" id="password"
			                    aria-describedby="passwordHelp">
			                <div id="passwordHelp" class="form-text">Enter password</div>
			            </div>
			        </div>
		
		            <div class="row justify-content-center">
			            <div class="mt-2 mb-3 col-4">
			            	<button type="submit" class="btn btn-primary">Login</button>
			             </div>
			        </div>
		        </form>
			</div>
	  	</div>

</div>

<jsp:include page="include/footer.jsp" />

