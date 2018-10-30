<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

<jsp:include page="../headNavigation/headNavigation.jsp" />
<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../css/searchEngine.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="../css/index.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <div class="lowerBanner_container">
        <p class="headerTxt">  Lyfjaskápurinn heima hjá þér</p>
        <div class = "lowerBanner">
            <Form method="post">
                <label>
                    <input type="text" value="${leita}" class="searchTextArea" name="search"/>
                    <param name="search" value="">
                </label>
            </Form>
        </div>

    </div>
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
        <c:forEach items="${medicine}" var="obj">
            <tr onclick="openPopUp()">
                <td><c:out value="${obj.name}"/></td>
                <td><c:out value="${obj.pharmaceutical_form}"/></td>
                <td><c:out value="${obj.strength}"/></td>
                <td><c:out value="${obj.active_ingredient}"/></td>
                <td><c:out value="${obj.legal_status}"/></td>
                <td><c:out value="${obj.ma_issued}"/></td>
                <td><c:out value="${obj.marketed}"/></td>
                <td><c:out value="${obj.other_info}"/></td>
            </tr>
        </c:forEach>
    </table>
    <div id="myModal" class="modal">
        <!-- Þetta er glugginn sem memur ofan á gráa gluggan-->
        <Form class="modal-content" method="post">
            <div class="popUpHead">
                <h2 class="popUpHeadTitle">Bæta lyf í lyfjaskáp</h2>
                <div class="close">&times;</div>
            </div>
            <div class="popUpTextContainer">
                <div class="popUpText"><b>Nafn Lyfstins:</b> Atomoxetin Medical Valley</div>
                <div class="popUpText"><b>Styrkur :</b> 200 mg</div>
                <div class="popUpText"><b>Lyfjaform:</b> Tafla</div>
                <div class="popUpText"><b>Afgreiðslutilhögun:</b>(R Z) Sérfræðingsmerkt (og lyfseðilsskylt) </div>
            </div>
            <button class="popUpSubmit" type="submit" name="search">Staðfesta</button>
            <param name="search" value="${search}">
        </Form>
    </div>
</body>
</html>

<script>
    function openPopUp(){
        // nær í módelið
        var modal = document.getElementById('myModal');

        // Ná í span elementið sem lokar modelinu
        modal.style.display = "block";

        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() {
            modal.style.display = "none";
        }
    }
</script>
