<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>Appótekið</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/about.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
<h2>Um Verkefnið</h2>
<div class="aboutUsDiv">
<p> Verkefni eftir Helga, Hinrik og Rafnar</p>
<p>Appótekið er lyfja leitarvél skrifuð í Java Spring.<br>
Vefsíðan notar Java Spring Security til að halda utan um öryggi á innskráningu.<br>
    Notendur á Appótekinu geta skráð sig sem annaðhvort læknar eða sjúklingar, sjúklingar geta skráð á sig lyf.<br>
    Notendur sem eru bæði með gild netföng og eru staðfest í gagnagrunninum okkar geta fengið þjónustu að senda á sig tölvupóst. <br>
    Notendur geta valið hvenær þeir fá þennan tölvupóst og geta valið að fá upp að fjóra tölvupósta fyrir hver lyf. <br>
    Notendur geta einnig skráð sig sem lækna, læknar geta séð og skráð á sig sjúklinga, sem leyfir þeim að skrá lyf á sjúklinga.
</p>
    <p>
        Gagnagrunnurinn sem við notum er Postgres, hann er upphafstilltur með skriftu sem keyrir í byrjun keyrslu sem hleður inn öll lyf á íslandi <br>
        með söluleyfi á Íslandi. Nokkur lyf á gagnagrunninum okkar hafa hlekk að pdf skjali hjá Sérlyfjaskrá, þar á meðal Sobril, Viagra og Concerta. <br>
        Við gátum ekki sótt hlekkinn fyrir öll lyfin í gagnagrunninum okkar og þess vegna ákváðum við að sýna þess virkni fyrir nokkur sérvalin lyf. <br>
    </p>
    <p>Tölvupóstaþjónustan sem við notum var handskrifuð af Helga og er skrifuð í Node.js. Hún keyrir á Heroku og þarf að vera hituð upp fyrir notkun </p>
</div>
</body>
<footer>Class HBV501G, University of Iceland</footer>
</html>