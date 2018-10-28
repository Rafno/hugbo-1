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
<body>
<Form method="post" class="LoginForm">
        <div class="headers">Innskráning á Appótek</div>
    <div class="row">
        <input type="text" placeholder="Notendanafn" name="username" class="inputButton"/>
        <param name="username" value="">
    </div>
    <div class="row">
        <input type="text" placeholder="Lykilorð, Lágmark 6 stafir" name="password" class="inputButton"/>
        <param name="password" value="">
    </div>
    <div class="row">
        <button type="submit" class="LoginSubmitting">Staðfesta</button>
    </div>
</Form>
<div class="registering">
    <a href="/register">
        <button class="createNewUser"> Búa til nýjan Notanda</button>
    </a>
</div>
</body>