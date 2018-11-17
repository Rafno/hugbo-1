<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />
<!-- routi� '/netfangstadfest' tekur vi� query sem er b�tt vi� route me� ? og s��an id eftir �v�
     �esa Id segir hvort notandi hafi gefi� okkur r�tt email. -->
<head>
    <title>App�teki�</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/medicine.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <div class="emailContainer">
        <h2>Til hamingju</h2>
        <h4>Netfangi� �itt hefur veri� sta�fest</h4>
    </div>
</body>
</html>
<style>
    .emailContainer{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translateX(-50%) translateY(-50%);
    }
</style>