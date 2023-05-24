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
        <a href="${languageURL}"> <fmt:message key="locale.ingles" bundle="${strings}"/> </a>
    </c:when>

    <c:otherwise>
        <c:set var="languageURL" value="${mvc.uri('getVideojuegos', {'language': 'es'})}"/>
        <a href="${languageURL}"> <fmt:message key="locale.espanol" bundle="${strings}"/> </a>
    </c:otherwise>
</c:choose>

<h1>${title}</h1>
<table>
    <thead>
    <tr>
        <th> <fmt:message bundle="${strings}" key="title.thead.title"/>  </th>
        <th> <fmt:message bundle="${strings}" key="title.thead.actions"/> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="videojuego" items="${videojuegos}">
        <c:set var="selectVideojuegoURI" value="${mvc.uri('getVideojuegoById',           {'id': videojuego.id, 'language': mvc.locale.language}) }"/>
        <c:set var="updateVideojuegoURI" value="${mvc.uri('getFormUpdateVideojuegoById', {'id': videojuego.id, 'language': mvc.locale.language}) }"/>
        <c:set var="deleteVideojuegoURI" value="${mvc.uri('getFormDeleteVideojuegoById', {'id': videojuego.id, 'language': mvc.locale.language}) }"/>
        <tr>
            <td> <a href="${selectVideojuegoURI}">${videojuego.title}</a> </td>
            <td>
                <a href="${updateVideojuegoURI}"> <fmt:message key="label.submit.update.videojuego" bundle="${strings}"/> </a>
                &nbsp;&nbsp;&nbsp;
                <a href="${deleteVideojuegoURI}"><fmt:message key="label.submit.delete.videojuego" bundle="${strings}"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<c:set var="insertVideojuegoURI" value="${mvc.uri('getFormInsertVideojuego', {'language': mvc.locale.language}) }"/>
<a href="${insertVideojuegoURI}"> <fmt:message key="label.submit.insert.videojuego" bundle="${strings}"/>  </a>
</body>
</html>
