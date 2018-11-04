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
        <p class="headerTxt">  Lyfjask�purinn heima hj� M�mmu �inni</p>
        <div class = "lowerBanner">
            <Form method="post">
                <label>
                    <input type="text" value="${leita}" class="searchTextArea" name="search"/>
                    <input type="hidden" name="nafn" value=""/>
                    <input type="hidden" name ="styrkur" value=""/>
                    <input type="hidden" name ="lyfjaform"  value=""/>
                    <input type="hidden" name ="utgafudagur" value=""/>
                    <param name="search" value="">
                    <param name="nafn" value="">
                    <param name="search" value="">
                    <param name="nafn" value="">
                    <param name="styrkur" value="">
                    <param name="lyfjaform" value="">
                    <param name="utgafudagur" value="">
                </label>
            </Form>
        </div>

    </div>
    <div class="tafla">         
        <table class="lyfid" id="lyf" border="1">
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
                <p>${userLoggedInn} Notandi</p>
                <p>${doctor} L�knir</p>
                <c:forEach items="${medicine}" var="obj">
                    <c:if test="${not empty userLoggedInn}">
                        <c:if test="${not empty doctor}">
                            <tr onclick="openPopUpDoctor('${obj.name}','${obj.strength}','${obj.pharmaceutical_form}','${obj.ma_issued}')">
                        </c:if>
                        <c:if test="${empty doctor}">
                            <tr onclick="openPopUpPatient('${obj.name}','${obj.strength}','${obj.pharmaceutical_form}','${obj.ma_issued}')">
                        </c:if>
                    </c:if >
                    <c:if test="${empty userLoggedInn}">
                        <tr onclick="openPopUpUnRegistered()">
                    </c:if >
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
    </div>
    <!-- Modal fyrir innskr�� Sj�klinga-->
    <div id="myModal" class="modal">
        <!-- �etta er glugginn sem memur ofan � gr�a gluggan-->
        <Form class="modal-content" method="post">
            <div class="popUpHead">
                <h2 class="popUpHeadTitle">B�ta lyf � lyfjask�p</h2>
                <div class="close">&times;</div>
            </div>
            <div class="popUpTextContainer">
                <input class="popUpTexti" type="text" name="nafn" id="nafn" value = "nafn" readonly/>
                <input class="popUpTexti"type="text" name="styrkur" id="styrkur" readonly/>
                <input class="popUpTexti"type="text" name="lyfjaform" id="lyfjaform" readonly/>
                <input class="popUpTexti"type="text" name="utgafudagur" id="utgafudagur" readonly/>
            </div>
            <button class="popUpSubmit" type="submit" name="search">Sta�festa</button>
        </Form>
    </div>
    <!-- Model fyril innskr��a l�kna -->
    <div id="DocorMyModal" class="modal">
        <!-- �etta er glugginn sem memur ofan � gr�a gluggan-->
        <Form class="modal-content" method="post">
            <div class="popUpHead">
                <h2 class="popUpHeadTitle">B�ta lyf � sj�kling</h2>
                <div class="close">&times;</div>
            </div>
            <div class="doctorePopUpContainer">
                <div class="popUpTextContainer">
                    <input class="popUpTexti" type="text" name="nafn" id="Doctornafn" value = "nafn" readonly/>
                    <input class="popUpTexti"type="text" name="styrkur" id="Doctorstyrkur" readonly/>
                    <input class="popUpTexti"type="text" name="lyfjaform" id="Doctorlyfjaform" readonly/>
                    <input class="popUpTexti"type="text" name="utgafudagur" id="Doctorutgafudagur" readonly/>
                </div>
                <div class="DoctorSelectOptionsContainer">
                    <p>hehe</p>
                </div>
            </div>
            <button class="popUpSubmit" type="submit" name="search">Sta�festa</button>
        </Form>
    </div>
    <!-- Modeal fyrir ekki innskr��a notendur-->
    <div id="myModalUnregistered" class="modal">
        <!-- �etta er glugginn sem memur ofan � gr�a gluggan-->
        <div class="modal-content">
            <div class="popUpHead">
                <h2 class="popUpHeadTitle">�innskr��ur notandi</h2>
                <div class="close">&times;</div>
            </div>
            <div class="popUpTextContainer">
                <div> �� ert ekki innskr��ur notandi</div>
                <div> �a� er fr�tt a� skr� sig inn og �� geriri �a� <a href="register">h�rna</a></div>
            </div>
        </div>
    </div>


<script>
    function openPopUpPatient(nafn,styrkur,lyfjaform,utgafudagur){
        // n�r � m�deli�
        var modal = document.getElementById('myModal');
        var b = document.getElementById('nafn').value = "Nafn Lyfsins: "+nafn;
        var c = document.getElementById('styrkur').value = "styrkur: "+styrkur;
        var d = document.getElementById('lyfjaform').value = "lyfjaform: "+lyfjaform;
        var e = document.getElementById('utgafudagur').value = "�tg�fudagur: "+utgafudagur;

        // N� � span elementi� sem lokar modelinu
        modal.style.display = "block";

        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() {
            modal.style.display = "none";
        }
    }

    function openPopUpDoctor(nafn,styrkur,lyfjaform,utgafudagur){
        // n�r � m�deli�
        var modal = document.getElementById('DocorMyModal');
        var b = document.getElementById('Doctornafn').value = "Nafn Lyfsins: "+nafn;
        var c = document.getElementById('Doctorstyrkur').value = "styrkur: "+styrkur;
        var d = document.getElementById('Doctorlyfjaform').value = "lyfjaform: "+lyfjaform;
        var e = document.getElementById('Doctorutgafudagur').value = "�tg�fudagur: "+utgafudagur;

        // N� � span elementi� sem lokar modelinu
        modal.style.display = "block";

        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() {
            modal.style.display = "none";
        }
    }

    function openPopUpUnRegistered() {
        var myModalUnregistered = document.getElementById('myModalUnregistered');
        // N� � span elementi� sem lokar modelinu
        myModalUnregistered.style.display = "block";

        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() {
            myModalUnregistered.style.display = "none";
        }
    }
</script>
</body>
</html>
