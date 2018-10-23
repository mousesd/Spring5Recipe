<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="members" scope="request" type="java.util.List<net.homenet.domain.Member>" />
<html>
<head>
    <title>Member List</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>
    <core:forEach items="${members}" var="member">
        <tr>
            <td>${member.name}</td>
            <td>${member.phone}</td>
            <td>${member.email}</td>
        </tr>
    </core:forEach>
</table>
</body>
</html>
