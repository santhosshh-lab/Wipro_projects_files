<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
</head>
<body>
<div class="container">
    <h1>Add Contact</h1>
    <form method="post" action="${pageContext.request.contextPath}/contacts">
        <input type="hidden" name="action" value="create"/>
        <label>Name</label><input type="text" name="name" required/>
        <label>Email</label><input type="email" name="email"/>
        <label>Phone</label><input type="text" name="phone"/>
        <button type="submit">Save</button>
        <a class="btn" href="${pageContext.request.contextPath}/contacts">Cancel</a>
    </form>
</div>
</body>
</html>
