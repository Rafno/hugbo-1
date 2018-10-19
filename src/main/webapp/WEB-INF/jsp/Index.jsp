<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

<jsp:include page="headNavigation/headNavigater.jsp" />

<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/medicine.css"/>"/>
</head>
<div class = "lowerBanner">
    <h2 class = "headerTxt"> Lyfjaskápurinn heima hjá þér </h2>
    <form method ="POST" class ="searchbar">
        <div class="search-wrapper">
            <img src="../../img/searchImg.png" alt=""/>
            <input class="search-input" placeholder="Search..." type="text" value="" name="search" id="search" />
        </div>
    </form>
</div>
<body>
</body>
<footer>Class HBV501G, University of Iceland</footer>
</html>