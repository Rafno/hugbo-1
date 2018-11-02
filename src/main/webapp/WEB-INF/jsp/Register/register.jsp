<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/medicine.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/register.css"/>"/>
</head>
<h1 class="registerHeader">Búa til nýjan notanda</h1>
<form method="post" class="registering">
    <div class="row">
        <h2 class="title">Notendanafn</h2>

            <input type="text" placeholder="Enga íslenska stafi" name="username"  class="registerText"/>
            <param name="username" value="">
        </div>
    <div class="errors">
        <ul>
            <c:forEach items="${notendaVillur}" var="obj">
                <li><c:out value="${obj}"/></li>
            </c:forEach>
        </ul>
    </div>
    <br />
    <br />
    <div class="row">
        <h2 class="title">Lykilorð</h2>
        <input type="text" placeholder="Lágmark 6 stafir" name="password"style="-webkit-text-security: disc;" class="registerText"/>
        <param name="password" value="">
    </div>
    <div class="errors">
        <ul>
            <c:forEach items="${lykilordVillur}" var="obj">
                <li><c:out value="${obj}"/></li>
            </c:forEach>
        </ul>
    </div>
    <br />
    <br />
    <div class="row">
        <h2 class="title">Staðfesta lykilorð</h2>
        <input type="text" placeholder="Passaðu að hafa lykilorðin eins " name="passwordRepeat" style="-webkit-text-security: disc;" class="registerText"/>
    </div>
    <br />
    <br />
    <div class="row">
        <h2 class="title">Nafn</h2>
        <input type="text" placeholder="Þarf að fylla út" name="name" value="${nafn}" required class="registerText"/>
        <param name="name" value="">
    </div>
    <br />
    <input type="text" value="${succesfull}" id="succesfull" readonly>
    <br />
    <br />
    <div class="row">
        <input type="submit" value="Staðfesta" id="confirm"/>
    </div>
</form>