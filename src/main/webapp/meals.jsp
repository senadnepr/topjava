<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

            <%--<button class="btn btn-primary" data-toggle="modal" data-target="#editMeal" data-whatever="Добавить">--%>
        <form action="meals" method="post">
            <button class="submit btn btn-primary" value="add" name="button">
                <span class="fa fa-plus"></span>
                Добавить
            </button>
        </form>
        <table class="table table-striped table-borderless ">
            <thead class="thead-dark">
            <tr>
                <%--<th>ID</th>--%>

                <th>Дата/Время</th>
                <th>Описание</th>
                <th>Калории</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach var="meal" items="${mealsList}">
                <c:if test="${meal.exceed eq false}">
                    <c:set var="textStyle" value="text-success"/>
                </c:if>
                <c:if test="${meal.exceed eq true}">
                    <c:set var="textStyle" value="text-danger"/>

                </c:if>

                <form action="meals" method="post">

                <tr class="${textStyle}">
                    <td hidden>
                        <input type="text" name="id" value="${meal.id}">
                    </td>

                    <td><fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                       type="both"/>
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ parsedDateTime }"/></td>

                        <%--<td>${meal.dateTime}</td>--%>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td>
                            <button class="submit btn btn-link ${textStyle}" value="edit" name="button">
                                <span class="fa fa-edit"></span>
                            </button>
                    </td>
                    <td>
                            <label></label>
                            <button class="submit btn btn-link ${textStyle}" value="delete" name="button">
                                <span class="fa fa-remove"></span>
                            </button>
                    </td>
                </tr>
                </form>
            </c:forEach>
        </table>
    </div>
</div>

<%--<div class="modal fade" id="editMeal">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>

            <%--<div class="modal-header">--%>
                <%--<h4 class="modal-title">modalHeader</h4>--%>
                <%--<button type="button" class="close" data-dismiss="modal">--%>
                    <%--&lt;%&ndash;&times;&ndash;%&gt;--%>
                    <%--<span class="fa fa-remove"></span>--%>
                <%--</button>--%>
            <%--</div>--%>

            <%--<!-- Modal body -->--%>
            <%--<div class="modal-body">--%>
                <%--<form id="detailsForm">--%>
                    <%--&lt;%&ndash;<input type="hidden" id="id" name="id">&ndash;%&gt;--%>

                    <%--<div class="form-group">--%>
                        <%--<label for="dateTime" class="col-form-label">Дата/Время</label>--%>
                        <%--<input class="form-control" id="dateTime" name="dateTime"--%>
                               <%--placeholder="Дата/Время">--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label for="description" class="col-form-label">Описание</label>--%>
                        <%--<input type="text" class="form-control" id="description" name="description"--%>
                               <%--placeholder="Описание">--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label for="calories" class="col-form-label">Калории</label>--%>
                        <%--<input type="text" class="form-control" id="calories" name="calories" placeholder="1000">--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>

            <%--<!-- Modal footer -->--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-secondary" data-dismiss="modal">--%>
                    <%--<span class="fa fa-close"></span>--%>
                    <%--Отменить--%>
                <%--</button>--%>
                <%--<button type="button" class="btn btn-primary" onclick="save()">--%>
                    <%--<span class="fa fa-check"></span>--%>
                    <%--Сохранить--%>
                <%--</button>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
</body>
</html>
