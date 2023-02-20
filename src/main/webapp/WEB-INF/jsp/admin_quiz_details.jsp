<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Admin Quiz Home </title>
</head>

<%@ include file="admin_header.jsp" %>

<body style="margin:8px;padding:1px">
<div>
    <h1> User quiz result details: </h1> <br>
    <c:forEach begin="0" end="${quizResults.size() - 1}" var="i" varStatus="loop" step="4">
        <div>
            <label> ${fn:substringBefore(i / 4 + 1, '.')}. ${quizResults.get(i).getQuestion_des()} </label> <br>
            <c:forEach begin="${i}" end="${i+3}" var="j" varStatus="loop">
                <c:set var="right" value="0" />
                <input type="radio"
                       style="background-color:lightBlue"
                <c:if test="${quizResults.get(j).getUser_choice_id() == quizResults.get(j).getOption_id()}">checked="checked"</c:if>
                >
                <textarea rows=1 cols=30
                    <c:choose>
                        <c:when test="${quizResults.get(j).getUser_choice_id() == quizResults.get(j).getOption_id() && quizResults.get(j).is_correct()}">
                            style="background-color:lightBlue"
                        </c:when>
                        <c:when test="${quizResults.get(j).getUser_choice_id() == quizResults.get(j).getOption_id()}">
                            style="background-color:red"
                        </c:when>
                        <c:when test="${quizResults.get(j).is_correct()}">
                            style="background-color:lightBlue"
                        </c:when>
                    </c:choose>
                >
                ${quizResults.get(j).getOption_des()}</textarea> <br>
            </c:forEach>
        </div>
    </c:forEach>
</div>
</body>


</html>
