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
    <label> Display Filter with Only </label>
    <form method="post" action="user_quiz_results">
        <select name="filterCategoryID">
            <c:forEach items="${categories}" var="category" varStatus="loop">
                <option value="${category.getCat_id()}"> ${category.getCat_name()} </option>
            </c:forEach>
        </select>
        <input type="submit" value="submit">
    </form>
    <h1> Here are all results of your quizzes: </h1> <br>
    <table class="center" border-collapse="collapse" border="1px solid #FF0000">
        <tr>
            <th style="text-align: center"> Category ID </th>
            <th style="text-align: center"> Quiz Name </th>
            <th style="text-align: center"> Start Time </th>
            <th style="text-align: center"> End Time </th>
            <th style="text-align: center"> Score </th>
            <th style="text-align: center"> </th>
        </tr>
        <c:forEach items="${quizzes}" var="quiz" varStatus="loop">
            <tr>
                <td style="text-align: center"> ${quiz.getCat_id()} </td>
                <td style="text-align: center"> ${quiz.getQuiz_name()} </td>
                <td style="text-align: center"> ${quiz.getStart_time()} </td>
                <td style="text-align: center"> ${quiz.getEnd_time()} </td>
                <td style="text-align: center"> ${quiz.getScore()} </td>
                <td style="text-align: center">
                    <form method="post" action="/user_quiz_result_detail">
                        <input type="text" name="selectedQuizID" value="${quiz.getQuiz_id()}" hidden>
                        <input type="submit" value="details">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>


</html>
