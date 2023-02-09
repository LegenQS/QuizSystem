<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="css.jsp" %>
    <title>Login</title>
</head>

<%@ include file="common_header.jsp" %>

<body style="margin:8px;padding:1px">
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/login">
        <div>
            <label>Username</label>
            <input type="text" name="username">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <button type="submit"> Submit </button>
    </form>
</div>
</body>

</html>
