<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring Social - Connections</title>
</head>
<body>
<h3>Spring Social - Connections</h3>
<core:forEach items="${providerIds}" var="provider">
    <h4>${provider}</h4>
    <core:if test="${not empty connectionMap[provider]}">
        You are connected to ${provider} as ${connectionMap[provider][0].displayName}
    </core:if>
    <core:if test="${empty connectionMap[provider]}">
        <div>
            You are not yet connected to ${provider}. Click <a href="<spring:url value="/connect/${provider}"/>">here</a> to connect to ${provider}.
        </div>
    </core:if>
</core:forEach>
</body>
</html>
