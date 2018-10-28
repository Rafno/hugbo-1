<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

<jsp:include page="../Index/Index.jsp" />


<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/searchEngine.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<table id="lyf">
    <tr>
        <th>Heiti lyfs</th>
        <th>Lyfjaform</th>
        <th>Styrkleiki</th>
        <th>Innihald lyfs</th>
        <th>Afgreiðslutilhögun</th>
        <th>Markaðsleyfi útgefið</th>
        <th>Markaðsett</th>
        <th>ýmsar upplýsingar</th>
    </tr>
    <tr>
        <td>${name}</td>
        <td>${form}</td>
        <td>${strength}</td>
        <td>${ingredient}</td>
        <td>${service}</td>
        <td>${marketedTime}</td>
        <td>${marketed}</td>
        <td>${otherInfo}</td>
    </tr>
    
</table>

</body>
</html>