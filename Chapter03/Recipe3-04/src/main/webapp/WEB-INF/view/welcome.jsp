<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="today" scope="request" type="java.util.Date"/>
<jsp:useBean id="handlingTime" scope="request" type="java.lang.Long"/>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Welcome Court Reservation System</h2>
Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>.

<hr/>
Handling time: ${handlingTime}ms

<br/>
Locale: ${pageContext.response.locale}
</body>
</html>
