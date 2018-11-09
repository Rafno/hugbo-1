<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

<head>
    <title>Appótekið - nýskráning</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/register.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/headNavigation.css"/>"/>
    <jsp:include page="../headNavigation/headNavigation.jsp" />
</head>
<body>

    <div class="registerHeader">Búa til nýjan notanda</div>
    <form method="post" class = "registeringContainer">
        <div class="registeringUsername">
            <div class="row">
                <h4 class="title" id = registerUserName>Notendanafn</h4>
                <input type="text" placeholder="  Enga íslenska stafi" name="username"  class="registerText" id = inputText1 />
                <param name = "username" value="">
            </div>
            <br />
            <div class="row">
                <h4 class="title">Lykilorð</h4>
                <input type="text" placeholder="  Lágmark 6 stafir" name="password"style="-webkit-text-security: disc;" class="registerText" id = inputText2 />
            </div>
            <br />
            <div class="row">
            <h4 class="title">Staðfesta lykilorð</h4>
            <input type="text" placeholder="  Passaðu að hafa lykilorðin eins " name="passwordRepeat" style="-webkit-text-security: disc;" class="registerText" id = inputText3 />
            </div>
            <br />
            <div class="row">
            <h4 class="title">Nafn</h4>
            <input type="text" placeholder="  Þarf að fylla út" name="name" value="${nafn}" required class="registerText" id = inputText4 maxlength="40" />
            </div>
            <br />
            <div class="row">
            <h4 class="title">Hlutverk</h4>
            <select class="optionPannelDoctorPatient" onChange="myFunction(this.options[this.selectedIndex].value)" name = "role">
                <option value="USER">    Sjúklingur  </option>
                <option value="DOCTOR">        Læknir          </option>
            </select>
            </div>
            <br />
            <input type="text" value="${succesfull}" id="succesfull" readonly>
            <br />
            <div class="rowButton">
            <input type="submit" value="Staðfesta" id="confirm"/>
            </div>
        </div>
        <div class="registeringHomeAdress">
            <div class="row">
                <h4 class="title">Netfang</h4>
                <input type="text" name="emailAddress"  class="registerText" id = inputTex8 required/>
                <param name="emailAddress" value="">
            </div>
            <div class="row">
                <h4 class="title">Heimilisfang</h4>
                <input type="text" name="homeAddress"  class="registerText" id = inputText5 />
                <param name="homeAddress" value="">
            </div>
            <br />
            <div class="row">
                <h4 class="title">Bæjarfélag</h4>
                <input type="text"  name="homeTown" class="registerText" id = inputText6 />
                <param name="homeTown" value="">
            </div>
            <br />
            <div class="row">
                <h4 class="title">Póstnúmer</h4>
                <input type="text" placeholder="  Aðeins tölustafir " name="zipCode" class="registerText" id = inputText7 />
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