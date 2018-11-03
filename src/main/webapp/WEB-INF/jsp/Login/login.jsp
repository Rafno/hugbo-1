<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />
<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>

<h1>Innskr�ning</h1>

<c:if test="${not empty errorMsg}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMsg}</div></c:if>

<form name='login' action="/login" method='POST'>
    <table>
        <tr>
            <td>UserName:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit"type="submit" value="Sta�festa" /></td>
        </tr>
    </table>
    <a class="createNewUser" href="<c:url value="/register" />">Register</a>
    <a class="btn" href="<c:url value="/logout" />">Logout</a>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>