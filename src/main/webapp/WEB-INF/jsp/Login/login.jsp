<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigater.jsp" />

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
        <input type="text" placeholder="Notendanafn" name="notendanafn" id="notendanafn" />
    </div>
    <div class="row">
        <input type="text" placeholder="Lykilorð, Lágmark 6 stafir" name="lykilord" id="lykilord" />
    </div>
    <div class="row">
        <button type="submit" clas="LoginSubmitting">Staðfesta</button>
    </div>
</Form>
<div class="registering">
    <a href="/Register">
        <button> Búa til nýjan Notanda</button>
    </a>
</div>
</body>