<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="include/header.jsp" />

	<div class="container">
		<h1>Welcome to ${user.firstName} ${user.lastName}'s Profile Page</h1>
	    
	    <br><br>
	
	    <!-- Post Card List -->
	    <u:forEach items="${posts}" var="post">
			<div class="col-md-6">
				<div class="card flex-md-row mb-4 box-shadow h-md-250" style="background-color: #0C690C; ">
					<div class="card-body d-flex flex-column align-items-start">
						<h3 class="mb-0">
							<a class="text-dark">${post.getAuthor().getFirstName()} ${post.getAuthor().getLastName()}</a>
						</h3>
						<div class="mb-1 text-muted">${post.timePosted}</div>
								<span class="border p-2 m-2">
									<p class="card-text mb-auto px-2" style="color: white; text-shadow: 2px 2px #000000;">
										${post.contentText}
									</p>
								</span>
							
							<!-- Post Comments Estimate -->
							<span class="border">
								<div class="col">
									<li class="list-group-item d-flex justify-content-between lh-condensed">
									   	<a href="/user/profile/post?id=${post.id}"><h6 class="my-0">${post.getComments().size()} comments are on this post...See More</h6></a>
									</li>
								</div>
							</span>
						</div>
					</div>
				</div>
	    </u:forEach>
		
		<!-- Friends List -->
		<div class="col-md-4 order-md-2 mb-4">
          <h4 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-muted">Friends List</span>
            <span class="badge badge-secondary badge-pill">3</span>
          </h4>
          <ul class="list-group mb-3">
            <u:forEach items="${friends}" var="friend">
            	<li class="list-group-item d-flex justify-content-between lh-condensed">
		           	<h6 class="my-0">${friend.firstName} ${friend.lastName}</h6>
		           	<form action="/userCtrl/add" method="POST"> 
				        <sec:authorize access="isAuthenticated()">
							<button type="submit" class="btn-primary btn-block">Add Friend</button>
						</sec:authorize>
				        <input type="hidden" name="userId" value="${curUser.id}"/>
				        <input type="hidden" name="friendId" value="${user.id}"/>
				    </form>
		        </li>
		        
		    </u:forEach>
          </ul>
        </div>
			
	</div>

<jsp:include page="include/footer.jsp" />