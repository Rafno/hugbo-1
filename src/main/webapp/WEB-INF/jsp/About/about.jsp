<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>App�teki�</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/about.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<h2>Um Verkefni�</h2>
<div class="aboutUsDiv">
<p> Verkefni eftir Helga, Hinrik og Rafnar</p>
<p>App�teki� er lyfja leitarv�l skrifu� � Java Spring.<br>
Vefs��an notar Java Spring Security til a� halda utan um �ryggi � innskr�ningu.<br>
    Notendur � App�tekinu geta skr�� sig sem anna�hvort l�knar e�a sj�klingar, sj�klingar geta skr�� � sig lyf.<br>
    Notendur sem eru b��i me� gild netf�ng og eru sta�fest � gagnagrunninum okkar geta fengi� �j�nustu a� senda � sig t�lvup�st. <br>
    Notendur geta vali� hven�r �eir f� �ennan t�lvup�st og geta vali� a� f� upp a� fj�ra t�lvup�sta fyrir hver lyf. <br>
    Notendur geta einnig skr�� sig sem l�kna, l�knar geta s�� og skr�� � sig sj�klinga, sem leyfir �eim a� skr� lyf � sj�klinga.
</p>
    <p>
        Gagnagrunnurinn sem vi� notum er Postgres, hann er upphafstilltur me� skriftu sem keyrir � byrjun keyrslu sem hle�ur inn �ll lyf � �slandi <br>
        me� s�luleyfi � �slandi. Nokkur lyf � gagnagrunninum okkar hafa hlekk a� pdf skjali hj� S�rlyfjaskr�, �ar � me�al Sobril, Viagra og Concerta. <br>
        Vi� g�tum ekki s�tt hlekkinn fyrir �ll lyfin � gagnagrunninum okkar og �ess vegna �kv��um vi� a� s�na �ess virkni fyrir nokkur s�rvalin lyf. <br>
    </p>
    <p>T�lvup�sta�j�nustan sem vi� notum var handskrifu� af Helga og er skrifu� � Node.js. H�n keyrir � Heroku og �arf a� vera hitu� upp fyrir notkun </p>
</div>
</body>
<footer>Class HBV501G, University of Iceland</footer>
</html>