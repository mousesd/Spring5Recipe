<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="profile" scope="request" type="org.springframework.social.connect.UserProfile"/>
<html>
<head>
    <title>Facebook Profile</title>
</head>
<body>
<h3>Twitter Profile</h3>
<p>
<table>
    <tr>
        <td>Name:</td>
        <td>${profile.name}</td>
    </tr>
    <tr>
        <td>UserName:</td>
        <td>${profile.username}</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>${profile.email}</td>
    </tr>
</table>
</body>
</html>
