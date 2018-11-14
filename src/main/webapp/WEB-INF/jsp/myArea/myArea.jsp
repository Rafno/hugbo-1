<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>Appótekið - Heimasvæði</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/myArea.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/searchEngine.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/checkBox.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <h2 class="myHomeHeader">Lyfjaskápurinn minn</h2>
    <div class = "myAreaLargeContainer">
        <div class="myAreaContainer">
            <div class="imgMyArea">
                ${image}
            </div>
            <form method="post" action = "/myHome" enctype="multipart/form-data" class="myAreaForm" >
                <div class = myAreaChooseFile> <input type="file" onchange="this.form.submit()" name="pic" id="fileChooser"/> </div>
            </form>
        </div>
        <div class = "myAreaTable">
            <c:if test="${not empty patients}">
                <table id="lyf" border="1">
                    <tr>
                        <th>${role}</th>
                    </tr>
                    <tr>
                    <c:forEach var="pat" items="${patients}">
                        <td><c:out value="${pat.name}"/></td>
                    </tr>
                </c:forEach>
                </table>
            </c:if>
            <table id="lyf" border="1">
                <tr>
                    <th>Heiti lyfs</th>
                    <th>Lyfjaform</th>
                    <th>Styrkleiki</th>
                    <th>Sendu mér áminningu</th>
                </tr>
                <tr>

                <c:forEach items="${medicine}" var="obj">
                    <td><c:out value="${obj.name}"/></td>
                    <td><c:out value="${obj.pharmaceutical_form}"/></td>
                    <td><c:out value="${obj.strength}"/></td>
                    <td><label class="switch">
                        <input type="checkbox" id="toggler" checked onclick="togglerer('${obj.name}')">
                        <span class="slider round"></span>
                    </label>
                    </td>
                </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <!-- Pop up kassinn fyrir notification -->
    <div id="myModalUnregistered" class="modal">
        <!-- Þetta er glugginn sem memur ofan á gráa gluggan-->
        <div class="modal-content">
            <div class="popUpHeadReminder">
                <h2 class="popUpHeadTitle" id = "Doctorutgafudagur"></h2>
                <div class="close" id="unregisterdClosing" onclick="closing()">&times;</div>
            </div>
            <div class="amminingaContainer">
                <div class="aminingaBox">
                    <h2 class="reminderHeadTitle">1.Ámining</h2>
                    <input type="time" value="18:00" class="Clock">
                    <button class="confirmReminderButton">Staðfest</button>
                </div>
                <div class="aminingaBox">
                    <h2 class="reminderHeadTitle">2.Ámining</h2>
                    <input type="time" value="18:00" class="Clock">
                    <button class="confirmReminderButton">Staðfest</button>
                </div>
                <div class="aminingaBox">
                    <h2 class="reminderHeadTitle">3.Ámining</h2>
                    <input type="time" value="18:00" class="Clock">
                    <button class="confirmReminderButton">Staðfest</button>
                </div>
                <div class="aminingaBox">
                    <h2 class="reminderHeadTitle">4.Ámining</h2>
                    <input type="time" value="18:00" class="Clock">
                    <button class="confirmReminderButton">Staðfest</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    function togglerer(name){


        var a = document.getElementById("toggler").value;
        document.getElementById("Doctorutgafudagur").innerHTML = "Setja áminingu á "+name;

        openNotification()


    }
    function openNotification() {
        var myModalUnregistered = document.getElementById('myModalUnregistered');
        // Ná í span elementið sem lokar modelinu
        myModalUnregistered.style.display = "block";

        var span = document.getElementById("unregisterdClosing");
        span.onclick = function() {
            myModalUnregistered.style.display = "none";
            alert("hello");
            
        }
    }
    function closing(){

    }
</script>
<style>
    /*Pop up container fyrir reminder*/
    .amminingaContainer {
        display: flex;
        flex-direction: row;
        width: 100%;
        background-color: slategrey;
        justify-content: center;
        padding-top: 6%;
        border: 1px solid black;
    }
    .aminingaBox {
        width: 29%;
        background-color: white;
        margin-left: 1%;
        margin-right: 1%;
        border: 3px solid black;
        border-radius: 4px;
        justify-content: center;
        min-height: 100%;
        margin-bottom: 2%;
        border-radius: 4px;
    }
    /*Stíla takkana í reminder*/
    .confirmReminderButton{
        font-size: 1.6rem;
        margin-left: 20%;
        margin-bottom: 10%;
        color: white;
        background-color: black;
    }
    /*Head container í pop up reminder*/

    .popUpHeadReminder{
        display: flex;
        flex-direction: row;
        background-color: darkgreen;
        justify-content: center;
        border: 1px solid black;
    }
    .Clock {
        font-size: 2rem;
        margin-bottom: 10%;
        margin-top: 10%;
        margin-left:5%;
    }
    .reminderHeadTitle {
        margin-left: 10%;
        font-weight: bold;
    }
</style>