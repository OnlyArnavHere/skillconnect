<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h1>Search Results for "${query}"</h1>
        <a href="app/dashboard.jsp">Back to Dashboard</a>
        <hr>
        <c:choose>
            <c:when test="${not empty results}">
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Bio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${results}">
                            <tr>
                                <td>${user.firstName} ${user.lastName}</td>
                                <td>${user.email}</td>
                                <td>${user.bio}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No users found with that skill.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
