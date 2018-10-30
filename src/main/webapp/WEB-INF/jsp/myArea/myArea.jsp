<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/myArea.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <h2 class="myHomeHeader">Heimasvæðið mitt</h2>
    <div class = "myAreaLargeContainer">
        <div class="myAreaContainer">
            <div class="imgMyArea">
                ${image}
            </div>
            <form method="post" action = "/myHome" enctype="multipart/form-data" class="myAreaForm" >
                <div class = myAreaChooseFile> <input type="file" name="pic" id="fileChooser"/> </div>
                <div class = myAreaSubmit> <input type="submit" value="Upload" name = "pic" /> </div>
            </form>
        </div>
        <div class = "myAreaTable">
            <table id="lyf" border="1">
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
            </table>
        </div>
    </div>
</body>