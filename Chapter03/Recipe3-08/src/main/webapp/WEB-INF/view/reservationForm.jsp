<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Reservation Form</title>
</head>
<body>
<form:form method="post" modelAttribute="reservation">
    <table>
        <tr>
            <td>Court Name</td>
            <td><form:input path="courtName"/></td>
        </tr>
        <!--
        <tr>
            <td>Date</td>
            <td><form:input path="date"/></td>
        </tr>
        -->
        <tr>
            <td>Hour</td>
            <td><form:input path="hour"/></td>
        </tr>
        <tr>
            <td>Player Name</td>
            <td><form:input path="player.name"/></td>
        </tr>
        <tr>
            <td>Player Phone</td>
            <td><form:input path="player.phone"/></td>
        </tr>
        <!--
        <tr>
            <td>Sport Type</td>
            <td><form:select path="sportType" items="${sportTypes}" itemValue="id" itemLabel="name" /></td>
        </tr>
        -->
        <tr>
            <td colspan="3"><input type="submit"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
