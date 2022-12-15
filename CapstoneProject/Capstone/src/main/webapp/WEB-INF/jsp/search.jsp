<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="include/header.jsp" />

	<div class="container">
	
		<h2>Listing of Users</h2>
		<br><br>
		
		<!-- User Listing -->
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
	                <td>
	                	<form action="/userCtrl/add" method="POST"> 
				        	<sec:authorize access="isAuthenticated()">
								<button type="submit" class="btn-primary btn-block">Add Friend</button>
							</sec:authorize>
				            <input type="hidden" name="userId" value="${curUser.id}"/>
				            <input type="hidden" name="friendId" value="${user.id}"/>
				    	</form>
					</td>
	            </tr>
	        </u:forEach>
	    </table>
	
	</div>
	
<jsp:include page="include/footer.jsp" />