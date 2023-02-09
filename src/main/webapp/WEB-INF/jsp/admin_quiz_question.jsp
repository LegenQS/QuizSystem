<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Admin Home </title>
</head>

<%@ include file="admin_header.jsp" %>

<body style="margin:8px;padding:1px">
<div>
    <h1> Here are all the questions registered </h1>
    <br>
    <table border-collapse="collapse" border="1px solid #FF0000">
          <tr>
              <th style="text-align: center"> Question ID </th>
              <th style="text-align: center"> Question Description </th>
              <th style="text-align: center"> Category ID </th>
              <th style="text-align: center"> </th>
          </tr>
          <c:forEach items="${questions}" var="question" varStatus="loop">
              <tr>
                  <td style="text-align: center"> ${question.getQuestion_id()} </td>
                  <td style="text-align: center"> ${question.getDescription()} </td>
                  <td style="text-align: center"> ${question.getCat_id()} </td>
                  <td style="text-align: center">
                      <form method="post" action="admin_quiz_question">
                          <input type="text" name="selectedQuestionID" value="${question.getQuestion_id()}" hidden>
                          <input type="submit" value="details">
                      </form>
                  </td>
              </tr>
          </c:forEach>
    </table>
</div>
</body>
</html>


