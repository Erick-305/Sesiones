<%--
  Created by IntelliJ IDEA.
  User: pepew
  Date: 29/5/2025
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%--
  Este JSP muestra un listado de categorías obtenidas del servidor
  y permite activar/desactivar categorías, así como agregar nuevas.
--%>
<%--
  Este JSP muestra un formulario para crear, editar o confirmar la activación de una categoría.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.erick.ManejodeSesiones.models.*" %>
<%@ page import="org.erick.ManejodeSesiones.models.Categoria" %>
<%
    Categoria categorias = (Categoria) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>
<html>
<head>
    <title>Formulario Categoria</title>
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
</head>
<body>
<h1>Formulario Categoria</h1>
<div>
    <form action="<%=request.getContextPath()%>/categoria/form" method="post">
        <div>
            <label for="nombre">Ingrese el nombre de categoria</label>
            <div>
                <input type="hidden" name="id" value="<%=categorias.getIdCategoria()%>">
                <input type="text" id="nombre" name="nombre" value="<%=categorias.getNombre() != null ? categorias.getNombre() : ""%>">
                <div>
                    <span style="color:red;"><%= (errores != null && errores.get("nombre") != null) ? errores.get("nombre") : "" %></span>
                </div>
            </div>
        </div>

        <div>
            <label for="descripcion">Ingrese la descripción</label>
            <div>
                <input type="text" id="descripcion" name="descripcion" value="<%=categorias.getDescripcion() != null ? categorias.getDescripcion() : ""%>">
                <div>
                    <span style="color:red;"><%= (errores != null && errores.get("descripcion") != null) ? errores.get("descripcion") : "" %></span>
                </div>
            </div>
        </div>

        <div>
            <input type="submit" value="<%=(categorias.getIdCategoria() != null && categorias.getIdCategoria() > 0) ? "Editar" : "Crear"%>">
        </div>
    </form>
</div>
</body>
</html>
