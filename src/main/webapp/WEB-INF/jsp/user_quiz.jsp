<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Quiz Home </title>
</head>

<%@ include file="user_header.jsp" %>

<body style="margin:8px;padding:1px">
<div>
    <form method="post" action="/user_quiz">
        <label> Choose a category to start: </label>

        <select name="selectedCategoryID">
            <c:forEach items="${categories}" var="category" varStatus="loop">
                <option value="${category.getCat_id()}"> ${category.getCat_name()} </option>
            </c:forEach>
        </select> <br> <br>

        <label> Type your quiz name below: </label> <br>
        <textarea name="quiz_name" rows="2" cols="25">${user.getUsername()}'s quiz
        </textarea> <br>

        <div>
            <button type="submit"> submit </button>
        </div>
    </form>
</div>
</body>


</html>
