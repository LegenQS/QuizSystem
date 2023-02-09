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
<form method="post" action="/user_feedback">
    <label > Please leave your feedback here (1000 characters limited): </label><br>
    <textarea name="message" rows="10" cols="60">Hope the website works well.</textarea> <br>

    <label > Rating: </label><br>
    <div class="slidecontainer">
        <input type="range" min="1" max="5" value="3" class="slider" name="rating"
               oninput="this.nextElementSibling.value = this.value">
        <output> 3 </output>
    </div>
    <input type="submit" value="Submit">
</form>

</body>
</html>
