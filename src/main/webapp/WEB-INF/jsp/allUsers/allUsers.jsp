<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>Appótekið</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/allUser.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
    <body>
        <h2 class="allUsersHeader">Allir sjúklingar</h2>
    <div class = "allPatientsTable">
        <table id="patient" border="1">
            <tr>
                <th>Dagsetning</th>
                <th>Nafn</th>
                <th>Bæjarfélag</th>
                <th>Postnúmer</th>
                <th>Heimilisfang</th>
                <th>Email</th>
            </tr>
                <c:forEach items="${users}" var="obj">
                    <tr onclick="addPatient('${obj.name}', '${obj.id}')">
                    <td><c:out value="${obj.date}"/></td>
                    <td><c:out value="${obj.name}"/></td>
                    <td><c:out value="${obj.homeTown}"/></td>
                    <td><c:out value="${obj.zipCode}"/></td>
                    <td><c:out value="${obj.homeAddress}"/></td>
                    <td><c:out value="${obj.email}"/></td>
                    </tr>
                </c:forEach>
        </table>
    </div>
    <div id="myModal" class="modal">
        <!-- Þetta er glugginn sem memur ofan á gráa gluggan-->
        <Form class="modal-content" method="post">
            <div class="popUpHead">
                <div class="popUpHeadTitle">Nýr sjúklingur</div>
                <div class="close" id="popUpClosing"onclick="closing()">&times;</div>
            </div >
            <div class="popUpMainContainer">
                <input class="popUpTexti" type="text" name="nafn" id = addPatientQuestion value = "nafn" readonly/>
                <div class="popUpButtonContainer">
                    <div class="declineContainer">
                        <button class="popUpDecline" type="submit" name="decline">Nei</button>
                    </div>
                    <div class="acceptContainer" method = "post" >
                        <button class="popUpAccept" type="submit" id = getId  name="Accept">Já</button>
                    </div>
                </div>
            </div>
        </Form>
    </div>
    <p class="allUserserrors">${error}</p>
<script>
    function addPatient(nafn, id) {
        var modal = document.getElementById('myModal');
        var a = document.getElementById('addPatientQuestion').value = "Vilt þú bæta við " + nafn + " sem notenda?";
        var b = document.getElementById('getId').value = id;
        modal.style.display = "block";
        
        var span = document.getElementById("popUpClosing");
        span.onclick = function () {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>

