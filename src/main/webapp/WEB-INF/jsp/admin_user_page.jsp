<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="css.jsp" %>
    <title>User Page</title>
</head>

<%@ include file="admin_header.jsp" %>

<body style="margin:8px;padding:1px">
<h1> User Information </h1>
<table class="center" border-collapse="collapse" border="1px solid #FF0000">
      <tr>
          <th style="text-align: center"> User Name </th>
          <th style="text-align: center"> First Name </th>
          <th style="text-align: center"> Last Name </th>
          <th style="text-align: center"> Address </th>
          <th style="text-align: center"> Email  </th>
          <th style="text-align: center"> Phone Number </th>
          <th style="text-align: center"> User Status </th>
          <th style="text-align: center"> Suspend </th>
      </tr>
      <c:forEach items="${all_user}" var="user" varStatus="loop">
          <form method="post" action="/user_activate">
              <tr>
                  <td style="text-align: center"> ${user.getUsername()} </td>
                  <td style="text-align: center"> ${user.getFirst_name()} </td>
                  <td style="text-align: center"> ${user.getLast_name()} </td>
                  <td style="text-align: center"> ${user.getAddress()} </td>
                  <td style="text-align: center"> ${user.getEmail()} </td>
                  <td style="text-align: center"> ${user.getPhone()} </td>
                  <td style="text-align: center"> ${user.is_active()} </td>
                  <td style="text-align: center">
                      <input type="text" name="user_id" value="${user.getUser_id()}" hidden>
                      <input type="submit" name="updateID" value=${user.is_active() ? 'Deactivate' : 'Activate'}>
                  </td>
              </tr>
          </form>
      </c:forEach>
</table>
</body>
</html>