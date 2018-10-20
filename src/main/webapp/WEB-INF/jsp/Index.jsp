<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

<jsp:include page="headNavigation/headNavigater.jsp" />

<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>


<div class="lowerBanner_container">
    <p class="headerTxt">  Lyfjask�purinn heima hj� ��r</p>
    <div class = "lowerBanner">

        <label>
            <input type="text" placeholder="Leita a� lyfjum � �slenskum marka�i" class="searchTextArea">
        </label>
    </div>

</div>
<body>
</body>
<footer>Class HBV501G, University of Iceland</footer>
</html>