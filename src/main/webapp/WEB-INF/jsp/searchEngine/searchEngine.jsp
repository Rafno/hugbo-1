<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<!-- �etta skjal er post � leitarv�l h�r assignaru lyf � �ig ef �� ert sj�klingur og ef �� ert dr �� getur�u sett lyf � sj�klinginn.   -->
<jsp:include page="../headNavigation/headNavigation.jsp" />
<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../css/searchEng.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="../css/index.css"/>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
    <div class="lowerBanner_container">
        <p class="headerTxt">  Lyfjask�purinn heima hj� ��r</p>
        <div class = "lowerBanner">
            <Form method="post" action="/?page=0page=50" >
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
                <c:set var = "counter" scope = "session" value = "${0}"/>
                <c:forEach items="${medicine}" var="obj">
                    <c:if test="${starting <= Integer.parseInt(counter) and ending >= Integer.parseInt(counter) }">
                        <!-- H�r �arf a� skilgreina starting og endasta�-->
                        <c:if test="${not empty userLoggedInn}">
                            <c:if test="${not empty doctor}">
                                <tr onclick="openPopUpDoctor('${obj.name}','${obj.strength}','${obj.pharmaceutical_form}','${obj.ma_issued}','${obj.pdfLink}','${obj.id}')">
                            </c:if>
                            <c:if test="${empty doctor}">
                                <tr onclick="openPopUpPatient('${obj.name}','${obj.strength}','${obj.pharmaceutical_form}','${obj.ma_issued}','${obj.pdfLink}')">
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
                    </c:if>
                        <c:set var = "counter" scope = "session" value = "${counter+1}"/>
                </c:forEach>
        </table>
    </div>
    <!-- Modal fyrir innskr�� Sj�klinga-->
    <div id="myModal" class="modal">
        <!-- �etta er glugginn sem kemur ofan � gr�a gluggan-->
        <Form class="modal-content" method="post">
            <div class="popUpHead">
                <h2 class="popUpHeadTitle">B�ta lyf � lyfjask�p</h2>
                <div class="close" id="patientClose">&times;</div>
            </div>
            <div class="popUpTextContainer">
                <input class="popUpTexti" type="text" name="nafn" id="nafn" value = "nafn" readonly/>
                <input class="popUpTexti"type="text" name="styrkur" id="styrkur" readonly/>
                <input class="popUpTexti"type="text" name="lyfjaform" id="lyfjaform" readonly/>
                <input class="popUpTexti"type="text" name="utgafudagur" id="utgafudagur" readonly/>
                <a class="popUpTexti" id="pdfLink" name="pdfLink"></a>
            </div>
            <button class="popUpSubmit" type="submit" name="search">Sta�festa</button>
        </Form>
    </div>
    <!-- Model fyril innskr��a l�kna -->
    <div id="DocorMyModal" class="modalDoctor">
        <!-- �etta er glugginn sem memur ofan � gr�a gluggan-->
        <Form class="modal-content" method="post">
            <div class="popUpHead" name = "search" value = "">
                <h2 class="popUpHeadTitle"> B�ta lyf � sj�kling</h2>
                <div class="close"  id="doctorClose">&times;</div>
            </div>
            <div class="doctorPopUpContainer">
                <div class="popUpTextContainer" id="container">
                    <input class="popUpTexti" type="text" name="nafn" id="Doctornafn" value = "nafn" readonly/>
                    <input class="popUpTexti"type="text" name="styrkur" id="Doctorstyrkur" readonly/>
                    <input class="popUpTexti"type="text" name="lyfjaform" id="Doctorlyfjaform" readonly/>
                    <input class="popUpTexti"type="text" name="utgafudagur" id="Doctorutgafudagur" readonly/>
                    <a class="popUpTexti" id="pdfLink" name="pdfLink"></a>
                </div>
                <div class="DoctorSelectOptionsContainer">
                    <select class="optionPannelAddMedToUser" onChange="myFunction(this.options[this.selectedIndex].value)" name = "userId">
                        <c:forEach items="${patients}" var="obj">
                            <option value="${obj.id}">  ${obj.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="acceptContainer" method = "post" >
                <button class="popUpSubmitDoctor" type="submit" id = "getMed" name="medId">Sta�festa</button>
            </div>
        </Form>
    </div>
    <!-- Modeal fyrir ekki innskr��a notendur-->
    <div id="myModalUnregistered" class="modal">
        <!-- �etta er glugginn sem memur ofan � gr�a gluggan-->
        <div class="modal-content">
            <div class="popUpHead">
                <h2 class="popUpHeadTitle">�innskr��ur notandi</h2>
                <div class="close" id="unregisterdClosing"onclick="closing()">&times;</div>
            </div>
            <div class="popUpTextContainer">
                <div> �� ert ekki innskr��ur notandi</div>
                <div> �a� er fr�tt a� skr� sig inn og �� gerir �a� me� a� smella <a href="register">h�r</a></div>
            </div>
        </div>
    </div>
    <div class="pagingContainer">
        <h4> ${integer.parseInt(finalCounter+1)}</h4>
        <c:if test="${page>= 1}">
            <Form method="post"  action ="http://localhost:8080?page=${Integer.parseInt(starting)-50}page=${Integer.parseInt(ending)-50}" class="backButton">
                    <input type="submit" value="Aftur � bak" id ="backButton">
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
            </Form>
        </c:if>
        <h4>S��a n�mer ${Integer.parseInt(page)} </h4>
        <c:if test="${Integer.parseInt(counter) >= Integer.parseInt(page)}"><h1>Skrifa�ur eitthva� helv�tis mellan ��n e�a �g fkn geng fr� ��r helv..... jspfkn skjal</h1></c:if>
        <Form method="post" action="http://localhost:8080?page=${Integer.parseInt(starting)+50}page=${Integer.parseInt(ending)+50}" class="forwardButton">
                <input type="submit" value="�fram" id="forwardButton">
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
        </Form>
    </div>

    <h1>${counter}</h1>
<script>
    function openPopUpPatient(nafn,styrkur,lyfjaform,utgafudagur,pdfLink){
        var modal = document.getElementById('myModal');
        var b = document.getElementById('nafn').value = "Nafn Lyfsins: "+nafn;
        var c = document.getElementById('styrkur').value = "styrkur: "+styrkur;
        var d = document.getElementById('lyfjaform').value = "lyfjaform: "+lyfjaform;
        var e = document.getElementById('utgafudagur').value = "�tg�fudagur: "+utgafudagur;
        if(pdfLink){
            document.getElementById('pdfLink').text = "S�rlyfjaskr�";
            document.getElementById('pdfLink').href = pdfLink;
        }
        // n�r � m�deli�
        // N� � span elementi� sem lokar modelinu
        modal.style.display = "block";

        var span = document.getElementById("patientClose");
        span.onclick = function() {
            modal.style.display = "none";
        }
    }

    function openPopUpDoctor(nafn,styrkur,lyfjaform,utgafudagur,pdfLink,medId){
        // n�r � m�deli�
        var modal = document.getElementById('DocorMyModal');
        var b = document.getElementById('Doctornafn').value = "Nafn Lyfsins: "+nafn;
        var c = document.getElementById('Doctorstyrkur').value = "styrkur: "+styrkur;
        var d = document.getElementById('Doctorlyfjaform').value = "lyfjaform: "+lyfjaform;
        var e = document.getElementById('Doctorutgafudagur').value = "�tg�fudagur: "+utgafudagur;
        var g = document.getElementById('getMed').value = medId;
        if(pdfLink){
            document.getElementById('pdfLink').text = "S�rlyfjaskr�";
            document.getElementById('pdfLink').href = pdfLink;
        }
        // N� � span elementi� sem lokar modelinu
        modal.style.display = "block";

        var span = document.getElementById("doctorClose");
        span.onclick = function() {
            modal.style.display = "none";
        }
    }

    function openPopUpUnRegistered() {
        var myModalUnregistered = document.getElementById('myModalUnregistered');
        // N� � span elementi� sem lokar modelinu
        myModalUnregistered.style.display = "block";

        var span = document.getElementById("unregisterdClosing");
        span.onclick = function() {
            myModalUnregistered.style.display = "none";
        }
    }
    function closing() {
        alert("closing")
        var myModalUnregistered = document.getElementById('myModalUnregistered');
        var span = document.getElementsByClassName("close")[0];
        myModalUnregistered.style.display = "block";
        span.onclick = function() {
            alert("�inniskr��ur")
           // myModalUnregistered.style.display = "block";
            myModalUnregistered.style.display = "none";
        }
    }
</script>
</body>
</html>
<style>
    .pagingContainer{
        display: flex;
        flex-direction: row;
        font-size: 0.8rem;
        justify-content: center;
        margin-top: 1%;
        margin-bottom: 1%;
    }
    .backButton{
        padding-right: 3%;
    }
    .forwardButton{
        padding-left: 3%;
    }
    #forwardButton{
        background-color: red;
        color: white;
    }
    #backButton{
        background-color: green;
        color: white;
    }
</style>