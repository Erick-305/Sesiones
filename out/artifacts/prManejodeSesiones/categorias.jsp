<%--
  Created by IntelliJ IDEA.
  User: pepew
  Date: 28/5/2025
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Categorías</title>
    <style>
        /* Navbar */
        .navbar {
            background-color: #343a40;
            overflow: hidden;
            padding: 0 20px;
        }
        .navbar a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 16px 20px;
            text-decoration: none;
            font-size: 17px;
        }
        .navbar a:hover {
            background-color: #495057;
            color: #fff;
        }
        .navbar .login-container {
            float: right;
        }
        .navbar input[type=text], .navbar input[type=password] {
            padding: 6px;
            margin-top: 12px;
            font-size: 17px;
            border: none;
            border-radius: 4px;
        }
        .navbar button {
            padding: 6px 10px;
            margin-top: 12px;
            margin-right: 8px;
            background: #17a2b8;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .navbar button:hover {
            background: #138496;
        }
        /* Tabla */
        table {
            width: 80%;
            margin: 40px auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 2px 8px #ccc;
        }
        th, td {
            padding: 14px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background: #007bff;
            color: white;
        }
        tr:hover {background-color: #f1f1f1;}
        /* Botones */
        .btn {
            padding: 6px 14px;
            border: none;
            border-radius: 4px;
            color: #fff;
            cursor: pointer;
            font-size: 15px;
        }
        .btn-update { background: #ffc107; color: #212529; }
        .btn-delete { background: #dc3545; }
        .btn-update:hover { background: #e0a800; }
        .btn-delete:hover { background: #bd2130; }
    </style>
</head>
<body>
<!-- Navbar con login -->
<div class="navbar">
    <a href="#">Inicio</a>
    <a href="#">Categorías</a>
    <a href="#">Contacto</a>
    <div class="login-container">
        <form action="login.jsp" method="post">
            <input type="text" placeholder="Usuario" name="username" required>
            <input type="password" placeholder="Contraseña" name="password" required>
            <button type="submit">Login</button>
        </form>
    </div>
</div>

<!-- Tabla de categorías -->
<table>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Acciones</th>
    </tr>
    <%
        // Ejemplo con datos fijos. Si tienes conexión a BD, reemplaza por tu consulta y bucle.
        // Connection conn = ...; Statement stmt = ...; ResultSet rs = stmt.executeQuery("SELECT * FROM categoria");
        // while(rs.next()) { ... }
        String[][] categorias = {{"1", ""}, {"2", ""}, {"3", ""}};
        for(int i=0; i<categorias.length; i++) {
    %>
    <tr>
        <td><%= categorias[i][0] %></td>
        <td><%= categorias[i][1] %></td>
        <td>
            <button class="btn btn-update" disabled>Actualizar</button>
            <button class="btn btn-delete" disabled>Eliminar</button>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
