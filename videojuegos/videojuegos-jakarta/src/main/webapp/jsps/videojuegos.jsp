<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 23/05/2023
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Videojuegos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="table-responsive">
    <table class="table table-striped table-hover caption-top">
        <caption>Lista de videojuegos</caption>
        <thead>
        <tr>
            <th class="col-1">Portada</th>
            <th class="col-1">Id</th>
            <th class="col-6">Nombre</th>
            <th class="col-1">Genero</th>
            <th class="col-3">Acciones</th>
        </tr>
        </thead>

        <tbody class="table-group-divider">
        <c:forEach var="videojuego" items="${videojuegos}">

            <c:set var="urlGetVideojuegoById"  value="${mvc.uri('getVideojuegoById',       {'id': videojuego.id()}) }"/>
            <c:set var="urlUpdateVideojuego"   value="${mvc.uri('getUpdateVideojuegoForm', {'id': videojuego.id()}) }"/>
            <c:set var="urlDeleteVideojuego"   value="${mvc.uri('getDeleteVideojuegoForm', {'id': videojuego.id()}) }"/>


            <tr class="align-middle">
                <td class="col-1">
                    <img src="${videojuego.cartel()}" class="img-fluid" alt="Imagen de ${videojuego.nombre()}">
                </td>
                <td class="col-1">${videojuego.id()}</td>
                <td class="col-6"> <a href="${urlGetVideojuegoById}"> ${videojuego.nombre()} </a> </td>
                <td class="col-1">${videojuego.genero()}</td>
                <td class="col-3"> <a href="${urlUpdateVideojuego}" class="btn btn-primary">Editar</a> <a href="${urlDeleteVideojuego}" class="btn btn-primary">Eliminar</a></td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <c:set var="urlInsertVideojuego"   value="${mvc.uri('getInsertVideojuegoForm')}"/>
    <a href="${urlInsertVideojuego}" class="btn btn-primary">Añadir videojuego</a>
</div>
</body>
</html>
