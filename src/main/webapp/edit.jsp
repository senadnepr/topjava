<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</head>
<body>
<div class="jumbotron pt-20">
    <div class="container">
        <h3 class="text-center">${headerString}</h3>
        <form action="meals" method="post">
            <div hidden>
                <input type="text" name="id" value="${meal.id}">
            </div>
            <div class="form-group">
                <label for="datetime">Дата/Время:</label>
                <input type="text" class="form-control" name="datetime" id="datetime" value="${meal.dateTime}">
            </div>
            <div class="form-group">
                <label for="description">Описание:</label>
                <input type="text" class="form-control" name="description" id="description" value="${meal.description}">
            </div>
            <div class="form-group">
                <label for="calories">Калории:</label>
                <input type="number" class="form-control" name="calories" id="calories" value="${meal.calories}">
            </div>
            <button type="submit" class="btn btn-primary" name="button" value="save">Сохранить</button>
            <button type="submit" class="btn btn-danger" name="button" value="cancel">Отмена</button>
        </form>
    </div>
</div>
</body>
</html>
