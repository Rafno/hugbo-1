<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head th:include="layout :: head(title=~{::title},links=~{})">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body th:include="layout :: body" th:with="content=~{::content}">
<div th:fragment="content">
    <form name="f" th:action="login" method="post" class="LoginForm">
            <div class="headers">Innskráning á Appótek</div>
        <div class="row">
            <p>username = user, password = userPass</p>
            <label for="username">Username</label>
            <input type="text" id="username" name='username'/>
        </div>
        <div class="row">
            <label for="password">Password</label>
            <input type="password" id="password" name='password'/>
            <div class="form-actions">
                <button type="submit" class="LoginSubmitting">Log in</button>
            </div>
        </div>
    </form>
    <div class="registering">
        <a href="/register">
            <button class="createNewUser"> Búa til nýjan Notanda</button>
        </a>
    </div>
</div>
</body>
