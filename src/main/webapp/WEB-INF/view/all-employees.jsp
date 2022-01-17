<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta id="root" about="${pageContext.request.contextPath}">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/css/home.css" rel="stylesheet">
    <title>Home</title>

    <style>
        table {
            width: 600px;
            border: 2px solid greenyellow;
            margin: auto;
        }
        td {
            text-align: center;
        }
        h2 {
            width: 300px;
            margin: auto;
        }
    </style>
</head>


<body>

<h2>All Employees:</h2>
<br>

<table>

    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Department</th>
        <th>Salary</th>
        <th>Operations</th>
    </tr>

    <c:forEach var="temp" items="${allEmps}">

        <c:url var="updateButton" value="/edit">
               <c:param name="tempId" value="${temp.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/delete">
            <c:param name="tempId" value="${temp.id}"/>
        </c:url>

        <tr>
            <td>${temp.name}</td>
            <td>${temp.surname}</td>
            <td>${temp.department}</td>
            <td>${temp.salary}</td>
            <td>
                <input type="button" value="update"
                       onclick="window.location.href = '${updateButton}'"/>
                <input type="button" value="delete"
                    onclick="window.location.href = '${deleteButton}'"/>
            </td>
        </tr>

    </c:forEach>
</table>

<br>

<input class="test" type="button" value="Add"
       onclick="window.location.href = 'create'">

</body>
</html>
