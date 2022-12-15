<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="include/header.jsp" />

	<div class="container">
		<h1>Home Page</h1>
	    
	    <br><br>
	
		<!-- New post creation -->
		<sec:authorize access="isAuthenticated()">
			
			<div class="card mb-4 box-shadow">
	            <div class="card-body">
	            	<form action="/user/profile/makePost" method="POST">
		            	<textarea id="formContent" name="contentText" rows="4" cols="50" placeholder="What's on your mind?"></textarea>
		                <div class="d-flex justify-content-between align-items-center">
		                    <div class="btn-group">
		                    	<button type="submit" class="btn btn-sm btn-secondary">POST</button>
		                    	<small class="text-muted">500 character limit</small>
		                    </div>
		            	</div>
		            </form>
	        	</div>
	        </div>	
	    </sec:authorize>
	    
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
	
	</div>

<jsp:include page="include/footer.jsp" />