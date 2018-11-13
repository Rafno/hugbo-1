<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>App�teki� - Heimasv��i</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/myArea.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/searchEngine.css"/>"/>
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
                    <th>Sendu m�r �minningu</th>
                </tr>
                <tr>

                <c:forEach items="${medicine}" var="obj">
                    <td><c:out value="${obj.name}"/></td>
                    <td><c:out value="${obj.pharmaceutical_form}"/></td>
                    <td><c:out value="${obj.strength}"/></td>
                    <td><label class="switch">
                        <input type="checkbox" id="toggler" checked onclick="togglerer()">
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
</body>
<script>
    function togglerer(){

        
        var a = document.getElementById("toggler").value;
        alert(a)
        if(a == 'on'){
            openNotification()
        }else{
            alert("s�rst ��");
        }


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