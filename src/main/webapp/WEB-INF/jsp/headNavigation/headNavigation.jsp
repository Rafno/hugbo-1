
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

    <head>
        <title>Appótekið</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/headNavigation.css"/>"/>
    </head>
<c:if test="${not empty loggedInn}">
    <header>
        <ul class="headNavigation">
            <li class="itemNavigation"><a class="active" href="/">Appótek</a></li>
            <li class="itemNavigation"><a class="active" href="/about">Um vefsíðuna</a></li>
            <li class="itemNavigation" id="homeArea">
                <a href="/myHome"class="active">
                    <img src="./../../../img/house.png" alt="headImage"class="headNavigationPic"/>
                </a>
            </li>
            <li class="itemNavigation" id="logout"><a class="active" href="/logout" >Útskráning</a></li>
            <li class="itemNavigation"><a class="active">${name}</a></li>

        </ul>
    </header>
</c:if>
<c:if test="${empty loggedInn}">
    <ul class="headNavigation">
        <li class="itemNavigation"><a class="active" href="/">Appótek</a></li>
        <li class="itemNavigation"><a class="active" href="/about">Um vefsíðuna</a></li>
        <li class="itemNavigation">
            <a href="/myHome"class="active">

            </a>
        </li>
        <li class="itemNavigation" id="login"><a class="active" href="/login" >Innskráning</a></li>
        <li class="itemNavigation" ><a class="active" href="/register">Búa til aðgang</a></li>
    </ul>
</c:if>
</html>
