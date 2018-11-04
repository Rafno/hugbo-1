<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<jsp:include page="../headNavigation/headNavigation.jsp" />

<head>
    <title>Project Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/myArea.css"/>"/>
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
            <table id="lyf" border="1">
                <tr>
                    <th>Heiti lyfs</th>
                    <th>Lyfjaform</th>
                    <th>Styrkleiki</th>
                    <th>Áminingakerfi</th>
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

                </tr>
            </table>
        </div>
    </div>
</body>
<script>
    function togglerer(){
        alert("hel")
        var a = document.getElementById("toggler").value;
        if(a == 'on'){
            alert("í gangir")
        }else{
            alert('ekki í gangi');
        }
        

    }
</script>