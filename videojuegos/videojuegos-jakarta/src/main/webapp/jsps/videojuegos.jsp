<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 25/5/23
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="strings" var="strings"/>
<fmt:message key="title.videojuegos"  bundle="${strings}" var="title"/>

<html>
<head>
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<c:choose>
    <c:when test="${mvc.locale.language == 'es'}">
        <c:set var="languageURL" value="${mvc.uri('getVideojuegos', {'language': 'en'})}"/>
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Flag_of_the_United_Kingdom_%281-2%29.svg/1200px-Flag_of_the_United_Kingdom_%281-2%29.svg.png" width="50" height="50" class="img-fluid">
        <a href="${languageURL}"> <fmt:message key="locale.ingles" bundle="${strings}"/> </a>
    </c:when>

    <c:otherwise>
        <c:set var="languageURL" value="${mvc.uri('getVideojuegos', {'language': 'es'})}"/>
        <img src="https://upload.wikimedia.org/wikipedia/commons/8/89/Bandera_de_Espa%C3%B1a.svg" width="50" height="50" class="img-fluid">
        <a href="${languageURL}"> <fmt:message key="locale.espanol" bundle="${strings}"/> </a>
    </c:otherwise>
</c:choose>

<br/>
<br/>

<h1>${title}</h1>
<table class="table table-striped table-hover caption-top">
    <thead>
    <tr>
        <th> <fmt:message bundle="${strings}" key="title.thead.title"/>  </th>
        <th> <fmt:message bundle="${strings}" key="title.thead.actions"/> </th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    <c:forEach var="videojuego" items="${videojuegos}">
        <c:set var="selectVideojuegoURI" value="${mvc.uri('getVideojuegoById',           {'id': videojuego.id, 'language': mvc.locale.language}) }"/>
        <c:set var="updateVideojuegoURI" value="${mvc.uri('getFormUpdateVideojuegoById', {'id': videojuego.id, 'language': mvc.locale.language}) }"/>
        <c:set var="deleteVideojuegoURI" value="${mvc.uri('getFormDeleteVideojuegoById', {'id': videojuego.id, 'language': mvc.locale.language}) }"/>
        <tr>
            <td> <a class="btn btn-primary" href="${selectVideojuegoURI}">${videojuego.nombre}</a> </td>
            <td>
                <a class="btn btn-primary btn-warning" href="${updateVideojuegoURI}"> <fmt:message key="label.submit.update.videojuego" bundle="${strings}"/> </a>
                &nbsp;&nbsp;&nbsp;
                <a class="btn btn-primary btn-danger" href="${deleteVideojuegoURI}"><fmt:message key="label.submit.delete.videojuego" bundle="${strings}"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<c:set var="insertVideojuegoURI" value="${mvc.uri('getFormInsertVideojuego', {'language': mvc.locale.language}) }"/>
<a class="btn btn-primary btn-success" href="${insertVideojuegoURI}"> <fmt:message key="label.submit.insert.videojuego" bundle="${strings}"/>  </a>
</body>
</html>
