<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title> FeedBack </title>
</head>

<%@ include file="admin_header.jsp" %>

<body style="margin:8px;padding:1px">
<h1> FeedBack Information </h1>
<table class="center" border-collapse="collapse" border="1px solid #FF0000">
      <tr>
          <th style="text-align: center"> FeedBack ID </th>
          <th style="text-align: center"> Rating </th>
          <th style="text-align: center"> Description </th>
      </tr>
      <c:forEach items="${feedbacks}" var="feedback" varStatus="loop">
          <tr>
              <td style="text-align: center"> ${feedback.getFeedback_id()} </td>
              <td style="text-align: center"> ${feedback.getRating()} </td>
              <td style="text-align: center"> ${feedback.getDescription()} </td>
          </tr>
      </c:forEach>
</table>
</body>

</html>