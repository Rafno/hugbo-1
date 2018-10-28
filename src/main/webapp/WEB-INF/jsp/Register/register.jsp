<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigater.jsp" />

<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/medicine.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/register.css"/>"/>
</head>
<form method="post">
    <div class="row">
        <h3 class="title">Notendanafn</h3>
        <input type="text" placeholder="Enga íslenska stafi" name="notendanafn" />
    </div>
    <br />
    <br />
    <div class="row">
        <h3 class="title">Lykilorð</h3>
        <input type="text" placeholder="lágmark 6 stafir" name="lykilord" />
    </div>
    <br />
    <br />
    <div class="row">
        <h3 class="title">Nafn</h3>
        <input type="text" placeholder="Þarf að fylla út" name="nafn" />
    </div>
    <br />
    <br />
    <div class="row">
        <input type="submit" value="Staðfesta" id="confirm" />
    </div>
</form>