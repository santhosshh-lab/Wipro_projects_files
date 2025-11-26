<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
</head>
<body>
<div class="container">
    <h1>Edit Contact</h1>
    <form method="post" action="${pageContext.request.contextPath}/contacts">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="${contact.id}" />
        <label>Name</label><input type="text" name="name" value="${contact.name}" required/>
        <label>Email</label><input type="email" name="email" value="${contact.email}" />
        <label>Phone</label><input type="text" name="phone" value="${contact.phone}" />
        <button type="submit">Update</button>
        <a class="btn" href="${pageContext.request.contextPath}/contacts">Cancel</a>
    </form>
</div>
</body>
</html>
