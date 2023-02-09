<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="css.jsp" %>
    <title> Contact </title>
</head>

<%@ include file="admin_header.jsp" %>

<body style="margin:8px;padding:1px">
<h1> Contact Information </h1>
<table class="center" border-collapse="collapse" border="1px solid #FF0000">
      <tr>
          <th style="text-align: center"> User ID </th>
          <th style="text-align: center"> Message </th>
          <th style="text-align: center"> Time </th>
      </tr>
      <c:forEach items="${contacts}" var="contact" varStatus="loop">
          <tr>
              <td style="text-align: center"> ${contact.getUser_id()} </td>
              <td style="text-align: center"> ${contact.getMessage()} </td>
              <td style="text-align: center"> ${contact.getTime()} </td>
          </tr>
      </c:forEach>
</table>
</body>

</html>