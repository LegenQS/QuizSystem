<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Admin Quiz Home </title>
</head>

<%@ include file="admin_header.jsp" %>
<body style="margin:8px;padding:1px">
    <h1> Welcome to Admin Quiz Center </h1>
    <label> You can choose to view all the questions or create new questions below: </label>
    <div>
        <form method="get" action="/admin_create_quiz">
            <input type="submit" value="Create Question">
        </form>
        <form method="get" action="/admin_quiz_question">
            <input type="submit" value="Get Question">
        </form>
    </div>
</body>
</html>