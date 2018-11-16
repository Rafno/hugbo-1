<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>Appótekið - Heimasvæði</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/myArea.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/searchEng.css"/>"/>
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
                            )" value="Skoða áminningar">
                    </td>
                </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <!-- Pop up kassinn fyrir notification -->
    <div id="myModalUnregistered" class="modal">
        <!-- Þetta er glugginn sem memur ofan á gráa gluggan-->
        <Form method="POST" action="/myhome" class="reminderForm">
            <div class="modal-content">
                <div class="popUpHeadReminder">
                    <h2 class="popUpHeadTitle" id = "Doctorutgafudagur"></h2>
                    <input type="submit" class="close" id="unregisterdClosing" onclick="closing()" value="&times;">
                </div>
                <div class="amminingaContainer">
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">1.Áminning</h2>
                        <input type="time"  class="Clock" name="time1" id="time1">
                        <input type="button" class="confirmReminderButton"  onclick="butts(1)" id="butt1">
                        <input type="hidden" name="buttonFyrst" id="buttonFyrst" value=""/>
                    </div>
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">2.Áminning</h2>

                        <input type="time" class="Clock"name="time2" id="time2">

                        <input type="button" class="confirmReminderButton"   onclick="butts(2)" id="butt2">
                        <input type="hidden" name="buttonSeckond" id="buttonSeckond" value=""/>
                    </div>
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">3.Áminning</h2>
                        <input type="time"  class="Clock" name="time3" id="time3">
                        <input type="button" class="confirmReminderButton"  onclick="butts(3)" id="butt3">
                        <input type="hidden" name="buttonThird" id="buttonThird" value=""/>
                    </div>
                    <div class="aminingaBox">
                        <h2 class="reminderHeadTitle">4.Áminning</h2>
                        <input type="time" class="Clock" name="time4" id="time4">
                        <input type="button" class="confirmReminderButton" onclick="butts(4)" id="butt4">
                        <input type="hidden" name="buttonFourth" id="buttonFourth" value=""/>
                        <input type="hidden" name="medicineId" id="medicineId"/>
                    </div>
                </div>
            </div>
        </Form>
    </div>

    <form method="post" action = "/myhome" class="deleteAccountButton">
        <input type="submit" class="unSubscribeButton" value="Eyða aðgan"  name = "deleteAccount" onclick="location.href = '/';">
    </form>
</body>
<script>
    function  butts(numb) {
        var responseButton = ""
        var responseBackroundColor = "";
        if(numb == 1){
            if(document.getElementById("butt1").value =="Hætta við") {
                responseBackroundColor = "background-color: green";
                responseButton = "Staðfesta"

            }else{
                responseButton="Hætta við"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt1").value = responseButton;
            document.getElementById("buttonFyrst").value = responseButton;
            document.getElementById("butt1").style = responseBackroundColor;

        }
        if(numb == 2) {
            if (document.getElementById("butt2").value == "Hætta við") {
                document.getElementById("butt2").style = "background-color: red";
                document.getElementById("")
                responseBackroundColor = "background-color: green";
                responseButton = "Staðfesta"

            } else {
                responseButton = "Hætta við"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt2").value = responseButton;
            document.getElementById("buttonSeckond").value = responseButton;
            document.getElementById("butt2").style = responseBackroundColor;
        }

        if(numb == 3) {
            if (document.getElementById("butt3").value == "Hætta við") {
                document.getElementById("butt3").style = "background-color: red";
                responseBackroundColor = "background-color: green";
                responseButton = "Staðfesta"

            } else {
                responseButton = "Hætta við"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt3").value = responseButton;
            document.getElementById("buttonThird").value = responseButton;
            document.getElementById("butt3").style = responseBackroundColor;
        }

        if(numb == 4) {
            if (document.getElementById("butt4").value == "Hætta við") {
                document.getElementById("butt4").style = "background-color: red";
                responseBackroundColor = "background-color: green";
                responseButton = "Staðfesta"

            } else {
                responseButton = "Hætta við"
                responseBackroundColor = "background-color: red";
            }
            document.getElementById("butt4").value = responseButton;
            document.getElementById("buttonFourth").value = responseButton;
            document.getElementById("butt4").style = responseBackroundColor;
        }

    }
    function togglerer(name, id,hour1,hour2,hour3,hour4,enable1,enable2,enable3,enable4){
        
        var a = hour1.substr(0,2);
        var b = hour1.substr(3,5);
        console.log(hour1.substr(3,5));
        
        document.getElementById("Doctorutgafudagur").innerHTML = "Setja áminningu á "+name;
        document.getElementById("medicineId").value = id;
        document.getElementById("time1").value = hour1.substr(3,5);
        document.getElementById("time2").value = hour2.substr(3,5);
        document.getElementById("time3").value = hour3.substr(3,5);
        document.getElementById("time4").value = hour4.substr(3,5);


        if (enable1){
            alert("enable1");
            document.getElementById("butt1").value = "Staðfesta";
            document.getElementById("buttonFyrst").value = "Staðfesta";
        }

        else{
            document.getElementById("butt1").value = "Hætta við";
            document.getElementById("buttonFyrst").value = "Hætta við";
        }
        if (enable2){
            document.getElementById("butt2").value = "Staðfesta";
            document.getElementById("buttonSeckond").value = "Staðfesta";
        }
        else{
            document.getElementById("butt2").value = "Hætta við";
            document.getElementById("buttonSeckond").value = "Hætta við";
        }
        if (enable3){
            document.getElementById("butt3").value = "Staðfesta";
            document.getElementById("buttonThird").value = "Staðfesta";
        }
        else{
            document.getElementById("butt3").value = "Hætta við";
            document.getElementById("buttonThird").value = "Hætta við";
        }
        if (enable4){
            document.getElementById("butt4").value = "Staðfesta";
            document.getElementById("buttonFourth").value = "Staðfesta";
        }
        else{
            document.getElementById("butt4").value = "Hætta við";
            document.getElementById("buttonFourth").value = "Hætta við";
        }

        openNotification()


    }
    function openNotification() {
        var myModalUnregistered = document.getElementById('myModalUnregistered');
        // Ná í span elementið sem lokar modelinu
        myModalUnregistered.style.display = "block";

        var span = document.getElementById("unregisterdClosing");
        span.onclick = function() {
            myModalUnregistered.style.display = "none";
            postForm();
            
        }
    }
    function postForm(){
        var form = document.createElement("form");
        var element1 = document.createElement("input");
        var element2 = document.createElement("input");

        form.method = "POST";

        element2.value="bb";
        element2.name="pic";
        form.appendChild(element1);
        
        element1.value="bb";
        element1.name="time1";
        form.appendChild(element1);


        document.body.appendChild(form);

        form.submit();
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
    /*Stíla takkana í reminder*/
    .confirmReminderButton{
        font-size: 1.6rem;
        margin-left: 20%;
        margin-bottom: 10%;
        color: white;
        background-color: red;
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
        font-size: 1.8rem;
        margin-bottom: 10%;
        margin-top: 10%;
    }
    .reminderHeadTitle {
        margin-left: 10%;
        font-weight: bold;
    }
</style>