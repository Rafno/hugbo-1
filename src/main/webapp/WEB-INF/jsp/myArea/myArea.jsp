<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />
<!-- ROutid herna er '/myHome' og �etta html skjal er fyrir heimasv��i� �itt b��i sem dr og patient. H�r getur� sett �miningar � �ig
     sme sj�kligur og sem l�knir getur�u fylgst me� sj�klingunum ��num.-->
<head>
    <title>App�teki� - Heimasv��i</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/myArea.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/searchEng.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <h2 class="myHomeHeader">Lyfjask�purinn minn</h2>
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
                        <th>B�jarf�lag</th>
                        <th>Heimilisfang</th>
                        <th>P�stn�mer</th>
                        <th>Heimsv��i</th>
                        <th>Me� gildan t�lvup�st</th>
                    </tr>
                    <tr>
                    <c:forEach var="pat" items="${patients}">
                        <td><c:out value="${pat.name}"/></td>
                        <td><c:out value="${pat.homeTown}"/></td>
                        <td><c:out value="${pat.homeAddress}"/></td>
                        <td><c:out value="${pat.zipCode}"/></td>
                        <td><a href="myHome/${pat.id}">Fara � heimasv��i</a></td>
                        <td><c:out value="${pat.confirmed}"/></td>
                    </tr>
                </c:forEach>
                </table>
            </c:if>
            <c:if test="${not empty reminderMeds}">
            <table id="lyf" border="1">
                <tr>
                    <th>Heiti lyfs</th>
                    <th>Lyfjaform</th>
                    <th>Styrkleiki</th>
                    <th>Sendu m�r �minningu</th>
                </tr>
                <tr>

                <c:forEach items="${reminderMeds}" var="obj">
                    <td><c:out value="${obj.name}"/></td>
                    <td><c:out value="${obj.pharmaceutical_form}"/></td>
                    <td><c:out value="${obj.strength}"/></td>
                    <td>
                        <input type="button" id="toggler" checked onclick="togglerer(
                            '   ${obj.name}',
                            '   ${obj.medicineId}',
                            '   ${obj.hour1}',
                            '   ${obj.hour2}',
                            '   ${obj.hour3}',
                            '   ${obj.hour4}',
                            '   ${obj.enable1}',
                            '   ${obj.enable2}',
                            '   ${obj.enable3}',
                            '   ${obj.enable4}'
                            )" value="Sko�a �minningar">
                    </td>
                </tr>
                </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
    <!-- Pop up kassinn fyrir notification -->
    <div id="myModalUnregistered" class="modal">
        <!-- �etta er glugginn sem memur ofan � gr�a gluggan-->
        <c:if test="${not empty patientId}">
            <Form method="POST" action="/myhome/${patientId}" class="reminderForm">
        </c:if>
        <c:if test="${empty patientId}">
            <Form method="POST" action="/myhome" class="reminderForm">
        </c:if>
            <div class="modal-content">
                <div class="popUpHeadReminder">
                    <input type="submit" class="close" id="unregisterdClosing" value="&times;">
                    <h2 class="popUpHeadTitle" id = "Doctorutgafudagur"></h2>
                </div>
                <div class="amminingaContainer">
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">1.�minning</h2>
                        <input type="time"  class="Clock" name="time1" id="time1">
                        <input type="button" class="confirmReminderButton"  onclick="butts(1)" id="butt1">
                        <input type="hidden" name="buttonFyrst" id="buttonFyrst" value=""/>
                    </div>
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">2.�minning</h2>

                        <input type="time" class="Clock"name="time2" id="time2">

                        <input type="button" class="confirmReminderButton"   onclick="butts(2)" id="butt2">
                        <input type="hidden" name="buttonSeckond" id="buttonSeckond" value=""/>
                    </div>
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">3.�minning</h2>
                        <input type="time"  class="Clock" name="time3" id="time3">
                        <input type="button" class="confirmReminderButton"  onclick="butts(3)" id="butt3">
                        <input type="hidden" name="buttonThird" id="buttonThird" value=""/>
                    </div>
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">4.�minning</h2>
                        <input type="time" class="Clock" name="time4" id="time4">
                        <input type="button" class="confirmReminderButton" onclick="butts(4)" id="butt4">
                        <input type="hidden" name="buttonFourth" id="buttonFourth" value=""/>
                        <input type="hidden" name="medicineId" id="medicineId"/>
                    </div>
                </div>
            </div>
        </Form>
    </div>

    <form method="GET" action = "/deletemyuserrightnow" class="deleteAccountButton">
        <input type="submit" class="unSubscribeButton" value="Ey�a a�gangi">

    </form>
</body>
<script>

    function  butts(numb) {
        var responseButton = ""
        var responseBackroundColor = "";
        if(numb == 1){
            if(document.getElementById("butt1").value =="H�tta vi�") {
                responseBackroundColor = "background-color: green";
                responseButton = "Sta�festa"

            }else{
                responseButton="H�tta vi�"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt1").value = responseButton;
            document.getElementById("buttonFyrst").value = responseButton;
            document.getElementById("butt1").style = responseBackroundColor;

        }
        if(numb == 2) {
            if (document.getElementById("butt2").value == "H�tta vi�") {
                document.getElementById("butt2").style = "background-color: red";
                document.getElementById("")
                responseBackroundColor = "background-color: green";
                responseButton = "Sta�festa"

            } else {
                responseButton = "H�tta vi�"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt2").value = responseButton;
            document.getElementById("buttonSeckond").value = responseButton;
            document.getElementById("butt2").style = responseBackroundColor;
        }

        if(numb == 3) {
            if (document.getElementById("butt3").value == "H�tta vi�") {
                document.getElementById("butt3").style = "background-color: red";
                responseBackroundColor = "background-color: green";
                responseButton = "Sta�festa"

            } else {
                responseButton = "H�tta vi�"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt3").value = responseButton;
            document.getElementById("buttonThird").value = responseButton;
            document.getElementById("butt3").style = responseBackroundColor;
        }

        if(numb == 4) {
            if (document.getElementById("butt4").value == "H�tta vi�") {
                document.getElementById("butt4").style = "background-color: red";
                responseBackroundColor = "background-color: green";
                responseButton = "Sta�festa"

            } else {
                responseButton = "H�tta vi�"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt4").value = responseButton;
            document.getElementById("buttonFourth").value = responseButton;
            document.getElementById("butt4").style = responseBackroundColor;
        }

    }
    function togglerer(name, id,hour1,hour2,hour3,hour4,enable1,enable2,enable3,enable4){
        /*var a = hour1.substr(0,2);
        var b = hour1.substr(3,5);
        console.log(hour1.substr(3,5));
        */
        document.getElementById("Doctorutgafudagur").innerHTML = "Setja �minningu � "+name;
        document.getElementById("medicineId").value = id;
        document.getElementById("time1").value = hour1.substr(3,5);
        document.getElementById("time2").value = hour2.substr(3,5);
        document.getElementById("time3").value = hour3.substr(3,5);
        document.getElementById("time4").value = hour4.substr(3,5);

        // Fyrst t�minn
        if (enable1.length == 7){
            document.getElementById("butt1").value = "Sta�festa";
            document.getElementById("butt1").style.backgroundColor = "green";
            document.getElementById("buttonFyrst").value = "Sta�festa";
        }
      

        else{
            document.getElementById("butt1").value = "H�tta vi�";
            document.getElementById("buttonFyrst").value = "H�tta vi�";
            document.getElementById("butt1").style.backgroundColor = "red";
        }
        // annat t�minn
        if (enable2.length == 7){
            document.getElementById("butt2").value = "Sta�festa";
            document.getElementById("butt2").style.backgroundColor = "green";
            document.getElementById("buttonSeckond").value = "Sta�festa";
        }
        else{
            document.getElementById("butt2").value = "H�tta vi�";
            document.getElementById("butt2").style.backgroundColor = "red";
            document.getElementById("buttonSeckond").value = "H�tta vi�";
        }
        //�ri�ji t�minn
        if (enable3.length == 7){
            document.getElementById("butt3").value = "Sta�festa";
            document.getElementById("butt3").style.backgroundColor = "green";
            document.getElementById("buttonThird").value = "Sta�festa";
        }
        else{
            document.getElementById("butt3").value = "H�tta vi�";
            document.getElementById("butt3").style.backgroundColor = "red";
            document.getElementById("buttonThird").value = "H�tta vi�";
        }
        // fj�r�i t�minn
        if (enable4.length == 7){
            document.getElementById("butt4").style.backgroundColor = "green";
            document.getElementById("butt4").value = "Sta�festa";
            document.getElementById("buttonFourth").value = "Sta�festa";
        }
        else{
            document.getElementById("butt4").value = "H�tta vi�";
            document.getElementById("butt4").style.backgroundColor = "red";
            document.getElementById("buttonFourth").value = "H�tta vi�";
        }

        openNotification()


    }
    function openNotification() {
        var myModalUnregistered = document.getElementById('myModalUnregistered');
        // N� � span elementi� sem lokar modelinu
        myModalUnregistered.style.display = "block";

        var span = document.getElementById("unregisterdClosing");
        span.onclick = function() {
            myModalUnregistered.style.display = "none";
        }
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
        width: 27%;
        background-color: white;
        border: 3px solid black;
        border-radius: 4px;
        justify-content: center;
        min-height: 100%;
        margin-bottom: 2%;
        border-radius: 4px;
    }
    /*St�la takkana � reminder*/
    .confirmReminderButton{
        font-size: 1.6rem;
        margin-left: 20%;
        margin-bottom: 10%;
        color: white;
        background-color: red;
    }
    /*Head container � pop up reminder*/

    .popUpHeadReminder{
        display: flex;
        flex-direction: row;
        background-color: darkgreen;
        justify-content: center;
        border: 1px solid black;
    }
    .Clock {
        font-size: 1.8rem;
        margin-bottom: 10%;
        margin-top: 10%;
    }
    .reminderHeadTitle {
        margin-left: 10%;
        font-weight: bold;
    }
    .myHomeHeader{
        display: flex;
        justify-content: center;
        font-size: 2rem;
    }
    .unSubscribeButton{
        background-color: red;
        color: white;
        font-size: 1.4rem;
        border-radius: 9px;
    }
    .myAreaTable{
        margin-left: -6%;
    }
</style>