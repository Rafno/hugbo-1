<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<!-- routid herna er '/register'. �etta html skjal s�r um register kerfi� og gefur villur ef upp koma villur en annars
     sendir ��r email me� netfangi sem �� gafst okkur og �� �arft a� vikrja �a� til �ess a� geta nota� email �j�nustuna
     okkar. -->
<head>
    <title>App�teki� - n�skr�ning</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/register.css"/>"/>
</head>
    <jsp:include page="../headNavigation/headNavigation.jsp" />
<body>

    <div class="registerHeader">B�a til n�jan notanda</div>
    <form method="post" class = "registeringContainer">
        <div class="registeringUsername">
            <div class="row">
                <h4 class="title" id = registerUserName>Notendanafn</h4>
                <input type="text" placeholder="  Enga �slenska stafi" name="username"  class="registerText"  />
                <param name = "username" value="">
            </div>
            <br />
            <div class="row">
                <h4 class="title">Lykilor�</h4>
                <input type="text" placeholder="  L�gmark 6 stafir" name="password"style="-webkit-text-security: disc;" class="registerText"/>
            </div>
            <br />
            <div class="row">
            <h4 class="title">Sta�festa lykilor�</h4>
            <input type="text" placeholder="  Passa�u a� hafa lykilor�in eins " name="passwordRepeat" style="-webkit-text-security: disc;" class="registerText"/>
            </div>
            <br />
            <div class="row">
            <h4 class="title">Nafn</h4>
            <input type="text" placeholder="  �arf a� fylla �t" name="name" value="${nafn}" required class="registerText" maxlength="40" />
            </div>
            <br />
            <div class="row">
            <h4 class="title">Hlutverk</h4>
            <select class="optionPannelDoctorPatient" onChange="myFunction(this.options[this.selectedIndex].value)" name = "role">
                <option value="USER">    Sj�klingur  </option>
                <option value="DOCTOR">        L�knir          </option>
            </select>
            </div>
            <br />
            <input type="text" value="${succesfull}" id="succesfull" readonly>
            <br />
            <div class="rowButton">
            <input type="submit" value="Sta�festa" id="confirm"/>
            </div>
        </div>
        <div class="registeringHomeAdress">
            <div class="row">
                <h4 class="title">Netfang</h4>
                <input type="text" name="emailAddress"  class="registerText" required/>
                <param name="emailAddress" value="">
            </div>
            <br />
            <div class="row">
                <h4 class="title">Heimilisfang</h4>
                <input type="text" name="homeAddress"  class="registerText"/>
                <param name="homeAddress" value="">
            </div>
            <br />
            <div class="row">
                <h4 class="title">B�jarf�lag</h4>
                <input type="text"  name="homeTown" class="registerText" />
                <param name="homeTown" value="">
            </div>
            <br />
            <div class="row">
                <h4 class="title">P�stn�mer</h4>
                <input type="text" placeholder="  A�eins t�lustafir " name="zipCode" class="registerText" />
                <param name = "zipCode" value = "">
            </div>
            <div class="errors">
                <ul>
                    <c:forEach items="${notendaVillur}" var="obj">
                        <li><c:out value="${obj}"/></li>
                    </c:forEach>
                </ul>
            </div>
            <br />
            <div class="errors">
                <ul>
                    <c:forEach items="${lykilordVillur}" var="obj">
                        <li><c:out value="${obj}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </form>
</body>
</html>
<script>
    /*setInterval(function(){
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "https://hugbo1.herokuapp.com", true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify({
            to: 'helgigretargunnars.96@gmail.com',
            content: 'hello helgi'
        }));
        xhr.onload = function() {
            console.log("HELLO")
            console.log(this.responseText);
            var data = JSON.parse(this.responseText);
            console.log(data);
        }
        }, 20000);*/
</script>