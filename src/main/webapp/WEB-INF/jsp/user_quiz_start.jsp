<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Quiz Start </title>
</head>

<%@ include file="user_header.jsp" %>

<body style="margin:8px;padding:1px">
<h1> Welcome to your quiz session </h1>
<div>
    <form method="post" action="user_quiz_start">
        <c:forEach items="${questionOption}" var="questionOps" varStatus="loop">
            <input type="text" name="question${questionOps.getSeq_id()}"
                   value="${questionOps.getQuestion().getQuestion_id()}" hidden>
            <label> ${questionOps.getSeq_id()}. ${questionOps.getQuestion().getDescription()} </label> <br>
            <c:forEach items="${questionOps.getOptions()}" var="option" varStatus="loop">
                <input type="radio"
                       name="user_choice${questionOps.getSeq_id()}"
                       value="${option.getOption_id()}"
                       checked> ${option.getDescription()} <br>
            </c:forEach>
            <br>
        </c:forEach>
        <button type="submit"> submit </button>
    </form>
</div>
</body>
</html>
