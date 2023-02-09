<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title>Quiz</title>
</head>

<%@ include file="user_header.jsp" %>

<body style="margin:8px;padding:1px">
<br>
<br>
<form method="post" action="/user_contact">
    <label> Please leave your message here (1000 characters limited): </label><br>
    <textarea name="message" rows="10" cols="60">Hope the website works well.</textarea> <br> <br>

    <input type="submit" value="Submit">
</form>

</body>
</html>
