<%--
  Created by IntelliJ IDEA.
  User: rberjon
  Date: 24/4/23
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="strings" var="strings"/>

<c:choose>
    <c:when test="${action == 'SELECT'}">
        <fmt:message bundle="${strings}" key="title.select.videojuego" var="title"/>
        <c:set var="actionURL" value=""/>
        <c:set var="actionMETHOD" value=""/>
        <c:set var="actionSUBMIT" value="${mvc.uri('getVideojuegos', {'language': mvc.locale.language})}"/>
        <c:set var="actionDISABLED" value="disabled"/>
    </c:when>

    <c:when test="${action == 'INSERT'}">
        <fmt:message bundle="${strings}" key="title.insert.videojuego" var="title"/>
        <c:set var="actionURL" value="${mvc.uri('postVideojuego', {'language': mvc.locale.language})}"/>
        <c:set var="actionMETHOD" value="POST"/>
        <fmt:message key="label.action.insert.videojuego" bundle="${strings}" var="actionSUBMIT" />
        <c:set var="actionDISABLED" value=""/>
    </c:when>

    <c:when test="${action == 'UPDATE'}">
        <fmt:message bundle="${strings}" key="title.update.videojuego" var="title"/>
        <c:set var="actionURL" value="${mvc.uri('putVideojuegoById', {'id': videojuego.id, 'language': mvc.locale.language})}"/>
        <c:set var="actionMETHOD" value="PUT"/>
        <fmt:message key="label.submit.update.videojuego" bundle="${strings}" var="actionSUBMIT" />
        <c:set var="actionDISABLED" value=""/>
    </c:when>

    <c:otherwise>
        <fmt:message bundle="${strings}" key="title.update.videojuego" var="title"/>
        <c:set var="actionURL"    value="${mvc.uri('deleteVideojuegoById', {'id': videojuego.id, 'language': mvc.locale.language})}"/>
        <c:set var="actionMETHOD" value="DELETE"/>
        <fmt:message key="label.submit.delete.videojuego" bundle="${strings}" var="actionSUBMIT" />
        <c:set var="actionDISABLED" value="disabled"/>
    </c:otherwise>
</c:choose>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>

<%--       <c:if test="${not empty errors}">--%>
<%--           <ul>--%>
<%--               <c:forEach var="errorMessage" items="${errors}">--%>
<%--                   <li>${errorMessage}</li>--%>
<%--               </c:forEach>--%>
<%--           </ul>--%>
<%--       </c:if>--%>


<form action="${actionURL}" method="POST" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="_method" value="${actionMETHOD}"/>

    <%-- no se si la portada y acciones debo ponerlas --%>
    <label for="inputPortada"> <fmt:message bundle="${strings}" key="label.portada"/> </label>
    <input id="inputPortada" type="text" name="portada" value="${videojuego.portada}"  ${actionDISABLED} />   <span style="color: red">&nbsp;&nbsp;${errors.portada}</span>
    <br/>
    <label for="inputNombre"> <fmt:message bundle="${strings}" key="label.nombre"/> </label>
    <input id="inputNombre" type="text" name="director" value="${videojuego.nombre}" ${actionDISABLED}/> <span style="color: red">&nbsp;&nbsp;${errors.nombre}</span>
    <br/>
    <label for="inputFecha"> <fmt:message bundle="${strings}" key="label.fecha"/> </label>
    <input id="inputFecha" type="date" name="fecha" value="${videojuego.fecha}" ${actionDISABLED}/> <span style="color: red">&nbsp;&nbsp;${errors.fecha}</span>
    <br/>
    <label for="inputGenero"> <fmt:message bundle="${strings}" key="label.genero"/> </label>
    <input id="inputGenero" type="text" name="genero" value="${videojuego.genero}" ${actionDISABLED}/> <span style="color: red">&nbsp;&nbsp;${errors.genero}</span>
    <br/>
    <br/>
    <label for="inputAcciones"> <fmt:message bundle="${strings}" key="title.thead.actions"/> </label>
    <input id="inputAcciones" type="text" name="acciones" value="${videojuego.acciones}" ${actionDISABLED}/> <span style="color: red">&nbsp;&nbsp;${errors.acciones}</span>


    <c:choose>
        <c:when test="${action == 'SELECT'}">
            <br/>
            <br/>
            <a href="${actionSUBMIT}"><fmt:message key="label.action.back.select.videojuegos" bundle="${strings}"/> </a>
        </c:when>
        <c:otherwise>
            <input type="submit" value="${actionSUBMIT}"/>
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>