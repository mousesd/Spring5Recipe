<%--suppress ELValidationInJSP --%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<core:url var="formLogin" value="/signin/authenticate"/>
<core:if test="${param.error eq 'bad_credentials'}">
    <div class="error">
        The login information was incorrect. please try again.
    </div>
</core:if>

<form method="post" action="${formLogin}">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <table>
        <tr>
            <td><label for="username">UserName</label></td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
            <td><label for="password">Password</label></td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td colspan="2"><button>Login</button></td>
        </tr>
    </table>
</form>
</body>
</html>
