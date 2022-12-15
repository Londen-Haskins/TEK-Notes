<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="include/header.jsp" />

	<div class="container">
	
		<!-- Post Main Card -->
		<div class="col-md-6 my-3">
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
						<sec:authorize access="isAuthenticated()">
							<form action="/userCtrl/makeComment" method="POST">
								<input type="hidden" name="userId" value="${curUser.getId()}"/>
								<input type="hidden" name="postId" value="${post.getId()}"/>
				            	<textarea id="formContent" name="message" rows="2" cols="50" placeholder="What's on your mind?"></textarea>
				                <div class="d-flex justify-content-between align-items-center">
				                    <div class="btn-group">
				                    	<button type="submit" class="btn btn-sm btn-secondary">COMMENT</button>
				                    	<small class="text-muted">200 character limit</small>
				                    </div>
				            	</div>
				            </form>
				        </sec:authorize>
						<!-- Post Comments List -->
						<span class="border">
							<div class="col">
								<ul class="list-group mb-3 p-2">
									<u:if test="${commentsExist}">
							            <u:forEach items="${comments}" var="comment" varStatus="status">
							            	<li class="list-group-item d-flex justify-content-between lh-condensed">
									           	<h6 class="my-0 mx-1 p-2">${commentNames.get(status.count - 1)}</h6>
									           	<p>${comment.message}</p>
									        </li>
									    </u:forEach>
									</u:if>
						        </ul>
							</div>
						</span>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	
<jsp:include page="include/footer.jsp" />