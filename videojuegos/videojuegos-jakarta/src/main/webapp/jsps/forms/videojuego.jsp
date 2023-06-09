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
        <c:set var="actionURL" value="${mvc.uri('postVideojuego', {'id': videojuego.id, 'language': mvc.locale.language})}"/>
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
        <fmt:message bundle="${strings}" key="title.delete.videojuego" var="title"/>
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

        <form action="${actionURL}" method="POST" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="_method" value="${actionMETHOD}"/>
            <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 row-cols-xl-6">
            <c:choose>
                <c:when test="${action != 'INSERT' && action != 'UPDATE'}">
                    <table class="table table-striped">
                        <tbody class="table-group-divider">
                         <tr class="align-middle">
                             <td><label for="inputCartel"> <fmt:message bundle="${strings}" key="label.cartel"/> </label></td>
                             <td><img id="inputCartel" src="${videojuego.cartel}" width="200" height="250" class="img-fluid" alt="Imagen de ${videojuego.nombre}">   <span style="color: red">&nbsp;&nbsp;${errors.cartel}</span></td>
                         </tr>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <label for="inputCartel2"> <fmt:message bundle="${strings}" key="label.cartel"/> </label>
                    <input id="inputCartel2" type="text" name="cartel" value="${videojuego.cartel}"  ${actionDISABLED} />   <span style="color: red"> ${errors.cartel}</span>
                </c:otherwise>
            </c:choose>
            </div>
            <br/>
            <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 row-cols-xl-6">
                <label for="inputNombre"> <fmt:message bundle="${strings}" key="label.nombre"/> </label>
                <input id="inputNombre" type="text" name="nombre" value="${videojuego.nombre}" ${actionDISABLED}/> <span style="color: red">&nbsp;&nbsp;${errors.nombre}</span>
            </div>
            <br/>
            <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 row-cols-xl-6">
                <label for="inputFecha"> <fmt:message bundle="${strings}" key="label.fecha"/> </label>
                <input id="inputFecha" type="date" name="fecha" value="${videojuego.fecha_lanzamiento}" ${actionDISABLED}/> <span style="color: red">&nbsp;&nbsp;${errors.fecha}</span>
            </div>
            <br/>
            <div class="row row-cols-2 row-cols-sm-3 row-cols-md-4 row-cols-lg-5 row-cols-xl-6">
                <label for="inputGenero"> <fmt:message bundle="${strings}" key="label.genero"/> </label>
                <input id="inputGenero" type="text" name="genero" value="${videojuego.genero}" ${actionDISABLED}/> <span style="color: red">&nbsp;&nbsp;${errors.genero}</span>
            </div>
            <br/>

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
