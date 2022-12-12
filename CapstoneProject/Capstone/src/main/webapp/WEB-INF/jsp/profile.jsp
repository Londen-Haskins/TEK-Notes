<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/header.jsp" />

	<div class="container">
		<h1>Welcome to ${user.firstName} ${user.lastName}'s Profile Page</h1>
	    
	    <br><br>
	
	    <div class="col-md-6">
			<div class="card flex-md-row mb-4 box-shadow h-md-250" style="background-color: #0C690C; ">
				<div class="card-body d-flex flex-column align-items-start">
					<h3 class="mb-0">
						<a class="text-dark">${user.firstName} ${user.lastName}</a>
					</h3>
					<div class="mb-1 text-muted">Nov 11</div>
					  <p class="card-text mb-auto" style="color: white; text-shadow: 2px 2px #000000;">
						Example Posting from the user
					  </p>
					</div>
				</div>
			</div>
		</div>

<jsp:include page="include/footer.jsp" />