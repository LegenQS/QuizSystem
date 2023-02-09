<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title>Quiz Result</title>
</head>

<%@ include file="user_header.jsp" %>

<body style="margin:8px;padding:1px">
<div>
    <div>
        <h1> ${sessionScope.user.username} final score is ${score}! </h1>
        <c:choose>
            <c:when test = "${score >= 3}">
                <h2> Congratulate! You have passed the quiz! </h2>
            </c:when>
            <c:otherwise>
                <h2> Sorry, you have failed the quiz, you can start a new quiz below. </h2>
            </c:otherwise>
        </c:choose>
        <br><br>
    </div>

    <div>
        <h1> Here is your quiz results: </h1> <br>
        <c:forEach begin="0" end="${quizResults.size() - 1}" var="i" varStatus="loop" step="4">
            <div>
                <label> ${quizResults.get(i).getQuestion_des()}$ </label> <br>
                <c:forEach begin="${i}" end="${i+3}" var="j" varStatus="loop">
                    <c:set var="right" value="0" />
                    <input type="radio"
                           style="background-color:lightBlue"
                    <c:if test="${quizResults.get(j).getUser_choice_id() == quizResults.get(j).getOption_id()}">checked="checked"</c:if>
                    >
                    <textarea rows=1
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
</div>

<form method="get" action="/user_quiz">
    <button type="submit"> restart </button>
</form>
</body>

</html>