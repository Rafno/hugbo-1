<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="headNavigation/headNavigater.jsp" />

<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/medicine.css"/>"/>
</head>
<h1 class = "lowerBanner">
    <form method ="POST" class ="searchbar">

        <img src="${pageContext.request.contextPath}/${searchImg.png}" border=0 width="48px" height="48px" alt="Image Not found"/>
        <input type="text" name="search" placeholder="Search.."/>
        <button type="Leita"/>Leita</button>
    </form>
</h1>
<body>
</body>
<footer>Class HBV501G, University of Iceland</footer>
</html>

