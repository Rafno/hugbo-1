<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

    <head>
        <title>Project Title</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/headNavigation.css"/>"/>
    </head>
    <header>
        <ul class="headNavigation">
            <li class="itemNavigation"><a class="active" href="/">Appótek</a></li>
            <li class="itemNavigation"><a class="active" href="/about">Um vefsíðuna</a></li>
            <li class="itemNavigation" id="homeArea">
                <a class="active" href="/myHome">
                    <img src="./../../../img/houese.png" alt=""/>
                </a>
            </li>
            <li class="itemNavigation" id="login"><a class="active" href="/login">Innskráning</a></li>

        </ul>
    </header>
</html>
