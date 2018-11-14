<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h3>Sign up</h3>
<form:form modelAttribute="signupForm" method="post">
    <table>
        <tr>
            <td><form:label path="username"/></td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td><form:label path="password"/></td>
            <td><form:password path="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><button>Sign up</button></td>
        </tr>
    </table>
</form:form>
</body>
</html>
