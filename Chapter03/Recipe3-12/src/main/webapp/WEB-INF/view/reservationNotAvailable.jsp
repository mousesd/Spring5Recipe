<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<jsp:useBean id="exception" scope="request" type="net.homenet.exception.ReservationNotAvailableException" />--%>

<html>
<head>
    <title>Reservation Not Available</title>
</head>
<body>
Your reservation for ${exception.courtName} is not available on <fmt:formatDate value="${exception.dateAsUtil}" pattern="yyyy-MM-dd"/>
at ${exception.hour}:00.
</body>
</html>
