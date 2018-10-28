<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<jsp:useBean id="courtName" scope="request" type="java.lang.String"/>--%>
<%--<jsp:useBean id="reservations" scope="request" type="java.util.List<net.homenet.domain.Reservation>"/>--%>

<html>
<head>
    <title>Reservation Query</title>
</head>
<body>
<form method="post">
    Court Name
    <input type="text" name="courtName" value="${courtName}"/>
    <input type="submit" value="Query"/>
</form>

<table border="1">
    <tr>
        <th>Court Name</th>
        <th>Date</th>
        <th>Hour</th>
        <th>Player</th>
    </tr>
<core:forEach items="${reservations}" var="reservation">
    <tr>
        <td>${reservation.courtName}</td>
        <td><fmt:formatDate value="${reservation.dateAsUtilDate}" pattern="yyyy-MM-dd"/></td>
        <td>${reservation.hour}</td>
        <td>${reservation.player.name}</td>
    </tr>
</core:forEach>
</table>
</body>
</html>
