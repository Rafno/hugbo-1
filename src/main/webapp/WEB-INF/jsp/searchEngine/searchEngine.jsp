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
        <p class="headerTxt">  Lyfjask�purinn heima hj� ��r</p>
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
            <th>Afgrei�slutilh�gun</th>
            <th>Marka�sleyfi �tgefi�</th>
            <th>Marka�sett</th>
            <th>�msar uppl�singar</th>
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
        <!-- �etta er glugginn sem memur ofan � gr�a gluggan-->
        <Form class="modal-content" method="post">
            <div class="popUpHead">
                <h2 class="popUpHeadTitle">B�ta lyf � lyfjask�p</h2>
                <div class="close">&times;</div>
            </div>
            <div class="popUpTextContainer">
                <div class="popUpText"><b>Nafn Lyfstins:</b> Atomoxetin Medical Valley</div>
                <div class="popUpText"><b>Styrkur :</b> 200 mg</div>
                <div class="popUpText"><b>Lyfjaform:</b> Tafla</div>
                <div class="popUpText"><b>Afgrei�slutilh�gun:</b>(R Z) S�rfr��ingsmerkt (og lyfse�ilsskylt) </div>
            </div>
            <button class="popUpSubmit" type="submit" name="search">Sta�festa</button>
            <param name="search" value="${search}">
        </Form>
    </div>
</body>
</html>

<script>
    function openPopUp(){
        // n�r � m�deli�
        var modal = document.getElementById('myModal');

        // N� � span elementi� sem lokar modelinu
        modal.style.display = "block";

        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() {
            modal.style.display = "none";
        }
    }
</script>
