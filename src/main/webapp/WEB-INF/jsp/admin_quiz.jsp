<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Admin Quiz Home </title>
</head>

<%@ include file="admin_header.jsp" %>

<body style="margin:8px;padding:1px">
    <br>
    <div>
        <label> Order By </label>
        <form method="post" action="admin_quiz">
            <select name="orderBy">
                <option value="user_id"> User ID </option>
                <option value="cat_id"> Category ID </option>
                <option value="quiz_name"> Quiz Name </option>
                <option value="start_time"> Start Time </option>
                <option value="end_time"> End Time </option>
                <option value="score"> Score </option>
            </select>
            <select name="order">
                <option value="asc"> Ascending </option>
                <option value="desc"> Descending </option>
            </select>
            <input type="submit" value="submit">
        </form>
    </div>
    <br>
    <div>
        <table border-collapse="collapse" border="1px solid #FF0000">
            <tr style="text-align: center">
                <th style="text-align: center"> Quiz ID </th>
                <th style="text-align: center"> User ID </th>
                <th style="text-align: center"> Category ID </th>
                <th style="text-align: center"> Quiz Name </th>
                <th style="text-align: center"> Start Time </th>
                <th style="text-align: center"> End Time </th>
                <th style="text-align: center"> Score </th>
                <th style="text-align: center">  </th>
            </tr>
            <c:forEach items="${quizzes}" var="quiz" varStatus="loop">
                <tr style="text-align: center">
                    <td style="text-align: center"> ${quiz.getQuiz_id()} </td>
                    <td style="text-align: center"> ${quiz.getUser_id()} </td>
                    <td style="text-align: center"> ${quiz.getCat_id()} </td>
                    <td style="text-align: center"> ${quiz.getQuiz_name()} </td>
                    <td style="text-align: center"> ${quiz.getStart_time()} </td>
                    <td style="text-align: center"> ${quiz.getEnd_time()} </td>
                    <td style="text-align: center"> ${quiz.getScore()} </td>
                    <td style="text-align: center">
                        <form method="post" action="/admin_quiz_details">
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