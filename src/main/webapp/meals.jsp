<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <form method="post" action="meals">
        <input type="hidden" name="formName" value="filter">
        <input type="hidden" name="userid" value="${userid}">

        <table>
            <tr>
                <td>
                    <dl>
                        <dt>From Date:</dt>
                        <dd><input type="date" name="fromDate"></dd>

                    </dl>
                <td>
                    <dl>
                        <dt>From Time:</dt>
                        <dd><input type="time" name="fromTime"></dd>
                    </dl>
                </td>
            </tr>
            <tr>
                <td>
                    <dl>
                        <dt>To Date:</dt>
                        <dd><input type="date" name="toDate"></dd>
                    </dl>
                </td>
                <td>
                    <dl>
                        <dt>To Time:</dt>
                        <dd><input type="time" name="toTime"></dd>
                    </dl>
                </td>
            </tr>
        </table>
        <td><a href="meals?action=cancel">Cancel</a></td>
        <button type="submit">Filter</button>
    </form>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">

                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}&userid=${userid}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}&userid=${userid}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>