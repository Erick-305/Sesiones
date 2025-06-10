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
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.erick.ManejodeSesiones.models.*" %>
<%@ page import="org.erick.ManejodeSesiones.models.Categoria" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
    <title>Listado Categoria</title>
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css">
</head>
<body>

<h1>Listado Categoria</h1>
<%
    if (username.isPresent()) {%>
<div style="color: blue;">Hola, <%=username.get()%> bienvenido</div>
<div><a href="${pageContext.request.contextPath}/categoria/form">Añadir Categorias</a></div>

<%}%>

<table>
    <thead>
    <th>ID CATEGORIA</th>
    <th>NOMBRE</th>
    <th>DESCRIPCIÓN</th>
    <th>CONDICIÓN</th>
    <th>ACCIÓN</th>
    </thead>
    <%
        for (Categoria cate : categorias) {%>
    <tbody>
    <td><%=cate.getIdCategoria()%></td>
    <td><%=cate.getNombre()%></td>
    <td><%=cate.getDescripcion()%></td>
    <td><%=cate.getCondicion()%></td>
    <%if(username.isPresent()){%>
    <td>
        <a href="<%=request.getContextPath()%>/categoria/form?id=<%=cate.getIdCategoria()%>">Editar</a>
        <a href="<%=request.getContextPath()%>/categoria/desactivar?id=<%=cate.getIdCategoria()%>">Desactivar</a>
    </td>
    <%}%>
    </tbody>

    <% }%>

</table>
<a href="<%=request.getContextPath()%>/categoria/reactivar">Reactivar Categorías</a>
<br>
<br>
<div>
    <a href="<%=request.getContextPath()%>/index.html">Inicio</a>
</div>
</body>
</html>