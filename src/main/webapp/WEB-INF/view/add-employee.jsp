<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2>Employee details:</h2>
<br>

    <form:form action="saveEmployee" modelAttribute="employee">

        <form:hidden path="id"/>

        Name <form:input path="name" placeholder="Enter employee's name"/>
        <br><br>

        Surname <form:input path="surname" placeholder="Enter employee's surname"/>
        <br><br>

        Department <form:input path="department" placeholder="Enter employee's department"/>
        <br><br>

        Salary <form:input path="salary" placeholder="Enter employee's salary"/>
        <br><br>

        <input type="submit" value="OK"/>
    </form:form>

</body>
</html>