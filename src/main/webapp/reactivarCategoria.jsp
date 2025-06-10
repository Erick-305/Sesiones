<%--
  Created by IntelliJ IDEA.
  User: pepew
  Date: 9/6/2025
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.erick.ManejodeSesiones.models.*" %>
<%@ page import="org.erick.ManejodeSesiones.models.Categoria" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<html>
<head>
    <title>Reactivar Categoría</title>
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
</head>
<body>
<h1>Categorías Desactivadas</h1>
<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
<table>
    <tr>
        <th>ID</th><th>Nombre</th><th>Descripción</th><th>Acción</th>
    </tr>
    <% for (Categoria cate : categorias) { %>
    <tr>
        <td><%=cate.getIdCategoria()%></td>
        <td><%=cate.getNombre()%></td>
        <td><%=cate.getDescripcion()%></td>
        <td><%=cate.getCondicion()%></td>
        <td>
            <a href="<%=request.getContextPath()%>/categoria/activar?id=<%=cate.getIdCategoria()%>">Activar</a>
        </td>
    </tr>
    <% } %>
</table>
<div>
    <a href="<%=request.getContextPath()%>/categoria">Regresar</a>
</div>
</body>
</html>