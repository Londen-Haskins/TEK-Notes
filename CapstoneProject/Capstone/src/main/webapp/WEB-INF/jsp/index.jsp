<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<jsp:include page="include/footer.jsp" />