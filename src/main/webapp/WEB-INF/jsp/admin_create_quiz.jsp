<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Admin Home </title>
</head>

<%@ include file="admin_header.jsp" %>

<body style="margin:8px;padding:1px">
<h1> Create Question Below </h1>

<form method="post" action="/admin_create_quiz">
    <label for="cate"> Choose a category of the question </label>

    <select name="category">
        <c:forEach items="${categories}" var="category" varStatus="loop">
            <option value="${category.getCat_name()}"> ${category.getCat_name()} </option>
        </c:forEach>
    </select>
    <br> <br>

    <label > Question Description (100 characters limited) </label><br>
    <textarea name="description" rows="3" cols="60"> </textarea> <br> <br>

    <input type="radio"
           name="correct_answer"
           value="1"/> A.
    <textarea name="option1" rows="2" cols="50"> </textarea> <br> <br>

    <input type="radio"
        name="correct_answer"
        value="2"/> B.
    <textarea name="option2" rows="2" cols="50"> </textarea> <br> <br>

    <input type="radio"
        name="correct_answer"
        value="3"/> C.
    <textarea name="option3" rows="2" cols="50"> </textarea> <br> <br>

    <input type="radio"
        name="correct_answer"
        value="4"/> D.
    <textarea name="option4" rows="2" cols="50"> </textarea> <br> <br>

    <input type="submit" value="Submit">
</form>
</body>
</html>