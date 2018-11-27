<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!doctype html>
<html>
<body>

<h1>Welcome</h1>
<p>
    Your User-Agent header: <core:out value="${header['User-Agent']}" />
</p>
<p>
    Your type of device: <core:out value="${requestScope.currentDevice}" />
</p>

</body>
</html>
