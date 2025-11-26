<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Contact Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
</head>
<body>
    <div class="container">
        <h1>Contact Manager</h1>
        <a class="btn" href="${pageContext.request.contextPath}/contacts?action=new">Add Contact</a>
        <table>
            <thead><tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Actions</th></tr></thead>
            <tbody>
            <c:forEach var="c" items="${contacts}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.email}</td>
                    <td>${c.phone}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/contacts?action=edit&id=${c.id}">Edit</a> |
                        <a href="${pageContext.request.contextPath}/contacts?action=delete&id=${c.id}" onclick="return confirm('Delete?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
