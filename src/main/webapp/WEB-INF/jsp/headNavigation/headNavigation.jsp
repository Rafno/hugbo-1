
<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>

    <head>
        <title>App�teki�</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/headNavigation.css"/>"/>
    </head>
    <header>
        <ul class="headNavigation">
            <li class="itemNavigation"><a class="active" href="/">App�tek</a></li>
            <li class="itemNavigation"><a class="active" href="/about">Um vefs��una</a></li>
            <li class="itemNavigation" id="homeArea">
                <a href="/myHome"class="active">
                    <img src="./../../../img/house.png" alt="headImage"class="headNavigationPic"/>
                </a>
            </li>
            <li class="itemNavigation"><a class="active" href="/login">Innskr�ning</a></li>

        </ul>
    </header>
</html>
