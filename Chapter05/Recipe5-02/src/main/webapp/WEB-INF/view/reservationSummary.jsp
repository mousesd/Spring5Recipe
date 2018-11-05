<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Reservation Summary</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Court Name</th>
        <th>Date</th>
        <th>Hour</th>
        <th>Player Name</th>
        <th>Player Phone</th>
    </tr>
<core:forEach items="${reservations}" var="reservation">
    <tr>
        <td>${reservation.courtName}</td>
        <td>${reservation.date}</td>
        <td>${reservation.hour}</td>
        <td>${reservation.player.name}</td>
        <td>${reservation.player.phone}</td>
    </tr>
</core:forEach>
</table>
</body>
</html>
