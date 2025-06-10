<%--
  Created by IntelliJ IDEA.
  User: pepew
  Date: 9/6/2025
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.erick.ManejodeSesiones.models.*" %>
<%@ page import="org.erick.ManejodeSesiones.models.Articulo" %>
<%
    List<Articulo> articulos = (List<Articulo>) request.getAttribute("articulos");
%>
<html>
<head>
    <title>Reactivar Artículo</title>
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
</head>
<body>
<h1>Artículos Desactivados</h1>
<table>
    <tr>
        <th>ID</th>
        <th>ID Categoría</th>
        <th>Código</th>
        <th>Nombre</th>
        <th>Stock</th>
        <th>Descripción</th>
        <th>Imagen</th>
        <th>Condición</th>
        <th>Acción</th>
    </tr>
    <% for (Articulo art : articulos) { %>
    <tr>
        <td><%=art.getIdarticulo()%></td>
        <td><%=art.getIdcategoria()%></td>
        <td><%=art.getCodigo()%></td>
        <td><%=art.getNombre()%></td>
        <td><%=art.getStock()%></td>
        <td><%=art.getDescripcion()%></td>
        <td><%=art.getImagen()%></td>
        <td><%=art.getCondicion()%></td>
        <td>
            <a href="<%=request.getContextPath()%>/articulo/activar?id=<%=art.getIdarticulo()%>">Activar</a>
        </td>
    </tr>
    <% } %>
</table>
<div>
    <a href="<%=request.getContextPath()%>/articulo">Regresar</a>
</div>
</body>
</html>