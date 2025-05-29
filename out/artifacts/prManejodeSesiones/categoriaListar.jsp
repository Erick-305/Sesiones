<%--
  Created by IntelliJ IDEA.
  User: pepew
  Date: 28/5/2025
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.erick.ManejodeSesiones.services.*"%>
<%@ page import="org.erick.ManejodeSesiones.services.services.models.Categoria" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias"));
    Optional<String> username = (Optional<String>) request.getAttrbute("username");
%>
<html>
<head>
    <title>Listado Categoria</title>
</head>
<body>
    <%
        if(username.isPresent()){%>
    <div style="color:blue;">Hola <%= username.get()%>, bienvenido a la aplicacion</div>
    <div><p><a href="${pageContext.req.contextPath}/categoria/form">Ingrese el producto</a></p></div>
    <%    }
    %>

    <h1>Listado Categoria</h1>
    <table>
        <thead>
            <th>Id Categoria</th>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Condicion</th>
            <th>Acciones</th>
        </thead>
        <%
            for(Categoria cat : categorias){ %>
            <tbody>
            <td><%cat.getIdCategoria()%></td>
            <td><%cat.getNombre()%></td>
            <td><%cat.getDescripcion%></td>
            <td><%cat.getCondicion()%></td>
            <td><a href="">Editar</a></td>
            <td><a href="">Activar o Desactivar</a></td>
            </tbody>
            <% } %>

    </table>
    <!-- Formulario para agregar producto directamente desde esta vista (opcional) -->
    <h2>Agregar Nuevo Producto</h2>
    <form action="<%= request.getContextPath() %>/productos" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required><br>

        <label for="descripcion">Descripción:</label>
        <textarea name="descripcion" id="descripcion" required></textarea><br>

        <button type="submit">Guardar Producto</button>
    </form>


</body>
</html>
