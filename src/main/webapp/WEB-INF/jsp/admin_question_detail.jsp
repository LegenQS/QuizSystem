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
    <form method="post" action="/admin_question_detail">
        <label> Choose the category of the question </label>

        <select name="category">
            <c:forEach items="${categories}" var="category" varStatus="loop">
                <option value="${category.getCat_name()}"
                        <c:if test="${category.getCat_id() == questionOption.getQuestion().getCat_id()}">selected="selected"</c:if>
                > ${category.getCat_name()} </option>
            </c:forEach>
        </select>
        <br>

        <label > Question Description (100 characters limited) </label><br>
        <input type="text" name="question_id" value="${questionOption.getQuestion().getQuestion_id()}" hidden>
        <textarea name="description"
                  value="${questionOption.getQuestion().getDescription()}"
                  rows="3" cols="60">${questionOption.getQuestion().getDescription()}</textarea> <br>
        <br>

        <c:forEach items="${questionOption.getOptions()}" var="questionOps" varStatus="loop">
            <input type="radio"
                   name="correct_answer"
                   value="${questionOps.getOption_id()}"
                   <c:if test="${questionOps.is_correct()}">checked="checked"</c:if>
            >
            <input type="text"
                   name="optionID${questionOption.getOptions().indexOf(questionOps)}"
                   value="${questionOps.getOption_id()}" hidden>
            <textarea rows="2" cols="50"
                      name="option${questionOption.getOptions().indexOf(questionOps)}"
                      value="${questionOps.getDescription()}"
                      >${questionOps.getDescription()}</textarea> <br>
        </c:forEach>
        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>