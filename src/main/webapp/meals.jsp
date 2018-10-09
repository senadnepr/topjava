<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark py-0">
    <div class="container">
        <a href="index.html" class="navbar-brand">
             Подсчет калорий</a>
    </div>
</nav>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center">Моя еда</h3>
        <table class="table table-striped table-borderless">
            <tr>
                <th>Дата/Время</th>
                <th>Описание</th>
                <th>Калории</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="meal" items="${mealsList}">
                <c:if test="${meal.exceed eq false}">
                    <tr class="text-success">
                </c:if>
                <c:if test="${meal.exceed eq true}">
                    <tr class="text-danger">
                </c:if>

                <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ parsedDateTime }" /></td>

                <%--<td>${meal.dateTime}</td>--%>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td></td>
                <td></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
