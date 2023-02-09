<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="css.jsp" %>
    <title>Register</title>
</head>

<%@ include file="common_header.jsp" %>

<body style="margin:8px;padding:1px">
<div>
    <div>
        <form method="post" action="/register">
            <div>
                <label> Username </label>
                <input type="text" name="username" required>
            </div>
            <div>
                <label> Password </label>
                <input type="password" name="password" required>
            </div>
            <div>
                <label> Email </label>
                <input type="email" name="email" required>
            </div>
            <div>
                <label> First Name </label>
                <input type="text" name="first_name" required>
            </div>
            <div>
                <label> Last Name </label>
                <input type="text" name="last_name" value="">
            </div>
            <div>
                <label> Address </label>
                <input type="text" name="address" value="">
            </div>
            <div>
                <label> Phone Number </label>
                <input type="tel" type="tel" id="phone" name="phone" pattern="[0-9]{10}" required>
            </div>
            <button type="submit"> Submit </button>
        </form>

        <form method="get" action="/login">
            <button type="submit"> Back </button>
        </form>
    </div>
</div>
</body>

</html>
